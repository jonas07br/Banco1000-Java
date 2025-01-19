package br.ufrn.bti.banco1000.repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import br.ufrn.bti.banco1000.model.Client;
@SuppressWarnings("deprecation")
public class ClientRepository {
    private static final String SAMPLE_CSV_FILE_PATH = "src\\main\\resources\\persistence\\users.csv";

    public List<Client> findAll(){
        List<Client> clients = new ArrayList<>();
        try{
            clients=readCsv();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return clients;
    }

    public Client findByCpf(String cpf){
        try{
            List<Client> clients = readCsv();
            for(Client client : clients){
                if(client.getCpf().equals(cpf)){
                    return client;
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public void save(Client client){
        try{
            List<Client> clients = readCsv();
            clients.add(client);
            writeCsv(clients);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void writeCsv(List<Client> clients) throws IOException{
        try (
            StringWriter writer = new StringWriter();
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
            .withHeader(Client.getHeaders())
            );
            ) {
                for (Client client : clients) {
                    csvPrinter.printRecord(client.getId(), client.getName(), client.getCpf(), client.getEmail(), client.getPhoneNumber(), client.getPassword());
                }
                Files.write(Paths.get(SAMPLE_CSV_FILE_PATH), writer.toString().getBytes());
            }
        }
        
    private List<Client> readCsv() throws IOException{
        try (
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
            .withHeader(Client.getHeaders())
            .withIgnoreHeaderCase(false)
            .withTrim()
            );
        ) {
            List<Client> clients = new ArrayList<Client>();
            for (CSVRecord csvRecord : csvParser) {
                if(csvRecord.get(Client.getHeaders()[0]).equalsIgnoreCase("id")){
                    continue;
                }
                Long id = Long.parseLong(csvRecord.get(Client.getHeaders()[0]));
                String name = csvRecord.get(Client.getHeaders()[1]);
                String cpf = csvRecord.get(Client.getHeaders()[2]);
                String email = csvRecord.get(Client.getHeaders()[3]);
                String phoneNumber = csvRecord.get(Client.getHeaders()[4]);
                String password = csvRecord.get(Client.getHeaders()[5]);

                Client client = new Client(id,name, cpf, email, phoneNumber, password);
                clients.add(client);
            }
            return clients;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
}