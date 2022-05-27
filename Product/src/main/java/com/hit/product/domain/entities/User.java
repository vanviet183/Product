package com.hit.product.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hit.product.domain.entities.base.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "users", uniqueConstraints = {
//        @UniqueConstraint(columnNames = "username"),
//        @UniqueConstraint(columnNames = "email")
//})
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Nationalized
    private String firstName;

    @Nationalized
    private String lastName;

    @Nationalized
    @Column(nullable = false)
    private String address;

//    @NotBlank
//    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(max = 120)
    @JsonIgnore
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Column(nullable = false)
    private String phone;

    private String avatar;

    private String birthday;

    private String gender;

    private Double scores;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
