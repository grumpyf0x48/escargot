package org.grumpyf0x48.escargot;

public class Escargot {

    private final Jardin jardin;
    private Position position;
    private Direction direction;

    public Escargot(int dimension) {
        this.jardin = new Jardin(dimension);
        this.position = new Position(dimension);
        this.jardin.marquerParcourue(position);
        this.direction = Direction.A_DROITE;
    }

    public Jardin getJardin() {
        return jardin;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public Position avance() {
        Position positionSuivante;
        try {
            positionSuivante = verifierOccupation(position.suivante(direction));
        } catch (IllegalStateException illegalStateException) {
            var directionSuivante = direction.suivante();
            positionSuivante = verifierOccupation(position.suivante(directionSuivante));
            direction = directionSuivante;
        }
        position = positionSuivante;
        jardin.marquerParcourue(position);
        return position;
    }

    public void parcourt() {
        Exception exception = null;
        while (exception == null) {
            try {
                System.out.println(jardin.toString());
                avance();
            } catch (IllegalStateException illegalStateException) {
                exception = illegalStateException;
            }
        }
    }

    private Position verifierOccupation(Position position) {
        if (jardin.aEteParcourue(position)) {
            throw new IllegalStateException(String.format("Emplacement utilisé: %s", position));
        }
        return position;
    }
}
