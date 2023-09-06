package com.ssafy.chartflow.emblem.entity;

import com.ssafy.chartflow.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "emblem")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString()
public class Emblem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long emblemId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "emblem")
    private List<UserEmblem> userEmblems = new ArrayList<>();
}
