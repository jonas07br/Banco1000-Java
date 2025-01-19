package br.ufrn.bti.banco1000.controller;

import java.util.List;

import br.ufrn.bti.banco1000.model.Client;
import br.ufrn.bti.banco1000.model.Account;
import br.ufrn.bti.banco1000.service.ClientService;

public class ClientController {

    private ClientService clienteService;
    public ClientController(){
        this.clienteService = new ClientService();
    }

    public Client createClient(){
        System.out.println("Criando cliente");
        return clienteService.createClient();
    }
    // public Client atualizarClient(Client cliente){
    //     System.out.println("Atualizando cliente");
    //     return clienteService.atualizarClient(cliente);
    // }

    //funcoes enquanto logado
    // public String depositar(Client clienteLogado) {
    //     return clienteService.depositar(clienteLogado);
    // }
    // public String sacar(Client clienteLogado) {
    //     return clienteService.sacar(clienteLogado);
    // }
    // public String transferir(Client clienteLogado, List<Account> contas) {
    //     return clienteService.transferir(clienteLogado,contas);
    // }
    // public String verSaldo(Client clienteLogado) {
    //     return clienteService.verSaldo(clienteLogado);
    // }
    // public String verExtrato(Client clienteLogado) {
    //     return clienteService.verExtrato(clienteLogado);
    // }


    
}
