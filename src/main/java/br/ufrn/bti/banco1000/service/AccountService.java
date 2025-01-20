package br.ufrn.bti.banco1000.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import br.ufrn.bti.banco1000.model.Client;
import br.ufrn.bti.banco1000.model.Account;
import br.ufrn.bti.banco1000.model.enumerations.AccountType;
import br.ufrn.bti.banco1000.repository.AccountRepository;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(){
        this.accountRepository = new AccountRepository();
    }

    private Scanner scan = new Scanner(System.in);
    public Account createAccount(Client cliente){
        try{
            AccountType accountType = null;
            System.out.println("Qual tipo de conta deseja criar?");
            System.out.println("1 - Corrente");
            System.out.println("2 - Poupança");
            System.out.println("3 - Salario");
            int op =scan.nextInt();
            System.out.println("Informe uma senha numerica para o uso da conta:");
            int senha = scan.nextInt();
            
            switch (op) {
                case 1:
                    accountType = AccountType.CORRENTE;
                    break;
                case 2:
                    accountType = AccountType.POUPANCA;
                    break;
                case 3:
                    accountType = AccountType.SALARIO;
                    break;
                default:
                    System.out.println("Opção invalida");
                break;
            }
            Account conta = new Account(cliente.getId(),accountType,senha);
            accountRepository.save(conta);
            
            return conta;

        }
        catch(Exception e){
            System.out.println("Erro ao criar conta");
            return null;
        }
        
    }
    public void deposit(Account account,double value){
        BigDecimal bigDecimalValue = new BigDecimal(value);
        
        List<Account> accounts = accountRepository.findAll();
        for(Account acc: accounts){
            if(acc.getAccountNumber().compareTo(account.getAccountNumber()) == 0){
                acc.setBalance(acc.getBalance().add(bigDecimalValue));
                break;
            }
        }
        accountRepository.saveAll(accounts);
    }
    public void withdraw(Account account,double value){
        BigDecimal bigDecimalValue = new BigDecimal(value);

        List<Account> accounts = accountRepository.findAll();
        for(Account acc: accounts){
            if(acc.getAccountNumber().compareTo(account.getAccountNumber()) == 0){
                if(acc.getBalance().compareTo(bigDecimalValue) < 0){
                    System.out.println("Saldo insuficiente");
                    return;
                }
                acc.setBalance(acc.getBalance().subtract(bigDecimalValue));
                break;
            }
        }
        accountRepository.saveAll(accounts);
    }
    
    public void transfer(Account account, Account accountDestiny, double value){
        BigDecimal bigDecimalValue = new BigDecimal(value);
        List<Account> accounts = accountRepository.findAll();
        for(Account acc: accounts){
            if(acc.getAccountNumber().compareTo(account.getAccountNumber()) == 0){
                if(acc.getBalance().compareTo(bigDecimalValue) < 0){
                    System.out.println("Saldo insuficiente");
                    return;
                }
                acc.setBalance(acc.getBalance().subtract(bigDecimalValue));
                break;
            }
        }
        for(Account acc: accounts){
            if(acc.getAccountNumber().compareTo(accountDestiny.getAccountNumber()) == 0){
                acc.setBalance(acc.getBalance().add(bigDecimalValue));
                break;
            }
        }
        accountRepository.saveAll(accounts);
    }
}
