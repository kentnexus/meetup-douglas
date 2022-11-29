package com.example.csis3275project.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Groups {
    @SequenceGenerator(

            name = "group_sequence",
            sequenceName = "group_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "group_sequence"
    )
    private long group_id;
    private String name;
    private String description;

    public Groups(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
