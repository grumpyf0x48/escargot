package org.grumpyf0x48.escargot;

public enum Direction {
    A_DROITE,
    EN_BAS,
    A_GAUCHE,
    EN_HAUT;

    public Direction suivante() {
        return switch (this) {
            case A_DROITE -> EN_BAS;
            case EN_BAS -> A_GAUCHE;
            case A_GAUCHE -> EN_HAUT;
            case EN_HAUT -> A_DROITE;
        };
    }

    @Override
    public String toString() {
        return "Direction[" + name() + "]";
    }
}
