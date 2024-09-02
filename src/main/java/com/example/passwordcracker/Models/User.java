package com.example.passwordcracker.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Email(message = "You need to have atleast one '@' and one '.'")
    private String userName;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$",
            message = "Password need to have at least one upper letter and one number")
    @Size(min = 6, max = 1000, message = "Password need to be minimum 6 characters and maximum 1000 characther")
    private String password;

    @Size(min = 1, max = 1000, message = "Your name is to short or to long you need minimum 1 character and maximum 1000")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Your name can only contains letters")
    private String firstname;

    @Size(min = 1, max = 1000, message = "Your name is to short or to long you need minimum 1 character and maximum 1000")
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String lastname;

    @Size(min = 10, max = 10, message = "Phone number need to be 10 numbers")
    @Pattern(regexp = "^[\\d-]+$", message = "You can only write numbers here")
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private Set<Role> role;

}
