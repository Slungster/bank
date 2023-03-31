package org.example.data;

public class AccountDto {

    private Long id;
    private double balance;
    private Long idClient;

    public AccountDto() {
    }

    public AccountDto(Long id, double balance, Long idClient) {
        this.id = id;
        this.balance = balance;
        this.idClient = idClient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public boolean hasSufficientBalance (double amount) {
        return this.balance >= amount;
    }

    public void creditBalance (double amount) {
        this.balance = this.balance + amount;
    }

    public void debitBalance (double amount) {
        this.balance = this.balance - amount;
    }
}
