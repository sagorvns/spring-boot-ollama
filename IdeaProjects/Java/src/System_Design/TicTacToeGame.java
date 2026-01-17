package System_Design;

import java.util.*;

public class TicTacToeGame {

    enum GameStatus { IN_PROGRESS, DRAW, WIN }

    static class Player {
        String name;
        char symbol;
        public Player(String name, char symbol) {
            this.name = name;
            this.symbol = symbol;
        }
    }

    static class Board {
        int size;
        char[][] grid;

        public Board(int size) {
            this.size = size;
            this.grid = new char[size][size];
            for (char[] row : grid)
                Arrays.fill(row, '-');
        }

        public boolean placeMove(int row, int col, char symbol) {
            if (grid[row][col] == '-') {
                grid[row][col] = symbol;
                return true;
            }
            return false;
        }

        public void printBoard() {
            for (char[] row : grid) {
                for (char cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        }
    }

    interface WinningStrategy {
        boolean checkWin(Board board, char symbol);
    }

    static class DefaultWinningStrategy implements WinningStrategy {
        public boolean checkWin(Board board, char symbol) {
            int n = board.size;
            char[][] grid = board.grid;

            // Check rows & cols
            for (int i = 0; i < n; i++) {
                if (allMatch(grid[i], symbol)) return true;

                boolean colMatch = true;
                for (int j = 0; j < n; j++)
                    if (grid[j][i] != symbol) colMatch = false;
                if (colMatch) return true;
            }

            // Check diagonals
            boolean diag1 = true, diag2 = true;
            for (int i = 0; i < n; i++) {
                if (grid[i][i] != symbol) diag1 = false;
                if (grid[i][n - 1 - i] != symbol) diag2 = false;
            }
            return diag1 || diag2;
        }

        private boolean allMatch(char[] row, char symbol) {
            for (char c : row)
                if (c != symbol) return false;
            return true;
        }
    }

    static class Game {
        Board board;
        List<Player> players;
        int currentTurn;
        GameStatus status;
        WinningStrategy strategy;
        int moves;

        public Game(int size, List<Player> players) {
            this.board = new Board(size);
            this.players = players;
            this.currentTurn = 0;
            this.status = GameStatus.IN_PROGRESS;
            this.strategy = new DefaultWinningStrategy();
            this.moves = 0;
        }

        public void play() {
            Scanner sc = new Scanner(System.in);
            board.printBoard();
            while (status == GameStatus.IN_PROGRESS) {
                Player current = players.get(currentTurn);
                System.out.println("Player: " + current.name + " (" + current.symbol + ")");
                System.out.print("Enter row and col: ");
                int r = sc.nextInt(), c = sc.nextInt();

                if (!board.placeMove(r, c, current.symbol)) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }

                moves++;
                board.printBoard();

                if (strategy.checkWin(board, current.symbol)) {
                    System.out.println("Player " + current.name + " wins!");
                    status = GameStatus.WIN;
                } else if (moves == board.size * board.size) {
                    System.out.println("Game is a draw!");
                    status = GameStatus.DRAW;
                } else {
                    currentTurn = (currentTurn + 1) % players.size();
                }
            }
        }
    }

    public static void main(String[] args) {
        Player p1 = new Player("Alice", 'X');
        Player p2 = new Player("Bob", 'O');

        List<Player> players = Arrays.asList(p1, p2);
        Game game = new Game(3, players);
        game.play();
    }
}

