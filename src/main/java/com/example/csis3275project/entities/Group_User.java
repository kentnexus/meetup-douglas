package com.example.csis3275project.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Group_User {
    @SequenceGenerator(

            name = "groupuser_sequence",
            sequenceName = "groupuser_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "groupuser_sequence"
    )
    private long group_user_id;
    private boolean isOwner;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups group;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account account;
//
//    public Group_User(long grpId, long usrId, boolean b) {
//        this.group.setGroup_id(grpId);
//        this.account.setUser_id(usrId);
//        this.isOwner = b;
//    }

    public long getGroup_user_id() {
        return group_user_id;
    }

    public void setGroup_user_id(long group_user_id) {
        this.group_user_id = group_user_id;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
