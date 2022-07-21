package org.grumpyf0x48.escargot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.util.stream.IntStream.range;
import static org.grumpyf0x48.escargot.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

class EscargotTest {

    @Nested
    @DisplayName("Quand on crée un escargot alors il a les caractéristiques initiales attendues")
    class CreationTest {

        @Test
        @DisplayName("Quand on crée un escargot alors il est à l'emplacement (0,0) et cet emplacement a été marqué parcouru")
        void quand_on_cree_un_escargot_alors_il_est_a_l_emplacement_0_0_et_cet_emplacement_a_ete_marque_parcouru() {
            var escargot = new Escargot(3);
            var jardin = escargot.getJardin();
            var position0_0 = new Position(0, 0, 3);

            var position = escargot.getPosition();

            assertAll(
                    () -> assertEquals(position0_0, position),
                    () -> assertTrue(jardin.aEteParcourue(position))
            );
        }

        @Test
        @DisplayName("Quand on crée un escargot alors il se déplace vers la droite")
        void quand_on_cree_un_escargot_alors_il_se_deplace_vers_la_droite() {
            var escargot = new Escargot(3);

            assertEquals(A_DROITE, escargot.getDirection());
        }
    }

    @Nested
    @DisplayName("Quand un escargot avance sur un jardin alors celui-ci est parcouru en colimaçon")
    class DeplacementTest {

        @Test
        @DisplayName("Quand un escargot avance sur un jardin de dimension 1x1 alors il a fini en 0 itérations")
        void quand_un_escargot_avance_sur_un_jardin_de_dimension_1x1_alors_il_a_fini_en_0_iterations() {
            var escargot = new Escargot(1);
            var jardin = escargot.getJardin();
            afficher(jardin);

            var exception = assertThrows(IllegalStateException.class, escargot::avance);
            assertNotNull(exception.getMessage());
            assertTrue(exception.getMessage().startsWith("On sort du jardin"));
        }

        @Test
        @DisplayName("Quand un escargot avance sur un jardin de dimension 2x2 alors il a fini en 3 itérations")
        void quand_un_escargot_avance_sur_un_jardin_de_dimension_2x2_alors_il_a_fini_en_3_iterations() {
            var escargot = new Escargot(2);
            var jardin = escargot.getJardin();
            afficher(jardin);

            var position1 = escargot.avance();
            afficher(jardin);
            assertAll(
                    () -> assertEquals(new Position(1, 0, 2), position1),
                    () -> assertEquals(A_DROITE, escargot.getDirection())
            );

            var position2 = escargot.avance();
            afficher(jardin);
            assertAll(
                    () -> assertEquals(new Position(1, 1, 2), position2),
                    () -> assertEquals(EN_BAS, escargot.getDirection())
            );

            var position3 = escargot.avance();
            afficher(jardin);
            assertAll(
                    () -> assertEquals(new Position(0, 1, 2), position3),
                    () -> assertEquals(A_GAUCHE, escargot.getDirection())
            );

            var exception = assertThrows(IllegalStateException.class, escargot::avance);
            assertNotNull(exception.getMessage());
            assertTrue(exception.getMessage().startsWith("Emplacement utilisé"));
        }

        @Test
        @DisplayName("Quand un escargot avance sur un jardin de dimension 2x2 alors le jardin est parcouru en colimaçon")
        void quand_un_escargot_avance_sur_un_jardin_de_dimension_2x2_alors_le_jardin_est_parcouru_en_colimacon() {
            var escargot = new Escargot(2);
            var jardin = escargot.getJardin();
            assertEquals("""
                    X .
                    . .
                    """, jardin.toString());

            escargot.avance();
            assertEquals("""
                    X X
                    . .
                    """, jardin.toString());

            escargot.avance();
            assertEquals("""
                    X X
                    . X
                    """, jardin.toString());

            escargot.avance();
            assertEquals("""
                    X X
                    X X
                    """, jardin.toString());

            assertThrows(IllegalStateException.class, escargot::avance);
        }

        @Test
        @DisplayName("Quand un escargot avance sur un jardin de dimension 3x3 alors il a fini en 8 itérations")
        void quand_un_escargot_avance_sur_un_jardin_de_dimension_3x3_alors_il_a_fini_en_8_iterations() {
            var escargot = new Escargot(3);
            var jardin = escargot.getJardin();
            afficher(jardin);

            var position1 = escargot.avance();
            afficher(jardin);
            assertAll(
                    () -> assertEquals(new Position(1, 0, 3), position1),
                    () -> assertEquals(A_DROITE, escargot.getDirection())
            );

            var position2 = escargot.avance();
            afficher(jardin);
            assertAll(
                    () -> assertEquals(new Position(2, 0, 3), position2),
                    () -> assertEquals(A_DROITE, escargot.getDirection())
            );

            var position3 = escargot.avance();
            afficher(jardin);
            assertAll(
                    () -> assertEquals(new Position(2, 1, 3), position3),
                    () -> assertEquals(EN_BAS, escargot.getDirection())
            );

            var position4 = escargot.avance();
            afficher(jardin);
            assertAll(
                    () -> assertEquals(new Position(2, 2, 3), position4),
                    () -> assertEquals(EN_BAS, escargot.getDirection())
            );

            var position5 = escargot.avance();
            afficher(jardin);
            assertAll(
                    () -> assertEquals(new Position(1, 2, 3), position5),
                    () -> assertEquals(A_GAUCHE, escargot.getDirection())
            );

            var position6 = escargot.avance();
            afficher(jardin);
            assertAll(
                    () -> assertEquals(new Position(0, 2, 3), position6),
                    () -> assertEquals(A_GAUCHE, escargot.getDirection())
            );

            var position7 = escargot.avance();
            afficher(jardin);
            assertAll(
                    () -> assertEquals(new Position(0, 1, 3), position7),
                    () -> assertEquals(EN_HAUT, escargot.getDirection())
            );

            var position8 = escargot.avance();
            afficher(jardin);
            assertAll(
                    () -> assertEquals(new Position(1, 1, 3), position8),
                    () -> assertEquals(A_DROITE, escargot.getDirection())
            );

            var exception = assertThrows(IllegalStateException.class, escargot::avance);
            assertNotNull(exception.getMessage());
            assertTrue(exception.getMessage().startsWith("Emplacement utilisé"));
        }

        @ParameterizedTest
        @ValueSource(ints = {4, 5, 10, 30, 50, 100})
        @DisplayName("Quand un escargot avance sur un jardin de dimension nxn alors il a fini en n*n-1 itérations")
        void quand_un_escargot_avance_sur_un_jardin_de_dimension_n_x_n_alors_il_a_fini_en_n_fois_n_moins_1_iterations(int dimension) {
            var escargot = new Escargot(dimension);

            range(0, dimension * dimension - 1).forEach(iteration -> escargot.avance());

            var exception = assertThrows(IllegalStateException.class, escargot::avance);
            assertNotNull(exception.getMessage());
            assertTrue(exception.getMessage().startsWith("Emplacement utilisé"));
        }
    }

    private static final boolean DOIT_AFFICHER = Boolean.getBoolean("DEBUG");

    private static void afficher(Jardin jardin) {
        if (DOIT_AFFICHER) {
            System.out.println(jardin);
        }
    }
}