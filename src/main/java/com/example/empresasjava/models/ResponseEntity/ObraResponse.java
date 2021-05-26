package com.example.empresasjava.models.ResponseEntity;

import com.example.empresasjava.models.Obra;
import com.google.common.base.Objects;

public class ObraResponse {

    private Integer prazo;
    private Integer valor_total;
    private Integer bonus_entrega;
    private Integer tempo_limite;
    private UserResponse user;

    public ObraResponse() {
    }

    public ObraResponse(Integer prazo, Integer valor_total, Integer bonus_entrega,
                        Integer tempo_limite, UserResponse user) {
        this.prazo = prazo;
        this.valor_total = valor_total;
        this.bonus_entrega = bonus_entrega;
        this.tempo_limite = tempo_limite;
        this.user = user;
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

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public static ObraResponse to_response(Obra obra){

        return new ObraResponse(
            obra.getPrazo(),
            obra.getValor_total(),
            obra.getBonus_entrega(),
            obra.getTempo_limite(),
            UserResponse.toResponse(obra.getUser())
        );
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObraResponse that = (ObraResponse) o;
        return Objects.equal(prazo, that.prazo) && Objects.equal(valor_total, that.valor_total) && Objects.equal(bonus_entrega, that.bonus_entrega) && Objects.equal(tempo_limite, that.tempo_limite) && Objects.equal(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(prazo, valor_total, bonus_entrega, tempo_limite, user);
    }
}
