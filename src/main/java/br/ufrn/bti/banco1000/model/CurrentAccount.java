package br.ufrn.bti.banco1000.model;

import java.math.BigDecimal;

import br.ufrn.bti.banco1000.model.enumerations.AccountType;

public class CurrentAccount extends Account {

    
    private final double maintenancefees = 10.0;
    
    public CurrentAccount(Long agency, Long accountNumber, Long clientId, AccountType accountType, int password,
            BigDecimal balance) {
        super(agency, accountNumber, clientId, accountType, password, balance);
    }
    
}
