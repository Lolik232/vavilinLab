package tech.reliab.course.vavilinLab.bank.builders;

import tech.reliab.course.vavilinLab.bank.entity.Bank;

import java.util.Random;

// TODO: create interface and create impl of RandomBankBuilder
public class BankBuilder {
    private String bankName;

    private static final int RATING_BOUND = 101;
    private static final int TOTAL_MONEY_BOUND = 1000001;
    private static final int MAX_RATE = 20;
    private static final double DIVIDER = 10.0;

    private int generateId() {
        return new Random().nextInt(Integer.MAX_VALUE);
    }

    private int generateRating() {
        return new Random().nextInt(RATING_BOUND);
    }

    private double generateTotalMoney() {
        return new Random().nextInt(TOTAL_MONEY_BOUND);
    }


    private double calculateInterestRate(int rating) {
        return MAX_RATE - (rating / DIVIDER);
    }


    public BankBuilder setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public Bank createBank() {
        var id = generateId();
        var rating = generateRating();
        var totalMoney = generateTotalMoney();
        var calculateInterestRate = calculateInterestRate(rating);

        return new Bank(id, bankName, rating, totalMoney, calculateInterestRate);
    }
}