/**
 * Solutions to Cracking the coding interview
 *
 * Tests are in Clojure and put in the module `katas.clj.java-ctci-test`.
 **/
package katas.java;

public class CTCI {

    /**
     * Rotate square matrix in 90 degree
     *
     * qn: 1.4
     **/
    public static Object[][] rotateSquareMatrix(Object[][] m) {
        assert m.length == m[0].length;

        /*
         * Take a square rectangle out of matrix, e.g.:
         *
         *    1 2 3 4
         *    d     6
         *    c     7
         *    b a 9 8
         *
         * A rotation of global matrix would move those entries into each
         * other only on the square, and all exchanges happen only on the
         * rectangle.
         *
         * Assume a clock-wise rotation, entry 2 would move to 6, 6 -> 9, 9 ->
         * c, c -> 2, which forms a cycle:
         *
         *    2 -> 6 -> 9 -> c -> 2
         *    1 -> 4 -> 8 -> b -> 1
         *
         * The algorithm goes from outer rectangle into inner ones, and rotates
         * them iteratively.
         *
         */
        for (int x = 0; x < (m.length / 2); x++) {
            int num_columns = m.length    - x;
            int offset      = num_columns - 1;
            for (int i = 1; i < num_columns; i++) {
                // start from (x, x+i), exchange entries in cycle
                Object temp_val = m[x][x+i];
                m[x][x+i]               = m[x+offset-i][x];
                m[x+offset-i][x]        = m[x+offset][x+offset-i];
                m[x+offset][x+offset-i] = m[x+i][x+offset];
                m[x+i][x+offset]        = temp_val;
            }
        }
        return m;
    }
}