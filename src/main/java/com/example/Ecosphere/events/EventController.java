package com.example.Ecosphere.events;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    EventPersistence eventPersistence;

    EventController(){
        eventPersistence = new EventPersistence();
    }

    @RequestMapping(value = "/loadEvents", produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public List<Event> getEvents(){
        List<Event> events = eventPersistence.loadEvents();
        return events;
    }

    @RequestMapping(value = "/registerEvent", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public boolean createUser(@RequestBody Event event){
        event.setEventName(event.getEventName());
        event.setEventDescription(event.getEventDescription());
        event.setEventLink(event.getEventLink());
        event.setEventImg(event.getEventImg());
        return event.createEvent(eventPersistence);
    }

    @RequestMapping(value = "/deleteEvent", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public boolean deleteEvent(@RequestBody Event event) {
        return eventPersistence.deleteEvent(event);
    }

    }
