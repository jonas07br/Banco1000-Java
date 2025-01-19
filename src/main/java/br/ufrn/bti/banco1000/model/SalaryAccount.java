package br.ufrn.bti.banco1000.model;

import br.ufrn.bti.banco1000.model.enumerations.AccountType;

public class SalaryAccount extends Account {
    
    private final int withdrawalLimit = 2;
    
    public SalaryAccount(int agency, Long accountNumber, Long clientId, AccountType accountType, int password,
            double balance) {
        super(agency, accountNumber, clientId, accountType, password, balance);
    }

}
