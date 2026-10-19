package com.example.tuto_spring_security.role;

import com.example.tuto_spring_security.common.BaseEntity;
import com.example.tuto_spring_security.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "ROLES")
public class Role extends BaseEntity {
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
