package com.myowntrainer;

import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMDataObjectSpecialization;

@IBMDataObjectSpecialization("ExLog")
public class ExLog extends IBMDataObject {
    
    private static final String NAME = "Name", EXERCISE = "Exercise", REPS = "Reps";

    public String getName() {
        return (String) getObject(NAME);
    }
    public void setName(String name) {
        setObject(NAME, (name != null) ? name : "");
    }

    public String getExercise() {
        return (String) getObject(EXERCISE);
    }

    public void setExercise(String exercise) {
        setObject(EXERCISE, (exercise != null) ? exercise : "");
    }
    
    public String getReps() {
        return (String) getObject(REPS);
    } 
    public void setReps(String reps) {
        setObject(REPS, (reps != null) ? reps : "");
    }
    
}
