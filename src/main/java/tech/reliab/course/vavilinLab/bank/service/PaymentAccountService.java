package tech.reliab.course.vavilinLab.bank.service;

import tech.reliab.course.vavilinLab.bank.entity.Bank;
import tech.reliab.course.vavilinLab.bank.entity.PaymentAccount;
import tech.reliab.course.vavilinLab.bank.entity.User;

import java.util.List;
import java.util.Optional;

public interface PaymentAccountService {

    PaymentAccount createPaymentAccount(User user, Bank bank);

    Optional<PaymentAccount> getPaymentAccountById(int id);

    List<PaymentAccount> getAllPaymentAccounts();

    void updatePaymentAccount(int id, Bank bank);

    void deletePaymentAccount(int id);
}
