import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formattable;
import java.util.Formatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Account implements Formattable {

  private Long id;
  private AccountName accountName;
  private List<AccountBalance> accountBalances = new ArrayList<>();

  public Account(Long id, AccountName accountName) {
    this.id = id;
    this.accountName = accountName;
  }

  @Override
  public void formatTo(Formatter formatter, int flags, int width, int precision) {
    formatter.format("%d %s", id, accountName);
  }

  public void addBalance(AccountBalance accountBalance) {
    if (!accountBalances.contains(accountBalance)) {
      accountBalances.add(accountBalance);
    }

  }

  public Optional<AccountBalance> getMoneyforToday() {
    LocalDate today = LocalDate.now();
    for (AccountBalance balance : accountBalances) {
      if (balance.getDate().equals(today)) {
        return Optional.of(balance);
      }
    }
    return Optional.empty();
  }

  public Optional<AccountBalance> getMoneyforAnyDay(LocalDate myDate) {


    for (AccountBalance balance : accountBalances) {
      if (balance.getDate().equals(myDate)) {

        return Optional.of(balance);
      }

    }

    return Optional.empty();
  }

  public  BigDecimal proJahr(AccountBalance moneyforToday){

    BigDecimal moneyAfterOneYear = moneyforToday.getBalance().multiply(new BigDecimal(0.01)).add(moneyforToday.getBalance());
    return moneyAfterOneYear;

  }
}


