/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.ufrn.bti.banco1000;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import br.ufrn.bti.banco1000.repository.UserRepository;
import br.ufrn.bti.banco1000.view.Terminal;


public class Banco1000 {

    
    public static void main(String[] args) {
        System.out.println("Bem vindo ao Banco1000");
        Terminal terminal = new Terminal();
        terminal.start();
        
        // UserRepository.getUsers();
    }
}
