package System_Design;

import java.util.*;

public class SnakeAndLadderGame {

    static class Player {
        String name;
        int position;

        Player(String name) {
            this.name = name;
            this.position = 1;
        }
    }

    static class Dice {
        int roll() {
            return new Random().nextInt(6) + 1;
        }
    }

    static class Board {
        Map<Integer, Integer> snakes;
        Map<Integer, Integer> ladders;

        Board() {
            snakes = new HashMap<>();
            ladders = new HashMap<>();
            initSnakesAndLadders();
        }

        private void initSnakesAndLadders() {
            // Sample snakes
            snakes.put(99, 21);
            snakes.put(92, 60);
            snakes.put(50, 10);

            // Sample ladders
            ladders.put(3, 22);
            ladders.put(5, 8);
            ladders.put(11, 26);
            ladders.put(20, 29);
        }

        int getNextPosition(int pos) {
            if (snakes.containsKey(pos)) {
                System.out.println("Oops! Bitten by snake at " + pos);
                return snakes.get(pos);
            }
            if (ladders.containsKey(pos)) {
                System.out.println("Yay! Climbed ladder at " + pos);
                return ladders.get(pos);
            }
            return pos;
        }
    }

    // Game Engine
    static class Game {
        List<Player> players;
        Dice dice;
        Board board;
        Queue<Player> turnQueue;

        Game(List<String> playerNames) {
            players = new ArrayList<>();
            turnQueue = new LinkedList<>();
            for (String name : playerNames) {
                Player p = new Player(name);
                players.add(p);
                turnQueue.add(p);
            }
            board = new Board();
            dice = new Dice();
        }

        void start() {
            boolean gameOver = false;
            while (!gameOver) {
                Player current = turnQueue.poll();
                System.out.println("\n" + current.name + "'s turn");

                int roll = dice.roll();
                System.out.println("Rolled: " + roll);

                int nextPos = current.position + roll;

                if (nextPos > 100) {
                    System.out.println("Can't move, needs exact roll.");
                } else {
                    int finalPos = board.getNextPosition(nextPos);
                    System.out.println("Moved from " + current.position + " to " + finalPos);
                    current.position = finalPos;

                    if (current.position == 100) {
                        System.out.println("\n🎉 " + current.name + " wins the game!");
                        gameOver = true;
                        continue;
                    }
                }

                // Re-add player for next turn
                turnQueue.offer(current);
            }
        }
    }

    public static void main(String[] args) {
        List<String> playerNames = Arrays.asList("Alice", "Bob", "Charlie");
        Game game = new Game(playerNames);
        game.start();
    }
}

