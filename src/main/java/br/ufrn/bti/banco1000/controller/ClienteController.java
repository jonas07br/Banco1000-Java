package br.ufrn.bti.banco1000.controller;

import java.util.List;

import br.ufrn.bti.banco1000.model.Cliente;
import br.ufrn.bti.banco1000.model.Conta;
import br.ufrn.bti.banco1000.service.ClienteService;

public class ClienteController {

    private ClienteService clienteService;
    public ClienteController(){
        this.clienteService = new ClienteService();
    }

    public Cliente criarCliente(){
        System.out.println("Criando cliente");
        return clienteService.criarCliente();
    }
    // public Cliente atualizarCliente(Cliente cliente){
    //     System.out.println("Atualizando cliente");
    //     return clienteService.atualizarCliente(cliente);
    // }

    //funcoes enquanto logado
    public String depositar(Cliente clienteLogado) {
        return clienteService.depositar(clienteLogado);
    }
    public String sacar(Cliente clienteLogado) {
        return clienteService.sacar(clienteLogado);
    }
    public String transferir(Cliente clienteLogado, List<Conta> contas) {
        return clienteService.transferir(clienteLogado,contas);
    }
    public String verSaldo(Cliente clienteLogado) {
        return clienteService.verSaldo(clienteLogado);
    }
    public String verExtrato(Cliente clienteLogado) {
        return clienteService.verExtrato(clienteLogado);
    }


    
}
