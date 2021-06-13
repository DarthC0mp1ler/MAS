package pja.edu.pl.darth.c0mp1ler.models.multi_aspectInheritance;

import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

public class EconomyBased implements DevelopmentDirection{

    private float income;
    private Construction construction;

    public EconomyBased(float income, Construction construction) {
        setIncome(income);
        setConstruction(construction);
    }

    public EconomyBased(float income) {
        setIncome(income);
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }
    @Override
    public Construction getConstruction() {
        return construction;
    }
    @Override
    public void setConstruction(Construction construction) {
        if(this.construction == construction){
            return;
        }
        if (construction == null && this.construction.getDirection() != this) {
            this.construction = null;
            return;
        }
        this.construction = construction;
        construction.setDirection(this);
    }

    @Override
    public void develop() {
        double prob = Math.random();
        if(prob > 0.5){
            income++;
        }
    }
}
