package br.ufrn.bti.banco1000.service;

import java.util.List;
import java.util.Scanner;

import br.ufrn.bti.banco1000.model.Client;
import br.ufrn.bti.banco1000.model.Account;
import br.ufrn.bti.banco1000.model.Transaction;

public class ClientService {

    private  Scanner scan = new Scanner(System.in);

    public  void closeScanner(){
        scan.close();
    }   
    public  Client criarClient(){
        while(true){
            String nome = this.setNome();
            String cpf = this.setCpf();
            String email = this.setEmail();
            String telefone = this.setTelefone();
            String senha = this.setSenha();
            
            System.out.println("Client criado com sucesso");
            return new Client(nome, cpf, email, telefone, senha);
        }
    }
    public Client atualizarClient(Client cliente) {
        Scanner scanOp = new Scanner(System.in);
        System.out.println("O que deseja atualizar?");
        System.out.println("1 - Nome");
        System.out.println("2 - Email");
        System.out.println("3 - Telefone");
        int op =scanOp.nextInt();
        switch (op){
            case 1:
                cliente.setNome(setNome());
                break;
            case 2:
                cliente.setEmail(setEmail()); 
                break;
                
            case 3:
                cliente.setTelefone(setTelefone());  
                break;
                
            default:
                System.out.println("Opção invalida");
                break;
        
        }
        return cliente;

    }

    private  String setNome(){
        while(true){
            System.out.println("Digite o nome do cliente: ");   
            String nome = scan.nextLine();
            if(nome.length() > 0){
                return nome;
            }
            System.out.println("Nome invalido");
        }
    }
    private  String setCpf(){
        while(true){
            System.out.println("Digite o cpf do cliente: ");   
            String cpf = scan.next();
            if(cpf.length() == 11){
                return cpf;
            }
            System.out.println("Cpf invalido");
        }
    }
    private  String setEmail(){
        while(true){
            System.out.println("Digite o email do cliente: ");   
            String email = scan.next();
            if(email.length() > 10 && 
                (email.contains("@gmail.com") ||
                email.contains("@hotmail.com") ||
                email.contains("@outlook.com"))){

                return email;
            }
            System.out.println("Email invalido");
        }
    }
    private  String setTelefone(){
        while(true){
            System.out.println("Digite o telefone do cliente: ");   
            String telefone = scan.next();
            if(telefone.length() == 11){
                return telefone;
            }
            System.out.println("Telefone invalido");
        }
    }
    private String setSenha(){
        while(true){
            System.out.println("Informe uma senha alfanumerica para acesso a conta: ");   
            String senha = scan.next();
            if(senha.length() > 0){
                return senha;
            }
            System.out.println("Senha invalida");
        }
    }
    
    public String depositar(Client cliente){
        if(cliente.getContas().size() >1){
            System.out.println("Qual valor deseja depositar?");
            double valor = scan.nextDouble();
            return this.selecionarConta(cliente).depositar(valor);
        }
        else{
            System.out.println("Qual valor deseja depositar?");
            double valor = scan.nextDouble();
            return cliente.getContas().get(0).depositar(valor);
        }
    }
    public Account selecionarConta(Client cliente){
        System.out.println("Selecione a conta desejada:");
            for(int i = 1; i <= cliente.getContas().size(); i++){
                System.out.println(i + " - " + cliente.getContas().get(i-1).getNumConta()+ " - "+cliente.getContas().get(i-1).getTipo());
            }
            int op = scan.nextInt();
            if(cliente.getContas().get(op-1)==null){
                System.out.println("Conta inexistente");
            }
            return cliente.getContas().get(op-1);
    }
    public String sacar(Client cliente) {
        if(cliente.getContas().size() >1){
            System.out.println("Qual valor deseja sacar?");
            double valor = scan.nextDouble();
            return this.selecionarConta(cliente).sacar(valor);
        }
        else{
            System.out.println("Qual valor deseja sacar?");
            double valor = scan.nextDouble();
            return cliente.getContas().get(0).sacar(valor);
        }
    }
    public String transferir(Client clienteLogado, List<Account> contasBd) {
        System.out.println("Qual valor deseja transferir?");
        double valor = scan.nextDouble();
        System.out.println("Informe o numero da conta do destinatario:");
        int numConta = scan.nextInt();
        Account contaDestino = null;
        for(Account c : contasBd){
            if(c.getNumConta()==numConta){
                contaDestino = c;
                System.out.println("Conta encontrada");
            }
        }
        if(contaDestino == null){
            System.out.println("Conta inexistente");
        }
        if(clienteLogado.getContas().size()>1){
            System.out.println("Selecione de onde o dinheiro sairá.");
            return this.selecionarConta(clienteLogado).transferir(contaDestino, valor);
        }
        else{
            return clienteLogado.getContas().get(0).transferir(contaDestino, valor);
        }

    }
    public String verSaldo(Client clienteLogado) {
        if(clienteLogado.getContas().size()>1){
            System.out.println("Selecione a conta desejada:");
            String saldo = "R$"+this.selecionarConta(clienteLogado).getSaldo();
            return saldo;
        }
        else{
            String saldo = "R$"+clienteLogado.getContas().get(0).getSaldo();
            return saldo;
        }
    }
    public String verExtrato(Client clienteLogado) {
        if(clienteLogado.getContas().size()>1){
            System.out.println("Selecione a conta desejada:");
            List<Transaction> extrato = this.selecionarConta(clienteLogado).getTransaction();
            extrato.forEach(System.out::println);
            return "Extrato impresso";
        }
        else{     
            List<Transaction> extrato = clienteLogado.getContas().get(0).getTransaction();
            extrato.forEach(System.out::println);
            return "Extrato impresso";
        }
    }

    

}
