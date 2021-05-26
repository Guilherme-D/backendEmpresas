package com.example.empresasjava.models.RequestEntity;

import com.example.empresasjava.models.Obra;
import com.example.empresasjava.models.User;
import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;

public class ObraRequest {

    @NotNull(message = "Campo prazo não pode ser nulo")
    private Integer prazo;
    @NotNull(message = "Campo valor_total não pode ser nulo")
    private Integer valor_total;
    @NotNull(message = "Campo bonus_entrega não pode ser nulo")
    private Integer bonus_entrega;
    @NotNull(message = "Campo tempo_limite não pode ser nulo")
    private Integer tempo_limite;

    public ObraRequest() {
    }

    public ObraRequest(Integer prazo, Integer valor_total, Integer bonus_entrega, Integer tempo_limite) {
        this.prazo = prazo;
        this.valor_total = valor_total;
        this.bonus_entrega = bonus_entrega;
        this.tempo_limite = tempo_limite;
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

    public static Obra to_Obra(ObraRequest response, User user){
        return new Obra(response.getPrazo(),
                response.getValor_total(),
                response.getBonus_entrega(),
                response.tempo_limite,
                user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObraRequest that = (ObraRequest) o;
        return Objects.equal(prazo, that.prazo) && Objects.equal(valor_total, that.valor_total) && Objects.equal(bonus_entrega, that.bonus_entrega) && Objects.equal(tempo_limite, that.tempo_limite);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(prazo, valor_total, bonus_entrega, tempo_limite);
    }
}
