package com.example.empresasjava.models.RequestEntity;

import com.example.empresasjava.models.User;
import com.google.common.base.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserRequest {

    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
    private String name;
    @NotNull(message = "Campo email não pode ser nulo")
    @NotEmpty(message = "Campo email não pode ser vazio")
    @Email(message = "Digite um email válido")
    private String email;
    @NotNull(message = "Campo password não pode ser nulo")
    @NotEmpty(message = "Campo password não pode ser vazio")
    private String password;
    @NotNull(message = "Campo is_admin não pode ser nulo")
    private Boolean is_admin;


    public static UserRequest toResponse(User user) {
        return null;
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

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    public static User toUser(UserRequest user) {
        return new User(user.name,
                        user.email,
                        user.password,
                        user.is_admin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequest that = (UserRequest) o;
        return Objects.equal(name, that.name) && Objects.equal(email, that.email) && Objects.equal(password, that.password) && Objects.equal(is_admin, that.is_admin);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, email, password, is_admin);
    }
}
