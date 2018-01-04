// "static void main" must be defined in a public class.
public class Solution {
    public static void main(String[] args) {
        new Solution();
    }
    public Solution() {
        int mat[][] = { { 6, 7, 8, 9 },
                        { 4, 6, 7, 8 },
                        { 1, 4, 6, 7 },
                        { 0, 1, 4, 6 },
                        { 2, 0, 1, 4 }
                      };
        if (isToepliz(mat))
            System.out.println("Matrix is a Toepliz ");
        else
            System.out.println("Matrix is not a Toepliz ");
    }
    
    boolean isToepliz(int[][] mat) {
        // 0 ~ mat[0].length
        for (int i = 0; i < mat[0].length; i++) {
            if (!checkDiagonal(mat, 0, i)) {
                return false;
            }
        }
        
        // 1 ~ mat.length
        for (int i = 1; i < mat.length; i++) {
            if (!checkDiagonal(mat, i, 0)) {
                return false;
            }
        }
        return true;
    }
    boolean checkDiagonal(int[][] mat, int x, int y) {
        int val = mat[x][y];
        while (x < mat.length && y < mat[0].length) {
            //System.out.println(x + " " + y);
            if (val != mat[x++][y++]) {
                return false;
            }
        }
        return true;
    }
    
}