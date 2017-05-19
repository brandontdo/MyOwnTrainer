package com.example.sportshack;

import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMDataObjectSpecialization;

@IBMDataObjectSpecialization("Player")
public class Player extends IBMDataObject {
    private static final String NAME = "name", NUMBER = "11";

    public String getName() {
        return (String) getObject(NAME);
    }

    public void setName(String itemName) {
        setObject(NAME, (itemName != null) ? itemName : "");
    }

    public Player(String number, String name) {
        setObject(NUMBER, (number != null) ? number : "");
        setObject(NAME, (name != null) ? name : "");
    }
}
