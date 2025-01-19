package br.ufrn.bti.banco1000.model;

import br.ufrn.bti.banco1000.model.enumerations.AccountType;

public class SavingsAccount extends Account {
    private final double monthlyIncome=0.05;

    public SavingsAccount(int agency, Long accountNumber, Long clientId, AccountType accountType, int password,
            double balance) {
        super(agency, accountNumber, clientId, accountType, password, balance);
    }

}
