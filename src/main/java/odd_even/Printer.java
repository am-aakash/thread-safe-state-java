package odd_even;

import lombok.NonNull;
import lombok.SneakyThrows;

public class Printer implements Runnable {
  private final int step;
  private final State state;
  private int currentValue;
  private final PrintType currentPrintType;
  private final int maxValue;

  public Printer(@NonNull final Integer startValue, final int step, @NonNull final State state,
      @NonNull final PrintType currentPrintType,
      @NonNull Integer maxValue) {
    this.currentValue = startValue;
    this.step = step;
    this.state = state;
    this.currentPrintType = currentPrintType;
    this.maxValue = maxValue;
  }

  @SneakyThrows
  @Override
  public void run() {
    while (currentValue <= maxValue) {
      synchronized (state) {
        while (this.currentPrintType != state.getNextToPrint()) {
          state.wait();
          // thread will wait for state.setNextToPrint(next) in other thread to complete
          // and change the state to next
        }
        System.out.println(currentPrintType.toString() + ": " + currentValue);
        currentValue += step;

        state.setNextToPrint((currentPrintType == PrintType.ODD) ? PrintType.EVEN : PrintType.ODD);
        // changing state.nextToPrint alternatively ODD and EVEN

        state.notifyAll(); // Wakes up all threads that are waiting on this object's monitor/wait
      }
    }
  }
}
