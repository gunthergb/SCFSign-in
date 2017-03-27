package edu.scf.labsignin.db.util;

/**
 * Firebase Utility methods.
 */
public final class FirebaseUtil {

    private FirebaseUtil(){}//prevent construction




    /**
     * Waits for data to finish uploading to firebase.
     * This should be called before the application exits.
     * Currently this just sleeps for 1000 ms.
     */
    public static void waitFB() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }


}
