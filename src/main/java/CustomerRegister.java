import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class CustomerRegister {

  public static void main(String[] args) {

    KundenInfo info = new KundenInfo("Gustav", "Ehlbeck", 1L);
    Account account = new Account(1L, AccountName.TAGESGELD);
    Account account2 = new Account(2L, AccountName.GIROKONTO);
    AccountBalance balance = new AccountBalance(new BigDecimal(2500), LocalDate.now());
    AccountBalance balance2 = new AccountBalance(new BigDecimal(500), LocalDate.now().minusDays(1L));
    info.addAccount(account);
    account.addBalance(balance);
    info.addAccount(account2);
    account2.addBalance(balance2);

    String format = String.format("%s", info);

    String account1 = String.format("%s", account);
    String balanceInfo = String.format("%s", balance);

//    LocalDate myDate;
    //  LocalDate myDate2;
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter the Date(d/MM/yyyy):");
    String date = scanner.next();
    String date2 = scanner.next();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate myDate = LocalDate.parse(date, dateTimeFormatter);
    LocalDate myDate2 = LocalDate.parse(date2, dateTimeFormatter);
    Period period = myDate.until(myDate2);
    System.out
        .println("Days between: " + period.getDays() + " Months between: " + period.getMonths() + " year between: " + period.getYears());

    for (Account acc : info.getAccounts()) {

      Optional<AccountBalance> moneyforToday =acc.getMoneyforToday();
      // Optional<AccountBalance> moneyforToday = acc.getMoneyforAnyDay(myDate);

      String stringBalance = "Keine";
      if (moneyforToday.isPresent()) {
        BigDecimal acb = moneyforToday.get().getBalance()
            .multiply(new BigDecimal(period.getMonths() / 12d))
            .multiply(new BigDecimal(0.01))
            .add(moneyforToday.get().getBalance())
            .setScale(2, RoundingMode.HALF_EVEN);
        //stringBalance = moneyforToday.get().getBalance().toString();
        BigDecimal proJahr = acc.proJahr(moneyforToday.get());
        System.out.println("Zinsen: " + acb);
      }

      System.out.println("Acount name: " + acc.getAccountName() + " Balances: " + stringBalance);


    }

    System.out.println(format + "\n" + account1 + "\n" + balanceInfo);


  }

}
