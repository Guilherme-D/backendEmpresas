package com.example.empresasjava.models.RequestEntity;

import com.example.empresasjava.models.Bonus;
import com.example.empresasjava.models.Obra;
import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;

public class BonusRequest {

    @NotNull(message = "Campo valor_total não pode ser nulo")
    private Integer obra_id;
    @NotNull(message = "Campo valor_total não pode ser nulo")
    private Integer dias;
    @NotNull(message = "Campo bonus_entrega não pode ser nulo")
    private Integer valor;

    public BonusRequest() {
    }

    public BonusRequest(Integer obra_id, Integer dias, Integer valor) {
        this.obra_id = obra_id;
        this.dias = dias;
        this.valor = valor;
    }

    public Integer getObra_id() {
        return obra_id;
    }

    public void setObra_id(Integer obra_id) {
        this.obra_id = obra_id;
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

    public static Bonus to_bonus(BonusRequest request, Obra obra){
        return new Bonus(
                obra,
                request.getDias(),
                request.getValor()
        );
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusRequest that = (BonusRequest) o;
        return Objects.equal(obra_id, that.obra_id) && Objects.equal(dias, that.dias) && Objects.equal(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(obra_id, dias, valor);
    }
}
