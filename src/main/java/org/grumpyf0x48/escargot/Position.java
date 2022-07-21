package org.grumpyf0x48.escargot;

public record Position(int x, int y, int dimension) {

    public Position(int dimension) {
        this(0, 0, dimension);
    }

    public Position {
        if (x < 0 || x >= dimension)
            throw new IllegalArgumentException(String.format("Position invalide: x=%d n'est pas entre 0 et %d", x, dimension - 1));

        if (y < 0 || y >= dimension)
            throw new IllegalArgumentException(String.format("Position invalide: y=%d n'est pas entre 0 et %d", y, dimension - 1));
    }

    public Position suivante(Direction direction) {
        try {
            return switch (direction) {
                case A_DROITE -> new Position(x + 1, y, dimension);
                case EN_BAS -> new Position(x, y + 1, dimension);
                case A_GAUCHE -> new Position(x - 1, y, dimension);
                case EN_HAUT -> new Position(x, y - 1, dimension);
            };
        } catch (IllegalArgumentException exception) {
            throw new IllegalStateException(String.format("On sort du jardin: %s %s", this, direction), exception);
        }
    }
}
