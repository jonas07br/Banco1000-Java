/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufrn.bti.banco1000.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author vinicius
 */
public class Transaction {
    
    private Long id;
    private Long userId;
    private LocalDate date;
    private String type;
    private Long sourceAccountId;
    private Long destinationAccountId;
    private String description;
    private BigDecimal value;
    
    public static String[] getHeaders() {
        return new String[] {"id", "userId", "date", "type", "sourceAccountId","destinationAccountId", "description", "value"};
    }

    public Transaction(Long id,Long userId,String type, Long sourceAccountId, Long destinationAccountId, String description,
            BigDecimal value) {
        this.id = id;
        this.userId=userId;
        this.date = LocalDate.now();
        this.type = type;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.description = description;
        this.value = value;
    }
    public Transaction(Long id,Long userId,LocalDate date,String type, Long sourceAccountId, Long destinationAccountId, String description,
            BigDecimal value) {
        this.id = id;
        this.userId=userId;
        this.date = date;
        this.type = type;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.description = description;
        this.value = value;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Long getSourceAccountId() {
        return sourceAccountId;
    }
    public void setSourceAccountId(Long sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }
    public Long getDestinationAccountId() {
        return destinationAccountId;
    }
    public void setDestinationAccountId(Long destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return 
        id +","+
        userId +","+
        date +","+
        type+","+
        sourceAccountId +","+ 
        destinationAccountId +","+
        description +","+
        value;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    

    
}
