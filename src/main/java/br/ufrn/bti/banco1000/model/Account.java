/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufrn.bti.banco1000.model;

import br.ufrn.bti.banco1000.model.enumerations.AccountType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class Account {
    private Long clientId;
    private int agency;
    private Long accountNumber;
    private AccountType accountType;
    private int password;
    private BigDecimal balance;
    

    public AccountType getTipo() {
        return accountType;
    }
    
    public Account(Long clientId, AccountType accountType, int password) {

        this.clientId=clientId;
        this.agency = 1000;
        this.accountNumber = Math.abs(new Random().nextLong()%10000)+1;
        this.accountType = accountType;
        this.password = password;
        this.balance = BigDecimal.ZERO;
    }
    
    public Account(int agency, Long accountNumber, Long clientId, AccountType accountType, int password,
            BigDecimal balance) {
        this.agency = agency;
        this.accountNumber = accountNumber;
        this.clientId = clientId;
        this.accountType = accountType;
        this.password = password;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o){
        return false;
    }
    

    
    public void setTipoConta(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return 
        agency +","+ 
        accountNumber +","+
        clientId +","+
        accountType +","+
        password +","+
        balance;
    }
    
    public static String[] getHeaders(){
        return new String[]{"agency","accountNumber","clientId","accountType","password","balance"};
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public int getAgency() {
        return agency;
    }

    public void setAgency(int agency) {
        this.agency = agency;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    
}
