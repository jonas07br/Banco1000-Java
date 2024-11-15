package br.ufrn.bti.banco1000.controller;

import br.ufrn.bti.banco1000.model.Cliente;
import br.ufrn.bti.banco1000.model.Conta;
import br.ufrn.bti.banco1000.service.ContaService;

public class ContaController {
    private ContaService contaService;

    public ContaController(){
        this.contaService = new ContaService();
    }
    
    public Conta criarConta(Cliente cliente){
        System.out.println("Criando conta");
        return contaService.criarConta(cliente);
    }
    
    
}
