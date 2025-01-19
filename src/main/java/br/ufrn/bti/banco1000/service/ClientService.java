package br.ufrn.bti.banco1000.service;

import java.util.List;
import java.util.Scanner;

import br.ufrn.bti.banco1000.model.Client;
import br.ufrn.bti.banco1000.model.Account;
import br.ufrn.bti.banco1000.model.Transaction;
import br.ufrn.bti.banco1000.repository.AccountRepository;
import br.ufrn.bti.banco1000.repository.ClientRepository;

public class ClientService {

    private  Scanner scan;
    private ClientRepository clientRepository;
    private AccountRepository accountRepository;
    private AccountService accountService;

    public ClientService(){
        this.scan = new Scanner(System.in);
        this.clientRepository = new ClientRepository();
        this.accountRepository = new AccountRepository();
        this.accountService = new AccountService();
    }
    public  void closeScanner(){
        scan.close();
    }   
    public Client createClient(){
        while(true){
            try{
                String cpf = this.getCPFAndverify();
                if(clientRepository.findByCpf(cpf)!=null){
                    System.out.println("Cpf ja cadastrado");
                    continue;
                }
                String name = this.getNameAndVerify();
                String email = this.getEmailAndVerify();
                String phoneNumber = this.getPhoneNumberAndVerify();
                String password = this.getPasswordAndVerify();
                Long id = clientRepository.findAll().size()+1L;

                Client client = new Client(id,name, cpf, email, phoneNumber, password);
                
                clientRepository.save(client);
                return client;

            }catch(Exception e){
                System.out.println("Erro ao criar cliente");
                return null;
            }
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
                cliente.setName(getNameAndVerify());
                break;
            case 2:
                cliente.setEmail(getEmailAndVerify()); 
                break;
                
            case 3:
                cliente.setPhoneNumber(getPhoneNumberAndVerify());  
                break;
                
            default:
                System.out.println("Opção invalida");
                break;
        
        }
        return cliente;

    }

    private  String getNameAndVerify(){
        while(true){
            System.out.println("Digite o nome do cliente: ");   
            String name = scan.nextLine();
            if(name.length() > 0){
                return name;
            }
            System.out.println("Nome invalido");
        }
    }
    private  String getCPFAndverify(){
        while(true){
            System.out.println("Digite o cpf do cliente: ");   
            String cpf = scan.next();
            if(cpf.length() == 11){
                return cpf;
            }
            System.out.println("Cpf invalido");
        }
    }
    private  String getEmailAndVerify(){
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
    private  String getPhoneNumberAndVerify(){
        while(true){
            System.out.println("Digite o numero telefonico do cliente: ");   
            String phoneNumber = scan.next();
            if(phoneNumber.length() == 11){
                return phoneNumber;
            }
            System.out.println("Telefone invalido");
        }
    }
    private String getPasswordAndVerify(){
        while(true){
            System.out.println("Informe uma senha alfanumerica para acesso a conta: ");   
            String password = scan.next();
            if(password.length() > 0){
                return password;
            }
            System.out.println("Senha invalida");
        }
    }
    
    public String deposit(Client cliente){
        List<Account> accounts = accountRepository.findByClientId(cliente.getId());
        
        if(accounts.size()==1){
            System.out.println("Conta encontrada:"+accounts.get(0));
            System.out.println("Qual valor deseja depositar?");
            double valor = scan.nextDouble();
            accountService.deposit(accounts.get(0), valor);
            return "Deposito realizado";
        }
        else{
            System.out.println("Selecione a conta desejada:");
            for(int i = 1; i <= accounts.size(); i++){
                System.out.println(i + " - " + accounts.get(i-1).getAccountNumber()+ " - "+accounts.get(i-1).getTipo());
            }
            int op = scan.nextInt();
            if(accounts.get(op-1)==null){
                System.out.println("Conta inexistente");
            }
            System.out.println("Qual valor deseja depositar?");
            double valor = scan.nextDouble();
            accountService.deposit(accounts.get(op-1), valor);
            return "Deposito realizado";
        }
    }


    // public Account selecionarConta(Client cliente){
    //     System.out.println("Selecione a conta desejada:");
    //         for(int i = 1; i <= cliente.getContas().size(); i++){
    //             System.out.println(i + " - " + cliente.getContas().get(i-1).getNumConta()+ " - "+cliente.getContas().get(i-1).getTipo());
    //         }
    //         int op = scan.nextInt();
    //         if(cliente.getContas().get(op-1)==null){
    //             System.out.println("Conta inexistente");
    //         }
    //         return cliente.getContas().get(op-1);
    // }
    public String withdraw(Client cliente) {
        List<Account> accounts = accountRepository.findByClientId(cliente.getId());
        
        if(accounts.size()==1){
            System.out.println("Conta encontrada:"+accounts.get(0));
            System.out.println("Qual valor deseja sacar?");
            double valor = scan.nextDouble();
            accountService.withdraw(accounts.get(0), valor);
            return "Saque realizado";
        }
        else{
            System.out.println("Selecione a conta desejada:");
            for(int i = 1; i <= accounts.size(); i++){
                System.out.println(i + " - " + accounts.get(i-1).getAccountNumber()+ " - "+accounts.get(i-1).getTipo());
            }
            int op = scan.nextInt();
            if(accounts.get(op-1)==null){
                System.out.println("Conta inexistente");
            }
            System.out.println("Qual valor deseja sacar?");
            double valor = scan.nextDouble();
            accountService.withdraw(accounts.get(op-1), valor);
            return "Saque realizado";
        }
    }
    // public String transferir(Client clienteLogado, List<Account> contasBd) {
    //     System.out.println("Qual valor deseja transferir?");
    //     double valor = scan.nextDouble();
    //     System.out.println("Informe o numero da conta do destinatario:");
    //     int numConta = scan.nextInt();
    //     Account contaDestino = null;
    //     for(Account c : contasBd){
    //         if(c.getNumConta()==numConta){
    //             contaDestino = c;
    //             System.out.println("Conta encontrada");
    //         }
    //     }
    //     if(contaDestino == null){
    //         System.out.println("Conta inexistente");
    //     }
    //     if(clienteLogado.getContas().size()>1){
    //         System.out.println("Selecione de onde o dinheiro sairá.");
    //         return this.selecionarConta(clienteLogado).transferir(contaDestino, valor);
    //     }
    //     else{
    //         return clienteLogado.getContas().get(0).transferir(contaDestino, valor);
    //     }

    // }
    public String getBalance(Client clienteLogado) {
        List<Account> accounts = accountRepository.findByClientId(clienteLogado.getId());
        if(accounts.size()==1){
            System.out.println("Saldo: "+accounts.get(0).getBalance());
            return "Saldo impresso";
        }
        else{
            for(int i = 1; i <= accounts.size(); i++){
                System.out.println(i + " - " + accounts.get(i-1).getAccountNumber()+ " - "+accounts.get(i-1).getTipo()+" - Saldo: "+accounts.get(i-1).getBalance());
            }
            
            return "Saldo impresso";
        }
    }
    // public String verExtrato(Client clienteLogado) {
    //     if(clienteLogado.getContas().size()>1){
    //         System.out.println("Selecione a conta desejada:");
    //         List<Transaction> extrato = this.selecionarConta(clienteLogado).getTransaction();
    //         extrato.forEach(System.out::println);
    //         return "Extrato impresso";
    //     }
    //     else{     
    //         List<Transaction> extrato = clienteLogado.getContas().get(0).getTransaction();
    //         extrato.forEach(System.out::println);
    //         return "Extrato impresso";
    //     }
    // }

    

}
