package com.example.empresasjava.models.ResponseEntity;

import com.example.empresasjava.models.Bonus;
import com.example.empresasjava.models.Equipe;
import com.google.common.base.Objects;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

public class EquipeResponse {

    private Integer quantidade_funcionarios;
    private ObraResponse obra;

    public EquipeResponse() {
    }

    public EquipeResponse(Integer quantidade_funcionarios, ObraResponse obra) {
        this.quantidade_funcionarios = quantidade_funcionarios;
        this.obra = obra;
    }

    public static EquipeResponse to_response(Equipe equipeRequest) {
        return new EquipeResponse(
                equipeRequest.getQuantidade_funcionarios(),
                ObraResponse.to_response(equipeRequest.getObra())
        );
    }

    public Integer getQuantidade_funcionarios() {
        return quantidade_funcionarios;
    }

    public void setQuantidade_funcionarios(Integer quantidade_funcionarios) {
        this.quantidade_funcionarios = quantidade_funcionarios;
    }

    public ObraResponse getObra() {
        return obra;
    }

    public void setObra(ObraResponse obra) {
        this.obra = obra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipeResponse that = (EquipeResponse) o;
        return Objects.equal(quantidade_funcionarios, that.quantidade_funcionarios) && Objects.equal(obra, that.obra);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(quantidade_funcionarios, obra);
    }
}
