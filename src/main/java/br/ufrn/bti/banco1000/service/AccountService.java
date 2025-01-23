package br.ufrn.bti.banco1000.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import br.ufrn.bti.banco1000.model.Client;
import br.ufrn.bti.banco1000.model.Transaction;
import br.ufrn.bti.banco1000.exceptions.BusinessException;
import br.ufrn.bti.banco1000.model.Account;
import br.ufrn.bti.banco1000.model.Agency;
import br.ufrn.bti.banco1000.model.enumerations.AccountType;
import br.ufrn.bti.banco1000.repository.AccountRepository;
import br.ufrn.bti.banco1000.repository.AgencyRepository;
import br.ufrn.bti.banco1000.repository.TransactionRepository;

public class AccountService {
    private AccountRepository accountRepository;
    private AgencyRepository agencyRepository;

    public AccountService(){
        this.accountRepository = new AccountRepository();
        this.agencyRepository = new AgencyRepository();
    }

    private Scanner scan = new Scanner(System.in);
    public Account createAccount(Client cliente){
        while(true){
            try{
                List<Agency> agencies = agencyRepository.findAll();
                System.out.println("Deseja criar a conta em qual agencia?");
                for(int i = 0; i < agencies.size(); i++){
                    System.out.println(i+1 + " - " + agencies.get(i).getAgencyName());
                }
                int opAgency = scan.nextInt();
                scan.nextLine();
                if(opAgency < 0 || opAgency-1 >= agencies.size()){
                    System.out.println("!!!Opção invalida");
                    continue;
                }
                Long agencyCode = agencyRepository.findAll().get(opAgency-1).getAgencyCode();
                
                AccountType accountType = null;
                System.out.println("Qual tipo de conta deseja criar?");
                System.out.println("1 - Corrente");
                System.out.println("2 - Poupança");
                System.out.println("3 - Salario");
                int op =scan.nextInt();
                System.out.println("Informe uma senha NUMERICA para o uso da conta:");
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
                    System.out.println("!!!Opção invalida");
                    break;
                }
                Account conta = new Account(agencyCode,cliente.getId(),accountType,senha);
                accountRepository.save(conta);
                
                return conta;
                
            }
            catch(Exception e){
                System.out.println("!!!Dados invalidos");
                scan.nextLine();
            }
        }
        
    }
    public void deposit(Account account,double value){
        try{
            BigDecimal bigDecimalValue = new BigDecimal(value);
            
            List<Account> accounts = accountRepository.findAll();
            for(Account acc: accounts){
                if(acc.getAccountNumber().compareTo(account.getAccountNumber()) == 0){
                    acc.setBalance(acc.getBalance().add(bigDecimalValue));
                    break;
                }
            }
            accountRepository.saveAll(accounts);
        }catch(Exception e){
            System.out.println(">>>Erro ao depositar");
        }
    }
    public void withdraw(Account account,double value){
        try{

            BigDecimal bigDecimalValue = new BigDecimal(value);
    
            List<Account> accounts = accountRepository.findAll();
            for(Account acc: accounts){
                if(acc.getAccountNumber().compareTo(account.getAccountNumber()) == 0){
                    if(acc.getBalance().compareTo(bigDecimalValue) < 0){
                        throw new BusinessException("Saldo insuficiente");
                    }
                    acc.setBalance(acc.getBalance().subtract(bigDecimalValue));
                    break;
                }
            }
            accountRepository.saveAll(accounts);
        }catch(BusinessException e){
            System.out.println(">>>Erro ao sacar");
        }
    }

    public void transfer(Account account, Account accountDestiny, double value){
        try{
            BigDecimal bigDecimalValue = new BigDecimal(value);
            List<Account> accounts = accountRepository.findAll();
            for(Account acc: accounts){
                if(acc.getAccountNumber().compareTo(account.getAccountNumber()) == 0){
                if(acc.getBalance().compareTo(bigDecimalValue) < 0){
                    throw new BusinessException("Saldo insuficiente");
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
        }catch(BusinessException e){
            System.out.println(">>>Erro ao transferir");
        }
    }
}
