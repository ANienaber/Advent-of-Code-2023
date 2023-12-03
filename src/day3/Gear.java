package day3;

public class Gear {

    private int num1;
    private int num2;
    private int mul;
    private Coordinates cor;



    private boolean tooManyNumbers;

    public Gear(Coordinates cor) {
        num1 = 0;
        num2 = 0;
        mul = 0;
        this.cor = cor;
        tooManyNumbers = false;
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getMul() {
        return mul;
    }

    public boolean isTooManyNumbers() {
        return tooManyNumbers;
    }

    public Coordinates getCor() {
        return cor;
    }

    public void addNumber(int num){
        if (num1 == 0){
            num1 = num;
        } else if (num2 == 0){
            num2 = num;
            mul = num1 * num2;
        } else {
            mul = 0;
            tooManyNumbers = true;
        }
    }
}
