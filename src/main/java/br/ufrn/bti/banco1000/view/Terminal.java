package br.ufrn.bti.banco1000.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ufrn.bti.banco1000.controller.ClienteController;
import br.ufrn.bti.banco1000.controller.ContaController;
import br.ufrn.bti.banco1000.model.Cliente;
import br.ufrn.bti.banco1000.model.Conta;
import br.ufrn.bti.banco1000.model.TipoConta;
import br.ufrn.bti.banco1000.service.ClienteService;
import br.ufrn.bti.banco1000.service.ContaService;

public class Terminal {

    // private ClienteService clienteService;
    // private ContaService contaService;
    private ClienteController clienteController;
    private ContaController contaController;

    private List<Cliente> clientesBd;
    private List<Conta> contasBd;

    public Cliente clienteLogado;

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
        this.clienteController = new ClienteController();
        this.contaController = new ContaController();

        this.clientesBd = new ArrayList<>();
        this.contasBd = new ArrayList<>();

        this.clienteLogado = null;

    }
    public void start(){
        Scanner scan = new Scanner(System.in);
        while(true){
            if(clienteLogado != null){
                System.out.println(opcoesComLogin);
                int op = scan.nextInt();
                if(op == 1){
                    System.out.println(this.clienteController.depositar(clienteLogado));
                
                } else if(op == 2){
                    System.out.println(this.clienteController.sacar(clienteLogado));
                    
                } else if(op == 3){
                    System.out.println(this.clienteController.transferir(clienteLogado,contasBd));
                    
                } else if(op == 4){
                    System.out.println(this.clienteController.verSaldo(clienteLogado));

                } else if(op == 5){
                    System.out.println(this.clienteController.verExtrato(clienteLogado));

                } else if(op == 6){
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
                        String cpfConta = scan.next();
                        Cliente clienteConta = null;
                        for(Cliente c : clientesBd){
                            if(c.getCpf().equals(cpfConta)){
                                clienteConta = c;
                            }
                        }
                        if(clienteConta != null){
                            Conta conta = contaController.criarConta(clienteConta);
                            if(conta != null){
                                System.out.println("Conta criada com sucesso");
                                contasBd.add(conta);
                            }
                            
                            
                        } else {
                            System.out.println("Cliente não encontrado");
                        }
                    }
                    else{
                        Cliente cliente = clienteController.criarCliente();
                        Conta conta = contaController.criarConta(cliente);
                        if(conta!=null){
                            System.out.println("Conta criada com sucesso");
                            contasBd.add(conta);
                        }
                        
                        clientesBd.add(cliente);
                    }
                    
                    
                } 
                else if(op == 2){
                    System.out.println("Informe seu CPF:");
                    String cpfLogin = scan.next();
                    System.out.println("Informe sua senha:");
                    String senhaLogin = scan.next();
                    Cliente clienteLogado = null;
                    for(Cliente c : clientesBd){
                        if(c.getCpf().equals(cpfLogin)){
                            clienteLogado = c;
                        }
                    }
                    if(clienteLogado != null){
                        if(clienteLogado.getSenha().equals(senhaLogin)){
                            System.out.println("Logado com sucesso");
                            System.out.println("Bem vindo "+clienteLogado.getNome());
                            this.clienteLogado = clienteLogado;
                        } else {
                            System.out.println("Senha incorreta");
                        }
                    } else {
                        System.out.println("Cliente não encontrado");
                    }
                } else if(op == 3){
                    scan.close();
                    return;
                } else if(op == 4){
                    System.out.println("Clientes cadastrados");
                    this.clientesBd.forEach(System.out::println);
                    
                } else {
                    System.out.println("Opção invalida");
                }
                
            }
            
            
        }
    }
}
