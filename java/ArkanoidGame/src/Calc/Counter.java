package Calc;

public class Counter {

    // local variables

    private int counter = 0;

    // add number to current count.
    public void increase(int number) {
        this.counter += number;
    }

    // subtract number from current count.
    public void decrease(int number) {
        this.counter -= number;
    }

    // get current count.
    public int getValue() {
        return this.counter;
    }
}
