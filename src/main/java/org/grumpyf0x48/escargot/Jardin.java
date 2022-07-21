package org.grumpyf0x48.escargot;

public class Jardin {

    private final int dimension;
    private final Zone[][] zones;

    public Jardin(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException(String.format("La dimension est invalide: %d", dimension));
        }
        this.dimension = dimension;
        this.zones = new Zone[dimension][dimension];
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                zones[x][y] = Zone.NON_PARCOURUE;
            }
        }
    }

    public void marquerParcourue(Position position) {
        zones[position.x()][position.y()] = Zone.PARCOURUE;
    }

    public boolean aEteParcourue(Position position) {
        return zones[position.x()][position.y()] == Zone.PARCOURUE;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                builder.append(zones[x][y]);
                if (x < dimension - 1) {
                    builder.append(' ');
                }
            }
            builder.append('\n');
        }
        return builder.toString();
    }

}
