package br.ufrn.bti.banco1000.controller;

import br.ufrn.bti.banco1000.model.Client;
import br.ufrn.bti.banco1000.model.Account;
import br.ufrn.bti.banco1000.service.AccountService;

public class AccountController {
    private AccountService contaService;

    public AccountController(){
        this.contaService = new AccountService();
    }
    
    public Account createAccount(Client cliente){
        System.out.println("Criando conta");
        return contaService.createAccount(cliente);
    }
    
    
}
