package com.example.empresasjava.models;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
    private String name;
    @NotNull(message = "Campo email não pode ser nulo")
    @NotEmpty(message = "Campo email não pode ser vazio")
    private String email;
    @NotNull(message = "Campo password não pode ser nulo")
    @NotEmpty(message = "Campo password não pode ser vazio")
    private String password;
    private Boolean isAdmin;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

    public User(){}
    public User(String name,
                @NotNull(message = "Campo data não pode ser nulo")
                @NotEmpty(message = "Campo data não pode ser vazio")
                String email,
                @NotNull(message = "Campo data não pode ser nulo")
                @NotEmpty(message = "Campo data não pode ser vazio")
                String password,
                Boolean is_admin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = is_admin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equal(id, user.id) && Objects.equal(name, user.name) && Objects.equal(email, user.email) && Objects.equal(password, user.password) && Objects.equal(isAdmin, user.isAdmin) && Objects.equal(createdAt, user.createdAt) && Objects.equal(deletedAt, user.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, email, password, isAdmin, createdAt, deletedAt);
    }
}
