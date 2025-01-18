/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufrn.bti.banco1000.model;

import br.ufrn.bti.banco1000.model.enumerations.AccountType;

import java.util.ArrayList;

public class Account {
    private Client cliente;
    private int agencia;
    private int numeroConta;
    private AccountType tipo;

    public AccountType getTipo() {
        return tipo;
    }


    private int senha;
    private double saldo;
    private ArrayList<Transaction> transaction = new ArrayList();

    
    
    public Account(Client cliente, AccountType tipo, int senha) {
        this.cliente=cliente;
        this.agencia = 1000;
        this.numeroConta = (int) (Math.random() * 1000);
        this.tipo = tipo;
        this.senha = senha;
        this.saldo = 0;
        this.transaction = new ArrayList();
    }
    
    
    public String depositar(double valor) {
        this.saldo = this.saldo + valor;
        this.transaction.add(new Transaction("DEPOSITO", this.cliente, "DEPOSITO", valor));
        return "Deposito realizado com sucesso";
    }
    
    public String sacar(double valor) {
        if (this.saldo - valor >= 0) {
            this.saldo = this.saldo - valor;
            this.transaction.add(new Transaction("SAQUE", this.cliente, "SAQUE", valor));
            return "Saque realizado com sucesso";
        }
        return "Saldo insuficiente";
    }
    
    public String transferir(Account conta, double valor) {
        if (this.saldo - valor >= 0 ) {
            this.sacar(valor);
            this.transaction.remove(this.transaction.size()-1);
            
            conta.depositar(valor);
            conta.transaction.remove(conta.transaction.size()-1);
            
            conta.transaction.add(new Transaction("TRANSFERENCIA", this.cliente, 
            "ENTRADA POR TRANSFERENCIA", valor));
            this.transaction.add(new Transaction("TRANSFERENCIA", this.cliente, 
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
    
    
    
    
    public void setTipoConta(AccountType tipo) {
        this.tipo = tipo;
    }
    
    
    @Override
    public String toString() {
        return "Conta [cliente=" + cliente.getNome() + ", agencia=" + agencia + ", numeroConta=" + numeroConta + ", tipo=" + tipo
        + ", senha=" + senha + ", saldo=" + saldo + ", transaction=" + transaction + "]";
    }
    
    public ArrayList<Transaction> getTransaction() {
        return transaction;
    }
    
    
    public void setTransaction(ArrayList<Transaction> transaction) {
        this.transaction = transaction;
    }
}
