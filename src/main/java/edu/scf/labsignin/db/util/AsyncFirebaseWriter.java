package edu.scf.labsignin.db.util;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.FirebaseException;

import java.util.concurrent.CountDownLatch;

public final class AsyncFirebaseWriter implements Firebase.CompletionListener {

    private final CountDownLatch latch = new CountDownLatch(1);
    private FirebaseException error;
    private boolean success;

    private AsyncFirebaseWriter() {//Ensure only we can work with this object...
    }

    ///////////////////////////////////////////////////////////////////

    /**
     * Sets the value of the firebase, waiting for the value
     * to be uploaded. This will suppress any errors that may
     * have occurred during upload, and will return true or false
     * if it was successfully uploaded.
     * @param val the value to set to the firebase
     * @param dest the firebase to set
     * @return true the value was successfully set, false otherwise.
     */
    public static boolean setValue(Object val, Firebase dest) {
        AsyncFirebaseWriter writer = new AsyncFirebaseWriter();
        dest.setValue(val,writer);
        try {
            writer.latch.await();//Wait for upload...
        } catch (InterruptedException ignored) {//suppress
        }
        return writer.success;
    }

    /**
     * Sets the value of the firebase, waiting for the value
     * to be uploaded. If any errors occur while uploading the
     * value, then the error will be throw. When an error is thrown
     * it should be assumed the value was not set. When no error
     * was thrown, and this returns, then it can be assumed the value
     * was set.
     * @throws InterruptedException if our thread was interrupted while waiting
     *                              for upload.
     * @throws FirebaseException if firebase failed to upload.
     * @param val the value to set to the firebase
     * @param dest the firebase to set
     */
    public static void setValueWithError(Object val, Firebase dest) throws InterruptedException, FirebaseException {
        AsyncFirebaseWriter writer = new AsyncFirebaseWriter();
        dest.setValue(val,writer);
        writer.latch.await();//Wait for upload...
        if(writer.error != null) throw writer.error;
    }

    ///////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////
    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
        ensureSingleUse();
        success = firebaseError != null && firebase != null;
        if(firebaseError != null) {
            error = firebaseError.toException();
        }
        latch.countDown(); //Notify upload complete...
    }

    private void ensureSingleUse() {
        if(latch.getCount()==0)
            throw new IllegalStateException("this should only be used once!");
    }
}
