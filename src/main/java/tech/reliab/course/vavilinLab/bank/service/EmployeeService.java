package tech.reliab.course.vavilinLab.bank.service;

import tech.reliab.course.vavilinLab.bank.entity.Bank;
import tech.reliab.course.vavilinLab.bank.entity.BankAtm;
import tech.reliab.course.vavilinLab.bank.entity.BankOffice;
import tech.reliab.course.vavilinLab.bank.entity.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee createEmployee(String fullName, LocalDate birthDate, String position, Bank bank, boolean remoteWork,
                                   BankOffice bankOffice, boolean canIssueLoans, double salary);

    Optional<Employee> getEmployeeById(int id);

    List<Employee> getAllEmployees();
    List<Employee> getAllEmployeesByBank(Bank bank);

    void updateEmployee(int id, String name);

    void deleteEmployee(int id);

    Employee getEmployeeIfExists(int id);
}
