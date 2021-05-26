package com.example.empresasjava.models;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "equipes")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Campo quantidade_funcionarios não pode ser nulo")
    private Integer quantidade_funcionarios;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "obras_id" )
    private Obra obra;


    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

    public Equipe() {
    }

    public Equipe(Integer quantidade_funcionarios, Obra obra) {
        this.quantidade_funcionarios = quantidade_funcionarios;
        this.obra = obra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade_funcionarios() {
        return quantidade_funcionarios;
    }

    public void setQuantidade_funcionarios(Integer quantidade_funcionarios) {
        this.quantidade_funcionarios = quantidade_funcionarios;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
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
        Equipe equipe = (Equipe) o;
        return Objects.equal(id, equipe.id) && Objects.equal(quantidade_funcionarios, equipe.quantidade_funcionarios) && Objects.equal(obra, equipe.obra) && Objects.equal(createdAt, equipe.createdAt) && Objects.equal(deletedAt, equipe.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, quantidade_funcionarios, obra, createdAt, deletedAt);
    }
}
