package br.ufrn.bti.banco1000.controller;

import java.util.List;

import br.ufrn.bti.banco1000.model.Client;
import br.ufrn.bti.banco1000.model.Account;
import br.ufrn.bti.banco1000.service.ClientService;

public class ClientController {

    private ClientService clientService;
    public ClientController(){
        this.clientService = new ClientService();
    }

    public Client login(){
        return clientService.login();
    }
    public Client createClient(){
        System.out.println("Criando cliente");
        return clientService.createClient();
    }
    // public Client atualizarClient(Client cliente){
    //     System.out.println("Atualizando cliente");
    //     return clientService.atualizarClient(cliente);
    // }

    // funcoes enquanto logado
    public String deposit(Client loggedClient) {
        return clientService.deposit(loggedClient);
    }
    public String withdraw(Client loggedClient) {
        return clientService.withdraw(loggedClient);
    }
    public String transfer(Client loggedClient) {
        return clientService.transfer(loggedClient);
    }
    public String getBalance(Client loggedClient) {
        return clientService.getBalance(loggedClient);
    }
    // public String verExtrato(Client loggedClient) {
    //     return clientService.verExtrato(loggedClient);
    // }


    
}
