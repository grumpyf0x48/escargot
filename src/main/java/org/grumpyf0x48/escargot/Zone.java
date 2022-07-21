package org.grumpyf0x48.escargot;

public enum Zone {
    PARCOURUE,
    NON_PARCOURUE;

    @Override
    public String toString() {
        return this == PARCOURUE ? "X" : ".";
    }
}
