/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufrn.bti.banco1000.model;

import java.util.Date;

/**
 *
 * @author vinicius
 */
public class Transaction {
    
    private Date data;
    private String tipo;
    private Client cliente;
    private String descricao;
    private double valor;
    
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public Transaction(String tipo, Client cliente, String descricao, double valor) {
        this.data = new Date();
        this.tipo = tipo;
        this.cliente = cliente;
        this.descricao = descricao;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Movimentacao [data=" + data + ", tipo=" + tipo + ", cliente=" + cliente.getNome() + ", descricao=" + descricao
                + ", valor=" + valor + "]";
    }
}
