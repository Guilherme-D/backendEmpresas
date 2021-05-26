package com.example.empresasjava.models.ResponseEntity;

import com.example.empresasjava.models.Bonus;
import com.example.empresasjava.models.Obra;
import com.google.common.base.Objects;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

public class BonusResponse {

    private Integer dias;
    private Integer valor;
    private ObraResponse obra;

    public BonusResponse() {
    }

    public BonusResponse(Integer dias, Integer valor, ObraResponse obra) {
        this.dias = dias;
        this.valor = valor;
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

    public ObraResponse getObra() {
        return obra;
    }

    public void setObra(ObraResponse obra) {
        this.obra = obra;
    }


    public static BonusResponse to_response(Bonus bonus){
        return new BonusResponse(
                bonus.getDias(),
                bonus.getValor(),
                ObraResponse.to_response(bonus.getObra())
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusResponse that = (BonusResponse) o;
        return Objects.equal(dias, that.dias) && Objects.equal(valor, that.valor) && Objects.equal(obra, that.obra);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dias, valor, obra);
    }
}
