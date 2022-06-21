package ru.mediatel.task.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
@Component
@Entity
@Table(name = "edge")
@Data
@NoArgsConstructor
public class Edge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_user_id")
    private Integer firstUser; // the "from" vertex
    @Column(name = "second_user_id")
    private Integer secondUser; // the "to" vertex

    public Edge(Integer firstUser, Integer secondUser) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }

    public Edge reversed() {
        return new Edge(secondUser, firstUser);
    }

    @Override
    public String toString() {
        return firstUser + " -> " + secondUser;
    }

}