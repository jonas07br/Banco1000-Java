package br.ufrn.bti.banco1000.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;


import br.ufrn.bti.banco1000.model.Transaction;


public class TransactionRepository {

    private static final String SAMPLE_CSV_FILE_PATH = "src\\main\\resources\\persistence\\transactions.csv";

    public static List<Transaction> findAll(){
        List<Transaction> transactions = new ArrayList<>();
        try{
            transactions=readCsv();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return transactions;
    }

    public static void save(Transaction transaction){
        try{
            List<Transaction> transactions = readCsv();
            transactions.add(transaction);
            writeCsv(transactions);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private static void writeCsv(List<Transaction> transactions) throws IOException{
        try (
            StringWriter writer = new StringWriter();
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
            .withHeader(Transaction.getHeaders())
            );
            ) {
                for (Transaction transaction : transactions) {
                    csvPrinter.printRecord(transaction.getId(), transaction.getUserId(), transaction.getDate(), transaction.getType(), transaction.getSourceAccountId(), transaction.getDestinationAccountId(), transaction.getDescription(), transaction.getValue());
                }
                Files.write(Paths.get(SAMPLE_CSV_FILE_PATH), writer.toString().getBytes());
            }
        }


    private static List<Transaction> readCsv() throws IOException{
        try (
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
            .withHeader(Transaction.getHeaders())
            .withIgnoreHeaderCase(false)
            .withTrim()
            );
        ) {
            List<Transaction> transactions = new ArrayList<Transaction>();
            for (CSVRecord csvRecord : csvParser) {
                if(csvRecord.get(Transaction.getHeaders()[0]).equalsIgnoreCase("id")){
                    continue;
                }
                Long id= Long.parseLong(csvRecord.get(Transaction.getHeaders()[0]));
                Long userId= Long.parseLong(csvRecord.get(Transaction.getHeaders()[1]));
                LocalDate date= LocalDate.parse(csvRecord.get(Transaction.getHeaders()[2]));
                String type= csvRecord.get(Transaction.getHeaders()[3]);
                Long sourceAccountId= Long.parseLong(csvRecord.get(Transaction.getHeaders()[4]));
                Long destinationAccountId= Long.parseLong(csvRecord.get(Transaction.getHeaders()[5]));
                String description  = csvRecord.get(Transaction.getHeaders()[6]);
                BigDecimal value= new BigDecimal(csvRecord.get(Transaction.getHeaders()[7]));
                Transaction transaction = new Transaction(id, userId, date, type, sourceAccountId, destinationAccountId, description, value);
                transactions.add(transaction);
                
            }
            return transactions;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
}
