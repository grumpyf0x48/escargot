package org.grumpyf0x48.escargot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.grumpyf0x48.escargot.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PositionTest {

    @Nested
    @DisplayName("Quand on reste dans le jardin alors on se déplace correctement")
    class DansLeJardinTest {

        @Test
        @DisplayName("Quand on avance à droite alors x augmente de 1")
        void quand_on_avance_a_droite_alors_x_augmente_de_1() {
            var position = new Position(3);

            var position1 = position.suivante(A_DROITE);
            assertEquals(new Position(1, 0, 3), position1);

            var position2 = position1.suivante(A_DROITE);
            assertEquals(new Position(2, 0, 3), position2);
        }

        @Test
        @DisplayName("Quand on avance en bas alors y augmente de 1")
        void quand_on_avance_en_bas_alors_y_augmente_de_1() {
            var position = new Position(2, 0, 3);

            var position1 = position.suivante(EN_BAS);
            assertEquals(new Position(2, 1, 3), position1);

            var position2 = position1.suivante(EN_BAS);
            assertEquals(new Position(2, 2, 3), position2);
        }

        @DisplayName("Quand on avance à gauche alors x diminue de 1")
        @Test
        void quand_on_avance_a_gauche_alors_x_diminue_de_1() {
            var position = new Position(2, 2, 3);

            var position1 = position.suivante(A_GAUCHE);
            assertEquals(new Position(1, 2, 3), position1);

            var position2 = position1.suivante(A_GAUCHE);
            assertEquals(new Position(0, 2, 3), position2);
        }


        @Test
        @DisplayName("Quand on avance en haut alors y diminue de 1")
        void quand_on_avance_en_haut_alors_y_diminue_de_1() {
            var position = new Position(0, 2, 3);

            var position1 = position.suivante(EN_HAUT);
            assertEquals(new Position(0, 1, 3), position1);

            var position2 = position1.suivante(EN_HAUT);
            assertEquals(new Position(0, 0, 3), position2);
        }
    }

    @Nested
    @DisplayName("Quand on sort du jardin alors on a une exception")
    class HorsDuJardinTest {

        @Test
        @DisplayName("Quand on avance trop à droite alors on a une exception")
        void quand_on_avance_trop_a_droite_alors_on_a_une_exception() {
            var position = new Position(2, 0, 3);

            var exception = assertThrows(IllegalStateException.class, () -> position.suivante(A_DROITE));

            assertEquals("On sort du jardin: Position[x=2, y=0, dimension=3] Direction[A_DROITE]", exception.getMessage());
        }

        @Test
        @DisplayName("Quand on avance trop en bas alors on a une exception")
        void quand_on_avance_trop_en_bas_alors_on_a_une_exception() {
            var position = new Position(0, 2, 3);

            var exception = assertThrows(IllegalStateException.class, () -> position.suivante(EN_BAS));

            assertEquals("On sort du jardin: Position[x=0, y=2, dimension=3] Direction[EN_BAS]", exception.getMessage());
        }

        @Test
        @DisplayName("Quand on avance trop à gauche alors on a une exception")
        void quand_on_avance_trop_a_gauche_alors_on_a_une_exception() {
            var position = new Position(0, 2, 3);

            var exception = assertThrows(IllegalStateException.class, () -> position.suivante(A_GAUCHE));

            assertEquals("On sort du jardin: Position[x=0, y=2, dimension=3] Direction[A_GAUCHE]", exception.getMessage());
        }

        @Test
        @DisplayName("Quand on avance trop en haut alors on a une exception")
        void quand_on_avance_trop_en_haut_alors_on_a_une_exception() {
            var position = new Position(3);

            var exception = assertThrows(IllegalStateException.class, () -> position.suivante(EN_HAUT));

            assertEquals("On sort du jardin: Position[x=0, y=0, dimension=3] Direction[EN_HAUT]", exception.getMessage());
        }
    }
}
