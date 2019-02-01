import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Formattable;
import java.util.Formatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class AccountBalance implements Formattable {
  private BigDecimal balance;
  private LocalDate date;

  @Override
  public void formatTo(Formatter formatter, int flags, int width, int precision) {
    formatter.format("%s %s",balance,date);
  }
}
