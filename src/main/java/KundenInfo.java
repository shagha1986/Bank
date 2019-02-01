import java.util.ArrayList;
import java.util.Formattable;
import java.util.Formatter;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KundenInfo implements Formattable {

  private String name;
  private String family;
  private Long id;
  private List<Account> accounts = new ArrayList<>();

  public KundenInfo(String name, String family, Long id) {
    this.name = name;
    this.family = family;
    this.id = id;
  }

  @Override
  public void formatTo(Formatter formatter, int flags, int width, int precision) {
    formatter.format("%s, %s (%d)", name, family, id);
  }

  public void addAccount(Account account) {
    if (!accounts.contains(account)) {
      accounts.add(account);
    }
  }
}
