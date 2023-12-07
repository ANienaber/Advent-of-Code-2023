package day7;

public enum CardValue {
    A(14), K(13), Q(12)/*, J(11)*/, T(10), J(1);

    private final int numVal;

    CardValue(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }

}
