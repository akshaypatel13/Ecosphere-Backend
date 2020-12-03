package com.example.Ecosphere.events;

import com.example.Ecosphere.databaseConfiguration.StoredProcedure;
import com.example.Ecosphere.userAuthentication.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventPersistence {

    public ArrayList<Event> loadEvents(){
        StoredProcedure proc = null;
        ArrayList<Event> events = new ArrayList<Event>() ;
        try {

            proc = new StoredProcedure("spLoadEvents()");
            ResultSet results = proc.resultSetExecution();
            if (null != results) {
                while (results.next()) {
                    Event event = new Event();
                    event.setEventID(results.getLong(1));
                    event.setEventName(results.getString(2));
                    event.setEventDescription(results.getString(3));
                    event.setEventLink(results.getString(4));
                    event.setEventImg(results.getString(5));
                    events.add(event);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != proc) {
                proc.cleanUp();
            }
        }
        return events;
    }

    public boolean registerEvent(Event event){
        StoredProcedure proc = null;
        try {
            proc = new StoredProcedure("spCreateEvent(?, ?, ?, ?)");
            proc.setParameter(1,event.getEventName());
            proc.setParameter(2, event.getEventDescription());
            proc.setParameter(3, event.getEventLink());
            proc.setParameter(4, event.getEventImg());
            proc.statementExecute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != proc) {
                proc.cleanUp();
            }
        }
        return true;
    }
}
