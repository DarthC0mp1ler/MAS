package pja.edu.pl.darth.c0mp1ler.models;

public enum ConstructionStatus {
    _PROSPEROUS_(2,"prosperous"),
    _INSPIRED_(1,"inspired"),
    _STANDARD_(0,"living"),
    _RAIDED_(-1,"raided"),
    _STARVING_(-2,"starving"),
    _DESTRUCTED_(-3,"destructed");

    private final int incomeIndicator;
    private final String printValue;

    ConstructionStatus(int incomeIndicator, String printValue) {
        this.incomeIndicator = incomeIndicator;
        this.printValue = printValue;
    }

    public int getIncomeIndicator() {
        return incomeIndicator;
    }
    @Override
    public String toString(){
        return printValue;
    }

}
