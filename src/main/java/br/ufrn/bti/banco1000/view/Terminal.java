package br.ufrn.bti.banco1000.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ufrn.bti.banco1000.controller.ClientController;
import br.ufrn.bti.banco1000.controller.AccountController;
import br.ufrn.bti.banco1000.model.Client;
import br.ufrn.bti.banco1000.model.Account;
import br.ufrn.bti.banco1000.model.enumerations.AccountType;
import br.ufrn.bti.banco1000.repository.AccountRepository;
import br.ufrn.bti.banco1000.repository.ClientRepository;
import br.ufrn.bti.banco1000.service.ClientService;
import br.ufrn.bti.banco1000.service.AccountService;

public class Terminal {

    // private ClientService clienteService;
    // private AccountService contaService;
    private ClientController clientController;
    private AccountController accountController;

    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    public Client clienteLogado;

    //opcoes
    public String opcoesSemLogin = 
        "1 - Criar conta\n"+
        "2 - Logar\n"+
        "3 - Sair";
    public String opcoesComLogin = 
        "1 - Depositar\n"+
        "2 - Sacar\n"+
        "3 - Transferir\n"+
        "4 - Ver saldo\n"+
        "5 - Ver extrato\n"+
        "6 - Sair";

    public Terminal(){
        this.clientController = new ClientController();
        this.accountController = new AccountController();

        this.clientRepository = new ClientRepository();
        this.accountRepository = new AccountRepository();

        this.clienteLogado = null;

    }
    public void start(){
        Scanner scan = new Scanner(System.in);
        while(true){
            if(clienteLogado != null){
                System.out.println(opcoesComLogin);
                int op = scan.nextInt();
                if(op == 1){
                    System.out.println(this.clientController.deposit(clienteLogado));
                }
                 else if(op == 2){
                    System.out.println(this.clientController.withdraw(clienteLogado));
                 }    
                // } else if(op == 3){
                //     System.out.println(this.clienteController.transferir(clienteLogado,contasBd));
                    
                else if(op == 4){
                    System.out.println(this.clientController.getBalance(clienteLogado));
                }
                // } else if(op == 5){
                //     System.out.println(this.clienteController.verExtrato(clienteLogado));
                // }
                
                else if(op == 6){
                    this.clienteLogado = null;
                    
                } else {
                    System.out.println("Opção invalida");
                }
            }
            else{
                System.out.println(opcoesSemLogin);

                int op = scan.nextInt();

                if(op == 1){
                    System.out.println("Ja possui cadastro no banco? (s/n)");
                    String opCadastro = scan.next();
                    if(opCadastro.equals("s")){
                        System.out.println("Informe seu CPF:");
                        String cpfAccount = scan.next();
                        Client clientAccount = null;
                        for(Client c : clientRepository.findAll()){
                            if(c.getCpf().equals(cpfAccount)){
                                clientAccount = c;
                            }
                        }
                        if(clientAccount != null){
                            Account conta = accountController.createAccount(clientAccount);
                            if(conta != null){
                                System.out.println("Conta criada com sucesso");
                            }
                            
                            
                        } else {
                            System.out.println("Client não encontrado");
                        }
                    }
                    else{
                        Client cliente = clientController.createClient();
                        if(cliente != null){
                            System.out.println("Cliente cadastrado com sucesso");
                        }
                        accountController.createAccount(cliente);
                        
                        
                        
                    }
                    
                    
                } 
                else if(op == 2){
                    System.out.println("Informe seu CPF:");
                    String cpfLogin = scan.next();
                    System.out.println("Informe sua senha:");
                    String senhaLogin = scan.next();
                    Client clienteLogado = null;
                    for(Client c : clientRepository.findAll()){
                        if(c.getCpf().equals(cpfLogin)){
                            clienteLogado = c;
                        }
                    }
                    if(clienteLogado != null){
                        if(clienteLogado.getPassword().equals(senhaLogin)){
                            System.out.println("Logado com sucesso");
                            System.out.println("Bem vindo "+clienteLogado.getName());
                            this.clienteLogado = clienteLogado;
                        } else {
                            System.out.println("Senha incorreta");
                        }
                    } else {
                        System.out.println("Client não encontrado");
                    }
                } else if(op == 3){
                    scan.close();
                    return;
                } else if(op == 4){
                    System.out.println("Clientes cadastrados");
                    this.clientRepository.findAll().forEach(System.out::println);
                    
                }else if(op == 5){
                    System.out.println("Contas cadastradas");
                    this.accountRepository.findAll().forEach(System.out::println);
                }
                else {
                    System.out.println("Opção invalida");
                }
                
            }
            
            
        }
    }
}
