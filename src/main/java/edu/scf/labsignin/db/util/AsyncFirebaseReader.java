package edu.scf.labsignin.db.util;

import com.firebase.client.*;
import edu.scf.labsignin.db.FirebaseObject;

import java.lang.reflect.Type;
import java.util.concurrent.CountDownLatch;

/**
 * Waits for data to be read from firebase.
 * Users can use this class to read data from firebase asynchronously,
 * versus having the data read concurrently.
 */
public final class AsyncFirebaseReader<T> implements ValueEventListener {

    private final CountDownLatch latch = new CountDownLatch(1);
    private final GenericTypeIndicator<T> dataType;
    private T value;
    private FirebaseException error;

    private AsyncFirebaseReader() {
        this((Class)null);
    }

    private AsyncFirebaseReader(GenericTypeIndicator<T> dataType) {
        this.dataType = dataType;
    }
    private AsyncFirebaseReader(Class<T> dataType) {
        this(new GenericTypeIndicator<T>() {
            @Override
            public Type getType() {
                return dataType;
            }
        });
    }

    //////////////////////////////////////////////////////////////////////////////////

    private static <T> AsyncFirebaseReader<T> create(Class<T> type) {
        return new AsyncFirebaseReader<>(type);
    }
    private static <T> AsyncFirebaseReader<T> create(GenericTypeIndicator<T> type) {
        return new AsyncFirebaseReader<>(type);
    }

    //Users should call these methods:
    public static <T> T getValue(Firebase fb, Class<T> type) { //throws no errors
        AsyncFirebaseReader<T> reader = create(type);
        fb.addListenerForSingleValueEvent(reader);
        return reader.get();
    }
    public static <T> T getValue(Firebase fb, GenericTypeIndicator<T> type) { //throws no errors
        AsyncFirebaseReader<T> reader = create(type);
        fb.addListenerForSingleValueEvent(reader);
        return reader.get();
    }

    public static <T> T getValueWithError(Firebase fb, Class<T> type) throws FirebaseException {
        AsyncFirebaseReader<T> reader = create(type);
        fb.addListenerForSingleValueEvent(reader);
        return reader.getWithError();
    }
    public static <T> T getValueWithError(Firebase fb, GenericTypeIndicator<T> type) throws FirebaseException {
        AsyncFirebaseReader<T> reader = create(type);
        fb.addListenerForSingleValueEvent(reader);
        return reader.getWithError();
    }

    //////////////////////////////////////////////////////////////////////////////////


    /**
     * Awaits (if needed) and returns the value from firebase.
     * This may be called multiple times. This will suppresses
     * any exceptions that may have occurred and simply return
     * null instead.
     *
     * @return the value returned from firebase.
     * @see #getWithError()
     */
    public T get() {
        try {
            latch.await(); //Wait for the data to be given to us (if not already)...
        } catch (InterruptedException ignored) {
        }
        return value;
    }

    /**
     * Gets any errors that occurred when trying to get
     * the value from firebase. This can be used in place
     * of having the exception being thrown.
     *
     * @return the exception.
     */
    public FirebaseException getError() {
        return error;
    }

    /**
     * Calls {@link #get()} while throwing any exceptions
     * that may have occurred. Normal get() call suppresses
     * any exceptions that may have occurred.
     *
     * @throws FirebaseException if a problem occurred
     * @see #get()
     */
    public T getWithError() throws FirebaseException {
        T val = get();
        if (error != null) throw error;
        return val;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        checkForReuse();
        try {
            if (dataType == null) {//Just raw values
                value = (T) dataSnapshot.getValue();
            } else { //Try to turn it into more meaningful data
                value = dataSnapshot.getValue(dataType);
            }
            if (value instanceof FirebaseObject) {
                //Set the ref if its a common firebase object (for ease of use)
                FirebaseObject fbo = (FirebaseObject) value;
                fbo.setRef(dataSnapshot.getRef());
            }
        } catch (FirebaseException exception) {//Something went wrong when trying to interpret the data...
            //Return back the exception to be dealt with later.
            error = exception;
        } finally { //Guaranteed to be ran...
            latch.countDown(); //Notify data has been received
        }
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {
        checkForReuse();
        error = firebaseError.toException();
        latch.countDown();
        ;
    }

    //Ensures the data is fetched once, and only once.
    //We are designed to only be used once.
    private void checkForReuse() {
        if (latch.getCount() == 0)//we were already used before...
            throw new IllegalStateException("This can only be used once! Create a new one");
    }

}
