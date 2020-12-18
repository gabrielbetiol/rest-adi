package com.curso.osworks.api.model;

import com.curso.osworks.domain.model.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OrdemServicoInput {

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal preco;

    @Valid
    @NotNull
    private ClienteIdInput cliente;

    @JsonProperty("cliente")
    private void unpackNested(Long  cliente) {
        this.cliente = new ClienteIdInput();
        this.cliente.setId(cliente);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public ClienteIdInput getCliente() {
        return cliente;
    }

    public void setCliente(ClienteIdInput cliente) {
        this.cliente = cliente;
    }
}
