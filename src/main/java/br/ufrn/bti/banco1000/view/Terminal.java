package br.ufrn.bti.banco1000.view;

import java.io.IOException;
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
                    Terminal.clearScreen();
                    System.out.println(this.clientController.deposit(clienteLogado));
                }else if(op == 2){
                    Terminal.clearScreen();
                    System.out.println(this.clientController.withdraw(clienteLogado));
                }else if(op == 3){
                    Terminal.clearScreen();
                    System.out.println(this.clientController.transfer(clienteLogado));
                }else if(op == 4){
                    Terminal.clearScreen();
                    System.out.println(this.clientController.getBalance(clienteLogado));
                }else if(op == 6){
                    this.clienteLogado = null;
                    Terminal.clearScreen();
                    
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

                        Client clientAccount = clientController.login();
                        
                        if(clientAccount != null){
                            Account conta = accountController.createAccount(clientAccount);
                            if(conta != null){
                                Terminal.clearScreen();
                                System.out.println("Conta criada com sucesso");
                            }
                            
                        }else{
                            Terminal.clearScreen();
                            System.out.println("Cpf ou senha incorretos");
                        }
                        
                    }
                    else{
                        Client cliente = clientController.createClient();
                        if(cliente != null){
                            System.out.println("Cliente cadastrado com sucesso");
                        }
                        if(accountController.createAccount(cliente)!=null){
                            Terminal.clearScreen();
                            System.out.println("Conta criada com sucesso");
                        }
                    }
                } 
                else if(op == 2){

                    Client clienteLogado = clientController.login();
                    if(clienteLogado != null){
                        Terminal.clearScreen();

                        System.out.println("Logado com sucesso");
                        System.out.println("Bem vindo "+clienteLogado.getName());
                        this.clienteLogado = clienteLogado;
                    
                    } else {
                        System.out.println("Cpf ou senha incorretos");
                    }
                }else if(op == 3){
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

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Comando para Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Comando para Linux/macOS
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ex) {
            System.err.println("Erro ao limpar a tela: " + ex.getMessage());
        }
    }
}
