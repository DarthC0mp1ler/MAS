package pja.edu.pl.darth.c0mp1ler.models;

public enum ConstructionType {
    _CITY_("city"),
    _VILLAGE_("village"),
    _CASTLE_("castle"),
    _FORTRESS_("fortress"),
    _TEMPLE_("temple");

    private final String printValue;

    ConstructionType(String value){
        printValue = value;
    }

    @Override
    public String toString(){
        return printValue;
    }
}
