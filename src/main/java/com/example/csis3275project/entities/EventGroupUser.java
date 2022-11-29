package com.example.csis3275project.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class EventGroupUser {
    @SequenceGenerator(

            name = "eventGroupUser_sequence",
            sequenceName = "eventGroupUser_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "eventGroupUser_sequence"
    )
    private long eventGroupUser_id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Events event;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Groups group;

    private boolean isOrganizer;

    public long getEventGroupUser_id() {
        return eventGroupUser_id;
    }

    public void setEventGroupUser_id(long eventGroupUser_id) {
        this.eventGroupUser_id = eventGroupUser_id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public boolean isOrganizer() {
        return isOrganizer;
    }

    public void setOrganizer(boolean organizer) {
        isOrganizer = organizer;
    }
}
