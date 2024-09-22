package odd_even;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class State {
  private PrintType nextToPrint;

  public State(@NonNull final PrintType nextToPrint) {
    this.nextToPrint = nextToPrint;
  }
}
