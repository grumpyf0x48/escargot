package org.grumpyf0x48.escargot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class JardinTest {

    @Test
    @DisplayName("Quand on crée un jardin avec une dimension de 0 alors on a une exception")
    void quand_on_cree_un_jardin_avec_une_dimension_de_0_alors_on_a_une_exception() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Jardin(0));
        assertNotNull(exception.getMessage());
        assertEquals("La dimension est invalide: 0", exception.getMessage());
    }

    @Test
    @DisplayName("Quand on crée un jardin avec une dimension négative alors on a une exception")
    void quand_on_cree_un_jardin_avec_une_dimension_negative_alors_on_a_une_exception() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Jardin(-17));
        assertNotNull(exception.getMessage());
        assertEquals("La dimension est invalide: -17", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    @DisplayName("Quand on crée un jardin alors aucun des emplacements n'a été parcouru")
    void quand_on_cree_un_jardin_alors_aucun_des_emplacements_n_a_ete_parcouru(int dimension) {
        var jardin = new Jardin(dimension);
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                var position = new Position(x, y, dimension);
                assertFalse(jardin.aEteParcourue(position));
            }
        }
    }
}