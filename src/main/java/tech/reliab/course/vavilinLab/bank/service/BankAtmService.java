package tech.reliab.course.vavilinLab.bank.service;

import tech.reliab.course.vavilinLab.bank.entity.Bank;
import tech.reliab.course.vavilinLab.bank.entity.BankAtm;
import tech.reliab.course.vavilinLab.bank.entity.BankOffice;
import tech.reliab.course.vavilinLab.bank.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface BankAtmService {
    BankAtm createBankAtm(String name,
                          String address, Bank bank, BankOffice location, Employee employee,
                          boolean cashWithdrawal, boolean cashDeposit, double maintenanceCost);

    Optional<BankAtm> getBankAtmById(int id);

    List<BankAtm> getAllBankAtms();

    List<BankAtm> getAllBankAtmsByBank(Bank bank);

    void updateBankAtm(int id, String name);

    void deleteBankAtm(int id);
}
