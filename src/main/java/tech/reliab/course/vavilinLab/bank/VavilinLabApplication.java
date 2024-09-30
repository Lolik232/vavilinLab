package tech.reliab.course.vavilinLab.bank;

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

import java.io.IOException;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

public class VavilinLabApplication {

    private static final List<String> firstNames = Arrays.asList("Владислав", "Олег", "Кирилл");
    private static final List<String> lastNames = Arrays.asList("Петрович", "Петрович2", "Петрович3");

    private static final Integer banksCount = 5;

    private static List<Bank> initializeBanks() {
        var bankNames = IntStream.range(0, banksCount).boxed().map((index) -> String.format("Супер банк %d", index));
        return bankNames.map((name) -> new BankBuilder().setBankName(name).createBank()).toList();
    }

    static UserService userService = new UserServiceImpl();
    static BankService bankService = new BankServiceImpl(userService);
    static EmployeeService employeeService = new EmployeeServiceImpl(bankService);
    static BankAtmService bankAtmService = new BankAtmServiceImpl(bankService);
    static PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(userService, bankService);
    static CreditAccountService creditAccountService = new CreditAccountServiceImpl(userService, bankService);
    static BankOfficeService bankOfficeService = new BankOfficeServiceImpl(bankService);

    public static void main(String[] args) throws IOException {
        var banks = initializeBanks();
        banks.forEach(VavilinLabApplication::initializeBankInfo);

        System.out.println("Хто я?");
        System.out.println("1. Вывести банк");
        System.out.println("2. Вывести информацию о пользователе");

        var scanner = new Scanner(System.in);

        var option = scanner.nextInt();

        if (option == 1) {
            handleBankOut();
        }

        if (option == 2) {
            handleUserInfoOut();
        }
    }


    private static void handleBankOut() {
        var banks = bankService.getAllBanks();
        for (var bank : banks) {
            System.out.println(bank);
        }

        var scanner = new Scanner(System.in);

        System.out.println("Введите ID банка");
        var id = scanner.nextInt();

        var bank = bankService.getBankById(id);

        var isBankNotPresent = bank.isEmpty();
        if (isBankNotPresent) return;


        System.out.println(bank.get());

        System.out.println("Банкоматы");
        System.out.println(bankAtmService.getAllBankAtmsByBank(bank.get()));

        System.out.println("Офисы");
        System.out.println(bankOfficeService.getAllBankOfficesByBank(bank.get()));


        System.out.println("Сотрудники");
        System.out.println(employeeService.getAllEmployeesByBank(bank.get()));


        System.out.println("Клиенты");
        System.out.println(userService.getUsersByBank(bank.get()));
    }

    private static void handleUserInfoOut() {
        var users = userService.getAllUsers();
        for (var user : users) {
            System.out.println(user);
        }


        var scanner = new Scanner(System.in);

        System.out.println("Введите ID пользователя");
        var id = scanner.nextInt();

        var user = userService.getUserById(id);

        var isUserNotPresent = user.isEmpty();
        if (isUserNotPresent) return;

        var creditAccounts = creditAccountService.getCreditAccountByUserId(user.get().getId());
        var paymentAccounts = paymentAccountService.getAllPaymentAccountsByUserId(user.get().getId());

        System.out.println("Кредитные счета");
        System.out.println(creditAccounts);

        System.out.println("Платёжные счета");
        System.out.println(paymentAccounts);
    }


    private static void initializeBankInfo(Bank bank) {
        bankService.registerBank(bank);

        Integer employeeCount = 5;
        Integer officeCount = 5;
        Integer atmCount = 5;

        List<User> users = Arrays.asList(
                userService.createUser("Ваваилин Владилсав Андреевич", LocalDate.now(), "Программист/Гений/Миллиардер/Филантроп"),
                userService.createUser("Ахахаха Андрей Олегович", LocalDate.now(), "Программист/Гений/Миллиардер/Филантроп"),
                userService.createUser("Ехзехехе Василий Огогого", LocalDate.now(), "Программист/Гений/Миллиардер/Филантроп")
        );


        var offices = IntStream.range(0, officeCount).boxed().map((index) -> bankOfficeService.createBankOffice(
                String.format("Самый лучший офис в мире %d", index),
                "Кутузовский 35",
                true,
                true,
                true,
                true,
                500,
                bank
        )).toList();


        var employee = IntStream.range(0, employeeCount).boxed().map((index) -> employeeService.createEmployee(
                String.format("Иванова Ирина%d Валерьевна", index),
                LocalDate.now(),
                "Прислуга1",
                bank,
                false,
                offices.get(index % offices.size()),
                true,
                30000
        )).toList();

        var bankAtms = IntStream.range(0, atmCount).boxed().map((index) ->
                bankAtmService.createBankAtm(
                        "Лучший в мире банкомат",
                        "Кутузовский 35",
                        bank,
                        offices.get(index % offices.size()),
                        employee.get(index % employee.size()),
                        true,
                        true,
                        500
                )).toList();


        for (var user : users) {
            PaymentAccount paymentAccount = paymentAccountService.createPaymentAccount(user, bank);

            CreditAccount creditAccount = creditAccountService.createCreditAccount(
                    user,
                    bank,
                    LocalDate.now(),
                    8,
                    500000,
                    14,
                    employee.get(user.hashCode() % employee.size()),
                    paymentAccount
            );

            paymentAccountService.createPaymentAccount(user, bank);

            creditAccountService.createCreditAccount(
                    user,
                    bank,
                    LocalDate.now(),
                    12,
                    50002,
                    14,
                    employee.get(user.hashCode() % employee.size()),
                    paymentAccount
            );
        }
        ;
    }

}
