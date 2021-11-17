package n_queen;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solver {

    Set<Integer> col = new HashSet<>();
    Set<Integer> posDiag = new HashSet<>(); // row + col
    Set<Integer> negDiag = new HashSet<>(); // row - col
    static int counterSolutions = 1;

    public String[][] init(int n) {

        String[][] board = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = ".";
            }
        }
        return board;
    }

    public void solve(String[][] board, int n, int row) {

        if (row == n) {
            print(board);
            return;
        }

        // i represents the column
        for (int i = 0; i < n; i++) {
            if (col.contains(i) || posDiag.contains(row+i) || negDiag.contains(row-i)) {
                continue;
            }

            col.add(i);
            posDiag.add(row+i);
            negDiag.add(row-i);
            board[row][i] = "Q";

            solve(board, n, row+1);

            col.remove(i);
            posDiag.remove(row+i);
            negDiag.remove(row-i);
            board[row][i] = ".";
        }
    }

    private void print(String[][] board) {

        System.out.println("solution number: " + counterSolutions);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        counterSolutions++;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        Solver s = new Solver();
        String[][] board = s.init(x);
        s.solve(board, x, 0);
    }
}
