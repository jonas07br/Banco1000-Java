package br.ufrn.bti.banco1000.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import br.ufrn.bti.banco1000.model.Account;
import br.ufrn.bti.banco1000.model.Client;
import br.ufrn.bti.banco1000.model.CurrentAccount;
import br.ufrn.bti.banco1000.model.SalaryAccount;
import br.ufrn.bti.banco1000.model.SavingsAccount;
import br.ufrn.bti.banco1000.model.enumerations.AccountType;

public class AccountRepository {
    private static final String SAMPLE_CSV_FILE_PATH = "src\\main\\resources\\persistence\\accounts.csv";

    public List<Account> findAll(){
        List<Account> accounts = new ArrayList<>();
        try{
            accounts=readCsv();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return accounts;
    }

    
    public void save(Account account){
        try{
            List<Account> accounts = readCsv();
            accounts.add(account);
            writeCsv(accounts);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void writeCsv(List<Account> accounts) throws IOException{
        try (
            StringWriter writer = new StringWriter();
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
            .withHeader(Account.getHeaders())
            );
            ) {
                for (Account account : accounts) {
                    csvPrinter.printRecord(account.getAgency(), account.getAccountNumber(), account.getClientId(),account.getAccountType(), account.getPassword(), account.getBalance());
                }
                Files.write(Paths.get(SAMPLE_CSV_FILE_PATH), writer.toString().getBytes());
            }
        }
        
    private static List<Account> readCsv() throws IOException{
        try (
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
            .withHeader(Account.getHeaders())
            .withIgnoreHeaderCase(false)
            .withTrim()
            );
        ) {
            List<Account> accounts = new ArrayList<Account>();
            for (CSVRecord csvRecord : csvParser) {
                if(csvRecord.get(Account.getHeaders()[0]).equalsIgnoreCase("agency")){
                    continue;
                }
                int agency = Integer.parseInt(csvRecord.get(Account.getHeaders()[0]));
                Long accountNumber = Long.parseLong(csvRecord.get(Account.getHeaders()[1]));
                Long clientId = Long.parseLong(csvRecord.get(Account.getHeaders()[2]));
                AccountType accountType = AccountType.valueOf(csvRecord.get(Account.getHeaders()[3]));
                int password = Integer.parseInt(csvRecord.get(Account.getHeaders()[4]));
                BigDecimal balance = new BigDecimal(csvRecord.get(Account.getHeaders()[5]));

                if(AccountType.CORRENTE.equals(accountType)){
                    CurrentAccount account = new CurrentAccount(agency, accountNumber, clientId, accountType, password, balance);
                    accounts.add(account);
                }
                else if(AccountType.POUPANCA.equals(accountType)){
                    SavingsAccount account = new SavingsAccount(agency, accountNumber, clientId, accountType, password, balance);
                    accounts.add(account);
                }
                else if(AccountType.SALARIO.equals(accountType)){
                    SalaryAccount account = new SalaryAccount(agency, accountNumber, clientId, accountType, password, balance);
                    accounts.add(account);
                }
                else{
                    throw new RuntimeException("Tipo de conta inválido");
                }
                
                
            }
            return accounts;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Account> findByClientId(Long clientId){
        List<Account> accounts = new ArrayList<>();
        try{
            List<Account> allAccounts = readCsv();
            for(Account account : allAccounts){
                if(account.getClientId().equals(clientId)){
                    accounts.add(account);
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return accounts;
    }

    public void saveAll(List<Account> accounts){
        try{
            writeCsv(accounts);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public Account findByAccountNumber(Long accountNumber){
        try{
            List<Account> allAccounts = readCsv();
            for(Account account : allAccounts){
                if(account.getAccountNumber().equals(accountNumber)){
                    return account;
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
