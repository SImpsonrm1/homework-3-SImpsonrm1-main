package edu.appstate.cs.cloud.restful.datastore;

import com.google.cloud.datastore.*;
import edu.appstate.cs.cloud.restful.models.Subject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SubjectService {
    // TODO: What is needed here?
    private Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    private KeyFactory keyFactory = datastore.newKeyFactory().setKind("Subject");
    private ArrayList<Subject> subjects = new ArrayList<>();

    public Key createSubject(Subject subject) {
        // TODO: What code needs to be added here to create a subject?
        Key key = datastore.allocateId(keyFactory.newKey());
        String subjectName = subject.getSubjectName();
         Entity subjectEntity = Entity.newBuilder(key)
                .set("subjectName", subjectName)
                .build();
        datastore.put(subjectEntity);
        return key;
    }

    public List<Subject> getAllSubjects() {
        // TODO: What code needs to be added here to retrieve all subjects?
        List<Subject> subjects = new ArrayList<>();
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind("Subject")
                .build();
        QueryResults<Entity> results = datastore.run(query);
        while (results.hasNext()) {
            Entity entity = results.next();
            Subject subject = new Subject.Builder()
                    .withSubjectName(entity.getString("subjectName"))
                    .build();
            subjects.add(subject);
        }
        return subjects;

        // TODO: Remove this return statement once you have something valid to return
    }

    // TODO: What support methods are needed here?
    // Feel free to look at the other service classes for inspiration.
    
    


        
    
}
