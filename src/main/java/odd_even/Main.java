package odd_even;

public class Main {
    public static void main(String[] args) {
        final State state = new State(PrintType.ODD);
        // state containing nect PrintType will be synchronized
        // helping in threads to operate alternatively

        final Printer oddPrinter = new Printer(1, 2, state, PrintType.ODD, 50);
        final Printer evenPrinter = new Printer(2, 2, state, PrintType.EVEN, 50);

        final Thread oddThread = new Thread(oddPrinter);
        final Thread evenThread = new Thread(evenPrinter);

        oddThread.start();
        evenThread.start();
    }
}