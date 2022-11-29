package com.example.csis3275project.web;

import com.example.csis3275project.entities.Events;
import com.example.csis3275project.entities.Groups;
import com.example.csis3275project.repositories.EventsRepository;
import com.example.csis3275project.repositories.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class listServices {
    @Autowired
    GroupsRepository groupsRepository;
    EventsRepository eventsRepository;

    public List<Groups> getAllGroups(){
        List<Groups> list = (List<Groups>)groupsRepository.findAll();
        return list;
    }

    public List<Events> getAllEvents(){
        List<Events> list = (List<Events>)eventsRepository.findAll();
        return list;
    }
}
