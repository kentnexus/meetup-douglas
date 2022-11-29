package com.example.csis3275project.web;

import com.example.csis3275project.entities.Account;
import com.example.csis3275project.entities.EventGroupUser;
import com.example.csis3275project.entities.Group_User;
import com.example.csis3275project.entities.Groups;
import com.example.csis3275project.repositories.EventGroupUserRepository;
import com.example.csis3275project.repositories.GroupUserRepository;
import com.example.csis3275project.repositories.GroupsRepository;
import jdk.jfr.Event;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
public class GroupController {

    @Autowired
    GroupsRepository groupsRepository;
    GroupUserRepository groupUserRepository;
    EventGroupUserRepository eventGroupUserRepository;

    @GetMapping("/group/{groupName}/group")
    public String groupPage(Model model, @PathVariable String groupName, Account account, long id){

//        group details
        Groups group = groupsRepository.findById(id).orElseThrow();
//        members and size
        List<Group_User> allGroups = groupUserRepository.findAll();
        List<Group_User> users = new ArrayList<>();
        Group_User admin = new Group_User();

        for(Group_User gu: allGroups) {
            if (gu.getGroup().getGroup_id() == id)
                users.add(gu);
            if (gu.isOwner() && gu.getGroup().getGroup_id() == id)
                admin = gu;
        }

//        user info
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        account = (Account) auth.getPrincipal();
        boolean isMember = false;

        for(Group_User gu: allGroups) {
            if (gu.getGroup().getGroup_id() == id && Objects.equals(gu.getAccount().getUser_id(), account.getUser_id())) {
                isMember = true;
                break;
            }
        }

//        events
        List<EventGroupUser> eventGroupUserList = eventGroupUserRepository.findAll();
        List<EventGroupUser> pastEventsList = new ArrayList<>();
        List<EventGroupUser> futureEventsList = new ArrayList<>();

        for(EventGroupUser egu:eventGroupUserList) {
            int res = egu.getEvent().getSchedule().compareTo(LocalDate.now());
            if (res>=0 && egu.getGroup().getGroup_id()==id && egu.isOrganizer())
                futureEventsList.add(egu);
            else if(res<0 && egu.getGroup().getGroup_id()==id && egu.isOrganizer())
                pastEventsList.add(egu);
        }

        model.addAttribute("grp",group);
        model.addAttribute("groupSize",users.size());
        model.addAttribute("admin",admin);
        model.addAttribute("isMember",isMember);
        model.addAttribute("members",users);
        model.addAttribute("pastEvents",pastEventsList);
        model.addAttribute("futureEvents",futureEventsList);

        return "GroupPage";
    }

    @GetMapping("/group/create")
    public String createGroup(Model model){

        model.addAttribute("group", new Groups());
        return "Create_GroupPage";
    }

    @PostMapping("/group/save")
    public String saveGroup(Account account, Groups group, Group_User group_user, HttpServletRequest request){
        long idd = 0;

        try {
            idd = Long.parseLong(request.getParameter("id"));
            Groups ngr = groupsRepository.findById(idd).orElseThrow();
            String newName = request.getParameter("name");
            String newDescription = request.getParameter("description");
            if(!newName.isEmpty())
                ngr.setName(newName);
            if(!newDescription.isEmpty())
                ngr.setDescription(newDescription);
            groupsRepository.save(ngr);
        } catch (Exception e){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            account = (Account) auth.getPrincipal();
            groupsRepository.save(group);
            group_user.setAccount(account);
            group_user.setGroup(group);
            group_user.setOwner(true);
            groupUserRepository.save(group_user);
        }

        return "redirect:/group/manage";
    }

    @GetMapping("/group/manage")
    public String manageGroup(Model model, Account account, Long id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        account = (Account) auth.getPrincipal();
        List<Group_User> groupUserList = groupUserRepository.findAll();
        List<Group_User> newGroupList = new ArrayList<>();

        for(Group_User grp: groupUserList){
            if(grp.getAccount().getUser_id()== account.getUser_id())
                newGroupList.add(grp);
        }

        model.addAttribute("listGroups",newGroupList);
        model.addAttribute("grp", new Groups());

        return "ManageGroup";
    }

    @GetMapping("/group/delete")
    public String deleteGroup(long id){
        List<Group_User> groupUserList = groupUserRepository.findAll();

        List<EventGroupUser> events = eventGroupUserRepository.findAll();
        for(EventGroupUser event: events){
            if(event.getGroup().getGroup_id()==id)
                eventGroupUserRepository.delete(event);
        }

        for(Group_User g: groupUserList){
            if(g.getGroup().getGroup_id()==id){
                groupUserRepository.delete(g);
            }
        }
        groupsRepository.deleteById(id);


        return "redirect:/group/manage";
    }

    @PostMapping("/group/join")
    public String joinGroup(Account account, long id, Group_User group_user){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        account = (Account) auth.getPrincipal();
        Groups group = groupsRepository.findById(id).orElseThrow();

        group_user.setOwner(false);
        group_user.setAccount(account);
        group_user.setGroup(group);
        groupUserRepository.save(group_user);

        String url = "redirect:/group/"+group.getName()+"/group?id="+id;

        return url;
    }

    @GetMapping("/group/leave")
    public String leaveGroup(long id, Account account){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        account = (Account) auth.getPrincipal();
        List<Group_User> groupUserList = groupUserRepository.findAll();
        for(Group_User g: groupUserList){
            System.out.print(g.getGroup().getGroup_id()+" "+g.getAccount().getUser_id());
            if(g.getGroup().getGroup_id()==id && g.getAccount().getUser_id()== account.getUser_id()){
                groupUserRepository.delete(g);
            }
        }

        return "redirect:/group/manage";
    }
}
