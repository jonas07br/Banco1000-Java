package br.ufrn.bti.banco1000.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import br.ufrn.bti.banco1000.model.Client;
import br.ufrn.bti.banco1000.model.Account;
import br.ufrn.bti.banco1000.model.Transaction;
import br.ufrn.bti.banco1000.repository.AccountRepository;
import br.ufrn.bti.banco1000.repository.ClientRepository;
import br.ufrn.bti.banco1000.repository.TransactionRepository;

public class ClientService {

    private  Scanner scan;
    private ClientRepository clientRepository;
    private AccountRepository accountRepository;
    private AccountService accountService;
    private TransactionRepository transactionRepository;

    public ClientService(){
        this.scan = new Scanner(System.in);
        this.clientRepository = new ClientRepository();
        this.accountRepository = new AccountRepository();
        this.accountService = new AccountService();
        this.transactionRepository = new TransactionRepository();
    }

    public Client createClient(){
        while(true){
            try{
                String cpf = this.getCPFAndverify();
                if(clientRepository.findByCpf(cpf)!=null){
                    System.out.println("!!!CPF ja cadastrado");
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
                System.out.println(">>>Erro ao criar cliente");
                return null;
            }
        }
    }
    
    private  String getNameAndVerify(){
        while(true){
            System.out.println("Digite o nome do cliente: ");   
            String name = scan.nextLine();
            if(name.length() > 0){
                return name;
            }
            System.out.println("!!!Nome invalido");
        }
    }
    private  String getCPFAndverify(){
        while(true){
            System.out.println("Digite o cpf do cliente: ");   
            String cpf = scan.next();
            if(cpf.length() == 11){
                scan.nextLine();
                return cpf;
            }
            System.out.println("!!!Cpf invalido");
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
                scan.nextLine();
                return email;
            }
            System.out.println("!!!Email invalido");
        }
    }
    private  String getPhoneNumberAndVerify(){
        while(true){
            System.out.println("Digite o numero telefonico do cliente: ");   
            String phoneNumber = scan.next();
            if(phoneNumber.length() == 11){
                scan.nextLine();
                return phoneNumber;
            }
            System.out.println("!!!Telefone invalido");
        }
    }
    private String getPasswordAndVerify(){
        while(true){
            System.out.println("Informe uma senha alfanumerica para acesso a conta: ");   
            String password = scan.next();
            if(password.length() > 0){
                scan.nextLine();
                return password;
            }
            System.out.println("!!!Senha invalida");
        }
    }
    
    public String deposit(Client cliente){
        try{

            List<Account> accounts = accountRepository.findByClientId(cliente.getId());
            
            if(accounts.size()==1){
            System.out.println("Conta encontrada:"+accounts.get(0));
            System.out.println("Qual valor deseja depositar?");
            double valor = scan.nextDouble();
            accountService.deposit(accounts.get(0), valor);

            Transaction transaction = new Transaction(
                transactionRepository.findAll().size()+1L,
                cliente.getId(),
                "DEPOSITO",
                null,
                accounts.get(0).getAccountNumber(),
                "DEPOSITO",
                new BigDecimal(valor));
            transactionRepository.save(transaction);

            return "Deposito realizado";
        }
        else if(accounts.size()>1){
            System.out.println("Selecione a conta desejada:");
            for(int i = 1; i <= accounts.size(); i++){
                System.out.println(i + " - " + accounts.get(i-1).getAccountNumber()+ " - "+accounts.get(i-1).getTipo());
            }
            int op = scan.nextInt();
            if(accounts.get(op-1)==null){
                System.out.println("!!!Conta inexistente");
            }
            System.out.println("Qual valor deseja depositar?");
            double valor = scan.nextDouble();
            accountService.deposit(accounts.get(op-1), valor);

            Transaction transaction = new Transaction(
                transactionRepository.findAll().size()+1L,
                cliente.getId(),
                "DEPOSITO",
                null,
                accounts.get(op-1).getAccountNumber(),
                "DEPOSITO",
                new BigDecimal(valor));
            transactionRepository.save(transaction);

            return "Deposito realizado";
        }
        else{
            System.out.println("Voce não possui conta(s) cadastrada(s)");
            return ">>>Deposito não realizado";
        }
        }catch(Exception e){
            System.out.println(">>>Erro ao depositar");
            scan.nextLine();
            return "Deposito não realizado";
        }
    }


    public String withdraw(Client cliente) {
        try{
            List<Account> accounts = accountRepository.findByClientId(cliente.getId());
            
            if(accounts.size()==1){
                System.out.println("Conta encontrada:"+accounts.get(0));
                System.out.println("Qual valor deseja sacar?");
                double valor = scan.nextDouble();
                accountService.withdraw(accounts.get(0), valor);
                Transaction transaction = new Transaction(
                        transactionRepository.findAll().size()+1L,
                        cliente.getId(),
                        "SAQUE",
                        accounts.get(0).getAccountNumber(),
                        null,
                        "SAQUE",
                        new BigDecimal(valor));
                    transactionRepository.save(transaction);
                return "Saque realizado";
            }
            else if(accounts.size()>1){
                System.out.println("Selecione a conta desejada:");
                for(int i = 1; i <= accounts.size(); i++){
                    System.out.println(i + " - " + accounts.get(i-1).getAccountNumber()+ " - "+accounts.get(i-1).getTipo());
                }
                int op = scan.nextInt();
                if(accounts.get(op-1)!=null){
                    System.out.println("Qual valor deseja sacar?");
                    double valor = scan.nextDouble();
                    accountService.withdraw(accounts.get(op-1), valor);

                    Transaction transaction = new Transaction(
                        transactionRepository.findAll().size()+1L,
                        cliente.getId(),
                        "SAQUE",
                        accounts.get(op-1).getAccountNumber(),
                        null,
                        "SAQUE",
                        new BigDecimal(valor));
                    transactionRepository.save(transaction);

                    return "Saque realizado";
                }
                System.out.println("!!!Conta inexistente");
                return "Saque não realizado";
            }else{
                System.out.println("Voce não possui conta(s) cadastrada(s)");
                return "Saque não realizado";
            }
        }catch(Exception e){
            System.out.println("Erro ao sacar");
            scan.nextLine();
            return "Saque não realizado";
        }
    }
    public String transfer(Client loggedClient) {
        try{
            System.out.println("Qual valor deseja transferir?");
            double valor = scan.nextDouble();
    
            System.out.println("Informe o numero da conta do destinatario:");
            Long numConta = scan.nextLong();
            
            if(accountRepository.findByAccountNumber(numConta)!=null){
                Account destinationAccount = accountRepository.findByAccountNumber(numConta);
                System.out.println("Conta encontrada: "+destinationAccount);
                List<Account> accounts = accountRepository.findByClientId(loggedClient.getId());
                if(accounts.size()==1){
                    accountService.transfer(accounts.get(0), destinationAccount, valor);

                    Transaction transactionSent = new Transaction(
                        transactionRepository.findAll().size()+1L,
                        loggedClient.getId(),
                        "TRANSFERENCIA",
                        accounts.get(0).getAccountNumber(),
                        destinationAccount.getAccountNumber(),
                        "TRANSFERENCIA ENVIADA",
                        new BigDecimal(valor));
                    Transaction transactionReceived = new Transaction(
                        transactionRepository.findAll().size()+1L,
                        destinationAccount.getClientId(),
                        "TRANSFERENCIA",
                        destinationAccount.getAccountNumber(),
                        accounts.get(0).getAccountNumber(),
                        "TRANSFERENCIA RECEBIDA",
                        new BigDecimal(valor));
                    transactionRepository.save(transactionReceived);
                    transactionRepository.save(transactionSent);

                    return "Transferencia realizada";
                }
                else{
                    System.out.println("Selecione a conta origem:");
                    for(int i = 1; i <= accounts.size(); i++){
                        System.out.println(i + " - " + accounts.get(i-1).getAccountNumber()+ " - "+accounts.get(i-1).getTipo());
                    }
                    int op = scan.nextInt();
                    if(accounts.get(op-1)!=null){
                        accountService.transfer(accounts.get(op-1), destinationAccount, valor);

                        Transaction transactionSent = new Transaction(
                            transactionRepository.findAll().size()+1L,
                            loggedClient.getId(),
                            "TRANSFERENCIA",
                            accounts.get(op-1).getAccountNumber(),
                            destinationAccount.getAccountNumber(),
                            "TRANSFERENCIA ENVIADA",
                            new BigDecimal(valor));
                        Transaction transactionReceived = new Transaction(
                            transactionRepository.findAll().size()+1L,
                            destinationAccount.getClientId(),
                            "TRANSFERENCIA",
                            destinationAccount.getAccountNumber(),
                            accounts.get(op-1).getAccountNumber(),
                            "TRANSFERENCIA RECEBIDA",
                            new BigDecimal(valor));
                        transactionRepository.save(transactionSent);
                        transactionRepository.save(transactionReceived);

                        return "Transferencia realizada";
                    }
                    System.out.println("Conta inexistente");
                    return "Transferencia não realizada";
                }
            }
            return "Conta não encontrada";
        }catch(Exception e){
            System.out.println("!!!Erro ao transferir");
            scan.nextLine();
            return ">>>Transferencia não realizada";
        }
        
    }
    public String getBalance(Client loggedClient) {
        List<Account> accounts = accountRepository.findByClientId(loggedClient.getId());
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
    
    public String getExtract(Client loggedClient) {
        Long id = loggedClient.getId();
        transactionRepository.findByClientId(id).forEach(System.out::println);
        return "Extrato impresso";
    }
    public Client login() {
        System.out.println("Informe seu CPF:");
        String cpf = scan.next();
        System.out.println("Informe sua senha:");
        String password = scan.next();
        Client client = clientRepository.findByCpf(cpf);
        if(client!=null && client.getPassword().equals(password)){
            return client;
        }
        return null;
    }

    

}
