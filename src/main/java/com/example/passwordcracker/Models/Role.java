package com.example.passwordcracker.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    List<User> users;
}
