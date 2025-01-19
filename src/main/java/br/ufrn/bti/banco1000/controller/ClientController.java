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
    public String withdraw(Client clienteLogado) {
        return clientService.withdraw(clienteLogado);
    }
    // public String transferir(Client clienteLogado, List<Account> contas) {
    //     return clientService.transferir(clienteLogado,contas);
    // }
    public String getBalance(Client clienteLogado) {
        return clientService.getBalance(clienteLogado);
    }
    // public String verExtrato(Client clienteLogado) {
    //     return clientService.verExtrato(clienteLogado);
    // }


    
}
