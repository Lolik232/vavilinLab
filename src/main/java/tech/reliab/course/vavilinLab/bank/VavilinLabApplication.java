package tech.reliab.course.vavilinLab.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.reliab.course.vavilinLab.bank.builders.BankBuilder;
import tech.reliab.course.vavilinLab.bank.entity.Bank;
import tech.reliab.course.vavilinLab.bank.entity.BankAtm;
import tech.reliab.course.vavilinLab.bank.entity.BankOffice;
import tech.reliab.course.vavilinLab.bank.entity.CreditAccount;
import tech.reliab.course.vavilinLab.bank.entity.Employee;
import tech.reliab.course.vavilinLab.bank.entity.PaymentAccount;
import tech.reliab.course.vavilinLab.bank.entity.User;
import tech.reliab.course.vavilinLab.bank.service.BankAtmService;
import tech.reliab.course.vavilinLab.bank.service.BankOfficeService;
import tech.reliab.course.vavilinLab.bank.service.BankService;
import tech.reliab.course.vavilinLab.bank.service.CreditAccountService;
import tech.reliab.course.vavilinLab.bank.service.EmployeeService;
import tech.reliab.course.vavilinLab.bank.service.PaymentAccountService;
import tech.reliab.course.vavilinLab.bank.service.UserService;
import tech.reliab.course.vavilinLab.bank.service.impl.BankAtmServiceImpl;
import tech.reliab.course.vavilinLab.bank.service.impl.BankOfficeServiceImpl;
import tech.reliab.course.vavilinLab.bank.service.impl.BankServiceImpl;
import tech.reliab.course.vavilinLab.bank.service.impl.CreditAccountServiceImpl;
import tech.reliab.course.vavilinLab.bank.service.impl.EmployeeServiceImpl;
import tech.reliab.course.vavilinLab.bank.service.impl.PaymentAccountServiceImpl;
import tech.reliab.course.vavilinLab.bank.service.impl.UserServiceImpl;

import java.time.LocalDate;

@SpringBootApplication
public class VavilinLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(VavilinLabApplication.class, args);
        var bankBuilder = new BankBuilder();
        var bank = bankBuilder.setBankName("Алалай Банк").createBank();


        UserService userService = new UserServiceImpl();
        User user = userService.createUser("Ваваилин Владилсав Андреевич", LocalDate.now(), "Программист/Гений/Миллиардер/Филантроп");

        BankService bankService = new BankServiceImpl(userService);
        bankService.registerBank(bank);

        BankOfficeService bankOfficeService = new BankOfficeServiceImpl(bankService);
        BankOffice bankOffice = bankOfficeService.createBankOffice(
                "Самый лучший офис с мире",
                "Кутузовский 35",
                true,
                true,
                true,
                true,
                500,
                bank
        );

        EmployeeService employeeService = new EmployeeServiceImpl(bankService);
        Employee employee = employeeService.createEmployee(
                "Иванова Ирина Валерьевна",
                LocalDate.now(),
                "Прислуга",
                bank,
                false,
                bankOffice,
                true,
                30000
        );

        BankAtmService bankAtmService = new BankAtmServiceImpl(bankService);
        BankAtm bankAtm = bankAtmService.createBankAtm(
                "Лучший в мире банкомат",
                "Кутузовский 35",
                bank,
                bankOffice,
                employee,
                true,
                true,
                500
        );

        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(userService, bankService);
        PaymentAccount paymentAccount = paymentAccountService.createPaymentAccount(user, bank);

        CreditAccountService creditAccountService = new CreditAccountServiceImpl(userService, bankService);
        CreditAccount creditAccount = creditAccountService.createCreditAccount(
                user,
                bank,
                LocalDate.now(),
                8,
                500000,
                14,
                employee,
                paymentAccount
        );

        System.out.println(bank);
        System.out.println(user);
        System.out.println(bankOffice);
        System.out.println(employee);
        System.out.println(bankAtm);
        System.out.println(paymentAccount);
        System.out.println(creditAccount);
    }
}
