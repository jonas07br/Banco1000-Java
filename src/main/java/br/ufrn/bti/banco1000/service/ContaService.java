package br.ufrn.bti.banco1000.service;

import java.util.Scanner;

import br.ufrn.bti.banco1000.model.Cliente;
import br.ufrn.bti.banco1000.model.Conta;
import br.ufrn.bti.banco1000.model.TipoConta;

public class ContaService {

    private Scanner scan = new Scanner(System.in);
    public Conta criarConta(Cliente cliente){
        try{
            TipoConta tipoConta = null;
            System.out.println("Qual tipo de conta deseja criar?");
            System.out.println("1 - Corrente");
            System.out.println("2 - Poupança");
            int op =scan.nextInt();
            System.out.println("Informe uma senha numerica para o uso da conta:");
            int senha = scan.nextInt();
            
            switch (op) {
                case 1:
                    tipoConta = TipoConta.CORRENTE;
                    break;
                case 2:
                    tipoConta = TipoConta.POUPANCA;
                    break;
                default:
                    System.out.println("Opção invalida");
                break;
            }
            Conta conta = new Conta(cliente,tipoConta,senha);
            cliente.setContas(conta);
            
            return conta;

        }
        catch(Exception e){
            System.out.println("Erro ao criar conta");
            return null;
        }
        
    }
}
