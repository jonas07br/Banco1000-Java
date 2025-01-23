package br.ufrn.bti.banco1000.model;

import java.math.BigDecimal;

import br.ufrn.bti.banco1000.model.enumerations.AccountType;

public class SalaryAccount extends Account {
    
    private final int withdrawalLimit = 2;
    
    public SalaryAccount(Long agency, Long accountNumber, Long clientId, AccountType accountType, int password,
            BigDecimal balance) {
        super(agency, accountNumber, clientId, accountType, password, balance);
    }

}
