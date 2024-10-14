import java.util.Scanner;
import java.util.Random;

public class FindTheShip {
    public static void main(String[] args) {
        // Initialize the board, 5x5 grid
        char[][] board = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = '-'; // empty positions
            }
        }

        // Randomly place two ships (represented by 'S') on the board
        Random random = new Random();
        int[] ship1 = {random.nextInt(5), random.nextInt(5)};
        int[] ship2 = {random.nextInt(5), random.nextInt(5)};
        // Make sure the two ships are not in the same place
        while (ship1[0] == ship2[0] && ship1[1] == ship2[1]) {
            ship2[0] = random.nextInt(5);
            ship2[1] = random.nextInt(5);
        }

        Scanner scanner = new Scanner(System.in);
        int chances = 6;
        int shipsFound = 0;

        System.out.println("Welcome to 'Find the Ships' game!");
        System.out.println("You have 6 chances to find 2 hidden ships on a 5x5 grid.");
        System.out.println("Enter your guesses in the format: row (0-4) and column (0-4).");

        while (chances > 0 && shipsFound < 2) {
            displayBoard(board);

            System.out.print("Enter row: ");
            int row = scanner.nextInt();
            System.out.print("Enter column: ");
            int col = scanner.nextInt();

            if (row < 0 || row > 4 || col < 0 || col > 4) {
                System.out.println("Invalid input. Please enter numbers between 0 and 4.");
                continue;
            }

            // Check if the guess hits a ship
            if ((row == ship1[0] && col == ship1[1]) || (row == ship2[0] && col == ship2[1])) {
                System.out.println("Hit! You've found a ship!");
                board[row][col] = 'S'; // Mark ship position
                shipsFound++;
            } else {
                System.out.println("Miss! No ship at this position.");
                board[row][col] = 'X'; // Mark miss
            }

            chances--;
            System.out.println("Chances left: " + chances);
        }

        if (shipsFound == 2) {
            System.out.println("Congratulations! You found both ships!");
        } else {
            System.out.println("Game over! You ran out of chances.");
            System.out.println("The ships were at: (" + ship1[0] + ", " + ship1[1] + ") and (" + ship2[0] + ", " + ship2[1] + ")");
        }

        displayBoard(board);
    }

    // Function to display the current state of the board
    public static void displayBoard(char[][] board) {
        System.out.println("Board:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
