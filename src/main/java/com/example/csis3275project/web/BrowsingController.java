package com.example.csis3275project.web;

import com.example.csis3275project.entities.*;
import com.example.csis3275project.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class BrowsingController {
    @Autowired
    GroupsRepository groupsRepository;
    EventsRepository eventsRepository;

    @GetMapping("/home")
    public String trending(Model model){
        List<Events> events = eventsRepository.findAll();
        List<Events> topEvents = new ArrayList<>();

        try{
            for(int i=0; i<10; i++){
                topEvents.add(events.get(i));
            }
        } catch (Exception ignored){}

        model.addAttribute("listEvents", events);

        List<Groups> groups = groupsRepository.findAll();
        List<Groups> topGroups = new ArrayList<>();

        try{
            for(int i=0; i<10; i++){
                topGroups.add(groups.get(i));
            }
        } catch (Exception ignored){}
        model.addAttribute("listGroups", topGroups);

        return "browsingPage";
    }

    @GetMapping("/")
    public String trending2(Model model, Account account){
        List<Events> events = eventsRepository.findAll();
        List<Events> topPlaceholder = new ArrayList<>();
        List<Events> topEvents = new ArrayList<>();

        try{
            for(Events e: events){
                int res = e.getSchedule().compareTo(LocalDate.now());
                if (res>=0)
                    topPlaceholder.add(e);
            }

            for(int i=0;i<6;i++){
                topEvents.add(topPlaceholder.get(i));
            }
        } catch (Exception ignored){}

        model.addAttribute("listEvents", topEvents);

        List<Groups> groups = groupsRepository.findAll();
        List<Groups> topGroups = new ArrayList<>();

        try{
            for(int i=0; i<10; i++){
                topGroups.add(groups.get(i));
            }
        } catch (Exception ignored){}
        model.addAttribute("listGroups", topGroups);

//        account
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        account = (Account) auth.getPrincipal();

        String user = account.getFirstName()+" "+account.getLastName();

        model.addAttribute("user",user);

        return "browsingPage";
    }

    @GetMapping("/search")
    public String searchName(Model model, String keyword){

        List<Events> events;
        List<Groups> groups;

        if (keyword.isEmpty()) {
            events = eventsRepository.findAll();
            groups = groupsRepository.findAll();
        } else {
            events = eventsRepository.findByKeyword(keyword);
            groups = groupsRepository.findByKeyword(keyword);
        }

        model.addAttribute("listEvents",events);
        model.addAttribute("listGroups",groups);

        return "SearchPage";
    }
}
