package basic;

public class buttles {

    static int[][] build(int n, int m) {

        int number = (n + 1) * (m + 1);
        int[][] mat = new int[number][number];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {

                int k = getIndex(i, j, m);

                mat[k][getIndex(i, 0, m)] = 1;
                mat[k][getIndex(0, j, m)] = 1;

                mat[k][getIndex(n, j, m)] = 1;
                mat[k][getIndex(i, m, m)] = 1;

                mat[k][getIndex(Math.min(i + j, n), i + j - Math.min(i + j, n), m)] = 1;
                mat[k][getIndex(i + j - Math.min(i + j, m), Math.min(i + j, m), m)] = 1;
            }
        }
        for (int i = 0; i < mat.length; i++) {
            mat[i][i] = 0;
        }
        return mat;
    }

    static int getIndex(int i, int j, int k) {
        return (k + 1) * i + j;
    }

    public static void main(String[] args) {

        int[][] mat = build(1, 2);

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }
        //	howManyOnes(mat);
    }

}
