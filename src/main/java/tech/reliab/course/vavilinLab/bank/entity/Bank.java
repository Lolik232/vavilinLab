package tech.reliab.course.vavilinLab.bank.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Bank {
    private int id;
    private String name;
    private int officeCount = 0;
    private int atmCount = 0;
    private int employeeCount = 0;
    private int clientCount = 0;
    private int rating;
    private double totalMoney;
    private double interestRate;

    public Bank(int id, String name, int rating, double totalMoney, double interestRate) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.totalMoney = totalMoney;
        this.interestRate = interestRate;
    }

    public void setOfficeCount(int officeCount) {
        if(officeCount < 0) return;
        this.officeCount = officeCount;
    }

    public void setAtmCount(int atmCount) {
        if(atmCount < 0) return;
        this.atmCount = atmCount;
    }

    public void setEmployeeCount(int employeeCount) {
        if(employeeCount < 0) return;
        this.employeeCount = employeeCount;
    }

    public void setClientCount(int clientCount) {
        if(clientCount < 0) return;
        this.clientCount = clientCount;
    }
}
