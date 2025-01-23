package br.ufrn.bti.banco1000.repository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import br.ufrn.bti.banco1000.model.Agency;


public class AgencyRepository {
    private static final String SAMPLE_CSV_FILE_PATH = "src\\main\\resources\\persistence\\agencyes.csv";
    
    public AgencyRepository(){
        try{
            readCsv();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public List<Agency> findAll(){
        List<Agency> agencyes = new ArrayList<>();
        try{
            agencyes=readCsv();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return agencyes;
    }

    public void save(Agency account){
        try{
            List<Agency> agencyes = readCsv();
            agencyes.add(account);
            writeCsv(agencyes);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    private void writeCsv(List<Agency> agencyes) throws IOException{
        try (
            StringWriter writer = new StringWriter();
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
            .withHeader(Agency.getHeaders())
            );
            ) {
                for (Agency agencye : agencyes) {
                    csvPrinter.printRecord(agencye.getAgencyCode(), agencye.getAgencyName());
                }
                Files.write(Paths.get(SAMPLE_CSV_FILE_PATH), writer.toString().getBytes());
            }
        }
        
    private List<Agency> readCsv() throws IOException{
        try (
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
            .withHeader(Agency.getHeaders())
            .withIgnoreHeaderCase(false)
            .withTrim()
            );
        ) {
            List<Agency> agencyes = new ArrayList<Agency>();
            for (CSVRecord csvRecord : csvParser) {
                if(csvRecord.get(Agency.getHeaders()[0]).equalsIgnoreCase("agencyCode")){
                    continue;
                }
                Long agencyCode = Long.parseLong(csvRecord.get(Agency.getHeaders()[0]));
                String agencyName = csvRecord.get(Agency.getHeaders()[1]);
                agencyes.add(new Agency(agencyCode, agencyName));
            }
            return agencyes;
        }
        catch(Exception e){
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader(Agency.getHeaders())
                    );
            this.save(new Agency(1000L, "Agencia 1000"));
            this.save(new Agency(1112L, "Agencia Azul"));
            return null;
        }
    }
    
}
