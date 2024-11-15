/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufrn.bti.banco1000.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinicius
 */
public class Cliente {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private List<Conta> contas;
    private String senha;
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cliente(String nome, String cpf, String email, String telefone, String senha) {
       this.nome = nome;
       this.cpf = cpf;
       this.email = email;
       this. telefone = telefone;
       this.contas = new ArrayList<>();
       this.senha= senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Conta> getContas() {
        return this.contas;
    }

    public void setContas(Conta conta) {
        this.contas.add(conta);
    }

    @Override
    public String toString() {
        return "Cliente [nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", telefone=" + telefone + ", contas="
                + contas + "]";
    }

    
    
}
