package br.ufrn.bti.banco1000.service;

import java.util.Scanner;

import br.ufrn.bti.banco1000.model.Client;
import br.ufrn.bti.banco1000.model.Account;
import br.ufrn.bti.banco1000.model.enumerations.AccountType;

public class AccountService {

    private Scanner scan = new Scanner(System.in);
    public Account criarConta(Client cliente){
        try{
            AccountType accountType = null;
            System.out.println("Qual tipo de conta deseja criar?");
            System.out.println("1 - Corrente");
            System.out.println("2 - Poupança");
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
                default:
                    System.out.println("Opção invalida");
                break;
            }
            Account conta = new Account(cliente,accountType,senha);
            cliente.setContas(conta);
            
            return conta;

        }
        catch(Exception e){
            System.out.println("Erro ao criar conta");
            return null;
        }
        
    }
}
