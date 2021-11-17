package sudoku;

public class Solver {

    public boolean solve(int[][] grid) {

        int[] pos = findEmpty(grid);
        if (pos == null) {
            return true;
        }

        int row = pos[0];
        int col = pos[1];
        for (int i = 1; i <= 9; i++) {
            if (valid(grid, i, pos)) {
                grid[row][col] = i;

                if (solve(grid)) {
                    return true;
                }

                grid[row][col] = 0;
            }
        }
        return false;
    }

    private boolean valid(int[][] grid, int num, int[] pos) {

        //check row
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[pos[0]][i] == num) {
                return false;
            }
        }

        //check col
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][pos[1]] == num) {
                return false;
            }
        }

        //check box 3*3
        int sRow = pos[0] - pos[0] % 3;
        int sCol = pos[1] - pos[1] % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + sRow][j + sCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[] findEmpty(int[][] grid) {

        int[] res = new int[2];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return null;
    }

    static void print(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int[][] grid = {
            {7, 8, 0, 4, 0, 0, 1, 2, 0},
            {6, 0, 0, 0, 7, 5, 0, 0, 9},
            {0, 0, 0, 6, 0, 1, 0, 7, 8},
            {0, 0, 7, 0, 4, 0, 2, 6, 0},
            {0, 0, 1, 0, 5, 0, 9, 3, 0},
            {9, 0, 4, 0, 6, 0, 0, 0, 5},
            {0, 7, 0, 3, 0, 0, 0, 1, 2},
            {1, 2, 0, 0, 0, 7, 4, 0, 0},
            {0, 4, 9, 2, 0, 6, 0, 0, 7}};

        Solver s = new Solver();
        if (s.solve(grid)) {
            print(grid);
        }
        else {
            System.out.println("can't solve");
        }

    }
}