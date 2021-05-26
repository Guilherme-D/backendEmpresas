package com.example.empresasjava.models.ResponseEntity;

import com.example.empresasjava.models.User;
import com.google.common.base.Objects;

public class UserResponse {
    private Integer id;
    private String name;
    private String email;
    private Boolean is_admin;

    public UserResponse(Integer id, String name, String email, Boolean is_admin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.is_admin = is_admin;
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

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    public static UserResponse toResponse(User user){
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getIsAdmin());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return Objects.equal(id, that.id) && Objects.equal(name, that.name) && Objects.equal(email, that.email) && Objects.equal(is_admin, that.is_admin);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, email, is_admin);
    }


}
