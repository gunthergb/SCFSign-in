package edu.scf.labsignin.db;

import com.firebase.client.*;
import edu.scf.labsignin.db.util.AsyncFirebaseReader;
import edu.scf.labsignin.db.util.AsyncFirebaseWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


public final class Subjects extends FirebaseObject {

    Subjects(Firebase ref) {
        super(ref);
    }

    //Gets all the subjects
    public Subject[] getSubjects() {
        Map<String,Subject> map = AsyncFirebaseReader.getValue(ref, new GenericTypeIndicator<Map<String, Subject>>() {});
        return map.values().toArray(new Subject[map.size()]);
    }

    //Get subjects later...
    public void getSubjectsLater(Consumer<Subject[]> consumer) {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Subject> map = dataSnapshot.getValue(new GenericTypeIndicator<Map<String, Subject>>() {});
                Subject[] subjects = map.values().toArray(new Subject[map.size()]);
                consumer.accept(subjects);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println(false);
            }
        });
    }

    //sets the subjects
    public boolean setSubjects(Subject[] subjects) {
        Map<String,Subject> mapped = new HashMap<>();
        for(Subject s : subjects) {
            mapped.put(s.getName(),s);
        }
        return AsyncFirebaseWriter.setValue(getRef(),mapped);
    }
}
