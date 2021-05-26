package com.example.empresasjava.models;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "obras")
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Campo prazo não pode ser nulo")
    private Integer prazo;
    @NotNull(message = "Campo valor_total não pode ser nulo")
    private Integer valor_total;
    @NotNull(message = "Campo bonus_entrega não pode ser nulo")
    private Integer bonus_entrega;
    @NotNull(message = "Campo tempo_limite não pode ser nulo")
    private Integer tempo_limite;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id" )
    private User user;

    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

    public Obra() {
    }

    public Obra(Integer prazo, Integer valor_total, Integer bonus_entrega,
                Integer tempo_limite, User user) {
        this.prazo = prazo;
        this.valor_total = valor_total;
        this.bonus_entrega = bonus_entrega;
        this.tempo_limite = tempo_limite;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrazo() {
        return prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }

    public Integer getValor_total() {
        return valor_total;
    }

    public void setValor_total(Integer valor_total) {
        this.valor_total = valor_total;
    }

    public Integer getBonus_entrega() {
        return bonus_entrega;
    }

    public void setBonus_entrega(Integer bonus_entrega) {
        this.bonus_entrega = bonus_entrega;
    }

    public Integer getTempo_limite() {
        return tempo_limite;
    }

    public void setTempo_limite(Integer tempo_limite) {
        this.tempo_limite = tempo_limite;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        Obra obra = (Obra) o;
        return Objects.equal(id, obra.id) && Objects.equal(prazo, obra.prazo) && Objects.equal(valor_total, obra.valor_total) && Objects.equal(bonus_entrega, obra.bonus_entrega) && Objects.equal(tempo_limite, obra.tempo_limite) && Objects.equal(createdAt, obra.createdAt) && Objects.equal(deletedAt, obra.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, prazo, valor_total, bonus_entrega, tempo_limite, createdAt, deletedAt);
    }
}
