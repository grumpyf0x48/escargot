package org.grumpyf0x48.escargot;

import java.util.Random;
import static java.lang.Integer.parseInt;

public class Demo {
    public static void main(String[] args) {
        var dimension = args.length > 0 ? parseInt(args[0]) : new Random().nextInt(4) + 1;
        new Escargot(dimension).parcourt();
    }
}
