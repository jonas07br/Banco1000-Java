/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufrn.bti.banco1000.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinicius
 */
public class Client {
    private String name;
    private String cpf;
    private String email;
    private String phoneNumber;
    private Long id;
    private String password;
    
    public Client(Long id,String name, String cpf, String email, String phoneNumber, String password) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this. phoneNumber = phoneNumber;
        this.password= password;
    }

    
    @Override
    public String toString() {
        return 
        id +","+
        name +","+ 
        cpf +","+ 
        email +","+ 
        phoneNumber+","+
        password;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public static String[] getHeaders(){
        return new String[]{"id","name","cpf","email","phoneNumber","password"};
    }
    
}
