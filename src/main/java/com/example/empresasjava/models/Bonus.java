package com.example.empresasjava.models;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "bonus")
public class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Campo valor_total não pode ser nulo")
    private Integer dias;
    @NotNull(message = "Campo bonus_entrega não pode ser nulo")
    private Integer valor;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "obras_id" )
    private Obra obra;


    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

    public Bonus() {
    }

    public Bonus(Obra obra, Integer dias, Integer valor) {
        this.obra = obra;
        this.dias = dias;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
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
        Bonus bonus = (Bonus) o;
        return Objects.equal(id, bonus.id) && Objects.equal(obra, bonus.obra) && Objects.equal(dias, bonus.dias) && Objects.equal(valor, bonus.valor) && Objects.equal(createdAt, bonus.createdAt) && Objects.equal(deletedAt, bonus.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, obra, dias, valor, createdAt, deletedAt);
    }
}
