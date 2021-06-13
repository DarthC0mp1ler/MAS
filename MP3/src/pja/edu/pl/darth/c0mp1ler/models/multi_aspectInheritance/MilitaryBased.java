package pja.edu.pl.darth.c0mp1ler.models.multi_aspectInheritance;

import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

public class MilitaryBased implements DevelopmentDirection{

    private float attackForce;
    private Construction construction;

    public MilitaryBased(float attackForce, Construction construction) {
        setAttackForce(attackForce);
        setConstruction(construction);
    }

    public MilitaryBased(float attackForce) {
        this.attackForce = attackForce;
    }

    public float getAttackForce() {
        return attackForce;
    }

    public void setAttackForce(float attackForce) {
        this.attackForce = attackForce;
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
        attackForce++;
    }
}
