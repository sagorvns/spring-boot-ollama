package System_Design;

import java.util.*;

abstract class Piece {
    boolean isWhite;
    public Piece(boolean isWhite) { this.isWhite = isWhite; }
    abstract boolean isValidMove(int startX, int startY, int endX, int endY);
}

class King extends Piece {
    public King(boolean isWhite) { super(isWhite); }
    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        return Math.abs(startX - endX) <= 1 && Math.abs(startY - endY) <= 1;
    }
}

class ChessBoard {
    private Piece[][] board = new Piece[8][8];

    public void placePiece(int x, int y, Piece piece) { board[x][y] = piece; }

    public boolean movePiece(int startX, int startY, int endX, int endY) {
        Piece piece = board[startX][startY];
        if (piece == null || !piece.isValidMove(startX, startY, endX, endY)) return false;
        board[endX][endY] = piece;
        board[startX][startY] = null;
        return true;
    }
}

public class ChessGame {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.placePiece(0, 4, new King(true));
        System.out.println(board.movePiece(0, 4, 1, 5)); // Valid move
    }
}

