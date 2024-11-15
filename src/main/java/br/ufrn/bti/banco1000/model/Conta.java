/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufrn.bti.banco1000.model;

import java.util.ArrayList;

public class Conta {
    private Cliente cliente;
    private int agencia;
    private int numeroConta;
    private TipoConta tipo;

    public TipoConta getTipo() {
        return tipo;
    }


    private int senha;
    private double saldo;
    private ArrayList<Movimentacao> movimentacao = new ArrayList();

    
    
    public Conta(Cliente cliente, TipoConta tipo, int senha) {
        this.cliente=cliente;
        this.agencia = 1000;
        this.numeroConta = (int) (Math.random() * 1000);
        this.tipo = tipo;
        this.senha = senha;
        this.saldo = 0;
        this.movimentacao = new ArrayList();
    }
    
    
    public String depositar(double valor) {
        this.saldo = this.saldo + valor;
        this.movimentacao.add(new Movimentacao("DEPOSITO", this.cliente, "DEPOSITO", valor));
        return "Deposito realizado com sucesso";
    }
    
    public String sacar(double valor) {
        if (this.saldo - valor >= 0) {
            this.saldo = this.saldo - valor;
            this.movimentacao.add(new Movimentacao("SAQUE", this.cliente, "SAQUE", valor));
            return "Saque realizado com sucesso";
        }
        return "Saldo insuficiente";
    }
    
    public String transferir(Conta conta, double valor) {
        if (this.saldo - valor >= 0 ) {
            this.sacar(valor);
            this.movimentacao.remove(this.movimentacao.size()-1);
            
            conta.depositar(valor);
            conta.movimentacao.remove(conta.movimentacao.size()-1);
            
            conta.movimentacao.add(new Movimentacao("TRANSFERENCIA", this.cliente, 
            "ENTRADA POR TRANSFERENCIA", valor));
            this.movimentacao.add(new Movimentacao("TRANSFERENCIA", this.cliente, 
            "SAIDA POR TRANSFERENCIA", valor));
            
            return "Transferencia realizada com sucesso";
        }
        else{
            return "Saldo insuficiente";
        }
    }
    
    public String getNome() {
        return this.cliente.getNome();
    }    
    
    public double getSaldo() {
        return this.saldo;
    }
    
    public int getNumConta(){
        return this.numeroConta;
    }
    
    @Override
    public boolean equals(Object o){
        return false;
    }
    
    public boolean notNull(){
        if(this.cliente != null && this.agencia != 0 && this.numeroConta != 0 && this.tipo != null && this.senha != 0 && this.saldo != 0){
            return true;
        }
        return false;
    }
    
    
    
    
    public void setTipoConta(TipoConta tipo) {
        this.tipo = tipo;
    }
    
    
    @Override
    public String toString() {
        return "Conta [cliente=" + cliente.getNome() + ", agencia=" + agencia + ", numeroConta=" + numeroConta + ", tipo=" + tipo
        + ", senha=" + senha + ", saldo=" + saldo + ", movimentacao=" + movimentacao + "]";
    }
    
    public ArrayList<Movimentacao> getMovimentacao() {
        return movimentacao;
    }
    
    
    public void setMovimentacao(ArrayList<Movimentacao> movimentacao) {
        this.movimentacao = movimentacao;
    }
}
