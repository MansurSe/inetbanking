package ru.skillfactory.inetbanking.model;


import lombok.Getter;

@Getter
public class MoneyTransferRequest {
    private Long id1;
    private Long id2;
    private int balance;


}
