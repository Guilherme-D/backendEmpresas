package com.example.empresasjava.models.RequestEntity;

import com.example.empresasjava.models.Equipe;
import com.example.empresasjava.models.Obra;
import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;

public class EquipeRequest {

    @NotNull(message = "Campo valor_total não pode ser nulo")
    private Integer obra_id;
    @NotNull(message = "Campo quantidade_funcionarios não pode ser nulo")
    private Integer quantidade_funcionarios;

    public EquipeRequest() {
    }

    public EquipeRequest(Integer obra_id, Integer quantidade_funcionarios) {
        this.obra_id = obra_id;
        this.quantidade_funcionarios = quantidade_funcionarios;
    }

    public static Equipe to_equipe(EquipeRequest request, Obra obraById) {
        return new Equipe(
                request.getQuantidade_funcionarios(),
                obraById
        );
    }

    public Integer getObra_id() {
        return obra_id;
    }

    public void setObra_id(Integer obra_id) {
        this.obra_id = obra_id;
    }

    public Integer getQuantidade_funcionarios() {
        return quantidade_funcionarios;
    }

    public void setQuantidade_funcionarios(Integer quantidade_funcionarios) {
        this.quantidade_funcionarios = quantidade_funcionarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipeRequest that = (EquipeRequest) o;
        return Objects.equal(obra_id, that.obra_id) && Objects.equal(quantidade_funcionarios, that.quantidade_funcionarios);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(obra_id, quantidade_funcionarios);
    }
}
