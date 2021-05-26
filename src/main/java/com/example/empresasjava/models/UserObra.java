package com.example.empresasjava.models;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_obras")
public class UserObra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Campo obra_id não pode ser nulo")
    private Integer obra_id;
    @NotNull(message = "Campo user_id não pode ser nulo")
    private Integer user_id;

    public UserObra() {
    }

    public UserObra(Integer obra_id, Integer user_id) {
        this.obra_id = obra_id;
        this.user_id = user_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getObra_id() {
        return obra_id;
    }

    public void setObra_id(Integer obra_id) {
        this.obra_id = obra_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserObra userObra = (UserObra) o;
        return Objects.equal(id, userObra.id) && Objects.equal(obra_id, userObra.obra_id) && Objects.equal(user_id, userObra.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, obra_id, user_id);
    }
}
