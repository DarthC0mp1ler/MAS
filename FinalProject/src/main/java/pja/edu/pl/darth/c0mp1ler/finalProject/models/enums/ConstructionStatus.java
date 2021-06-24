package pja.edu.pl.darth.c0mp1ler.finalProject.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ConstructionStatus {

    Prosperous(2),
    Inspired(1),
    Standard(0),
    Raided(-1),
    Starving(-2),
    Destroyed(-3);

    @Getter
    private final int status;

}
