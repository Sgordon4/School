package edu.iastate.cs472.proj1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An object of this class holds data about a game of checkers.
 * It knows what kind of piece is on each square of the checkerboard.
 * Note that RED moves "up" the board (i.e. row number decreases)
 * while BLACK moves "down" the board (i.e. row number increases).
 * Methods are provided to return lists of available legal moves.
 */
public class CheckersData {

  /*  The following constants represent the possible contents of a square
      on the board.  The constants RED and BLACK also represent players
      in the game. */

    static final int
            EMPTY = 0,
            RED = 1,
            RED_KING = 2,
            BLACK = 3,
            BLACK_KING = 4;


    int[][] board;  // board[r][c] is the contents of row r, column c.


    /**
     * Constructor.  Create the board and set it up for a new game.
     */
    CheckersData() {
        board = new int[8][8];
        setUpGame();
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < board.length; i++) {
            int[] row = board[i];
            sb.append(8 - i).append(" ");
            for (int n : row) {
                if (n == 0) {
                    sb.append(" ");
                } else if (n == 1) {
                    sb.append(ANSI_RED + "R" + ANSI_RESET);
                } else if (n == 2) {
                    sb.append(ANSI_RED + "K" + ANSI_RESET);
                } else if (n == 3) {
                    sb.append(ANSI_YELLOW + "B" + ANSI_RESET);
                } else if (n == 4) {
                    sb.append(ANSI_YELLOW + "K" + ANSI_RESET);
                }
                sb.append(" ");
            }
            sb.append(System.lineSeparator());
        }
        sb.append("  a b c d e f g h");

        return sb.toString();
    }

    /**
     * Set up the board with checkers in position for the beginning
     * of a game.  Note that checkers can only be found in squares
     * that satisfy  row % 2 == col % 2.  At the start of the game,
     * all such squares in the first three rows contain black squares
     * and all such squares in the last three rows contain red squares.
     */
    void setUpGame() {
        this.board = new int[][]{
            {BLACK, EMPTY, BLACK, EMPTY, BLACK, EMPTY, BLACK, EMPTY},
            {EMPTY, BLACK, EMPTY, BLACK, EMPTY, BLACK, EMPTY, BLACK},
            {BLACK, EMPTY, BLACK, EMPTY, BLACK, EMPTY, BLACK, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, RED  , EMPTY, RED  , EMPTY, RED  , EMPTY, RED  },
            {RED  , EMPTY, RED  , EMPTY, RED  , EMPTY, RED  , EMPTY},
            {EMPTY, RED  , EMPTY, RED  , EMPTY, RED  , EMPTY, RED  }
        };
    }


    /**
     * Return the contents of the square in the specified row and column.
     */
    int pieceAt(int row, int col) {
        return board[row][col];
    }


    /**
     * Make the specified move.  It is assumed that move
     * is non-null and that the move it represents is legal.
     * @return  true if the piece becomes a king, otherwise false
     */
    boolean makeMove(CheckersMove move) {
        return makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
    }


    /**
     * Make the move from (fromRow,fromCol) to (toRow,toCol).  It is
     * assumed that this move is legal.  If the move is a jump, the
     * jumped piece is removed from the board.  If a piece moves to
     * the last row on the opponent's side of the board, the
     * piece becomes a king.
     *
     * @param fromRow row index of the from square
     * @param fromCol column index of the from square
     * @param toRow   row index of the to square
     * @param toCol   column index of the to square
     * @return        true if the piece becomes a king, otherwise false
     */
    boolean makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        // You need to take care of the following situations:
        // 1. move the piece from (fromRow,fromCol) to (toRow,toCol)
        // 2. if this move is a jump, remove the captured piece
        // 3. if the piece moves into the kings row on the opponent's side of the board, crowned it as a king

        board[toRow][toCol] = board[fromRow][fromCol];
        board[fromRow][fromCol] = EMPTY;

        int rowDiff = toRow - fromRow;
        int colDiff = toCol - fromCol;

        //If this move was a jump...
        if(rowDiff % 2 == 0) {
            int RMid = fromRow + (rowDiff / 2);
            int CMid = fromCol + (colDiff / 2);

            board[RMid][CMid] = EMPTY;
        }

        //If the piece moved to the top or bottom row...
        if (toRow == 0 || toRow == 7){

            //If the piece isn't already a king...
            if(board[toRow][toCol] % 2 != 0) {
                board[toRow][toCol]++;
                return true;
            }
        }
        return false;
    }

    /**
     * Return an array containing all the legal CheckersMoves
     * for the specified player on the current board.  If the player
     * has no legal moves, null is returned.  The value of player
     * should be one of the constants RED or BLACK; if not, null
     * is returned.  If the returned value is non-null, it consists
     * entirely of jump moves or entirely of regular moves, since
     * if the player can jump, only jumps are legal moves.
     *
     * @param player color of the player, RED or BLACK
     */
    CheckersMove[] getLegalMoves(int player) {
        if(player != RED && player != BLACK)
            return null;

        List<CheckersMove> moveList = new ArrayList<CheckersMove>();
        List<CheckersMove> jumpList = new ArrayList<CheckersMove>();


        //For every square on the board...
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){

                int curPiece = board[row][col];

                //If this piece is empty or not of player's color...
                if(curPiece == EMPTY || (curPiece != player && curPiece != player+1))
                    continue;


                //Add any possible jumps
                CheckersMove[] jumpArray = getLegalJumpsFrom(player, row, col);
                if(jumpArray != null)
                    Collections.addAll(jumpList, jumpArray);



                //Because jumps are mandatory, if there are any possible jumps just skip checking for more moves
                if(jumpList.size() == 0){
                    if(curPiece == RED || curPiece == RED_KING || curPiece == BLACK_KING){
                        //Move top left
                        if(isValidMove(row-1, col-1))
                            moveList.add(new CheckersMove(row, col, row-1, col-1));
                        //Move top right
                        if(isValidMove(row-1, col+1))
                            moveList.add(new CheckersMove(row, col, row-1, col+1));
                    }
                    if(curPiece == BLACK || curPiece == RED_KING || curPiece == BLACK_KING){
                        //Move bottom left
                        if(isValidMove(row+1, col-1))
                            moveList.add(new CheckersMove(row, col, row+1, col-1));
                        //Move bottom right
                        if(isValidMove(row+1, col+1))
                            moveList.add(new CheckersMove(row, col, row+1, col+1));
                    }
                }
            }
        }

        //If there are valid moves... copy them to a static size array
        if(jumpList.size() != 0){
            CheckersMove[] jumpArray = new CheckersMove[jumpList.size()];
            jumpList.toArray(jumpArray);

            return jumpArray;
        }
        else if(moveList.size() != 0){
            CheckersMove[] moveArray = new CheckersMove[moveList.size()];
            moveList.toArray(moveArray);

            return moveArray;
        }
        return null;
    }



    /**
     * Return a list of the legal jumps that the specified player can
     * make starting from the specified row and column.  If no such
     * jumps are possible, null is returned.  The logic is similar
     * to the logic of the getLegalMoves() method.
     *
     * @param player The player of the current jump, either RED or BLACK.
     * @param row    row index of the start square.
     * @param col    col index of the start square.
     */
    CheckersMove[] getLegalJumpsFrom(int player, int row, int col) {
        if(player != RED && player != BLACK)
            return null;


        int curPiece = board[row][col];

        //If this piece is not of player's color...
        if(curPiece != player && curPiece != player+1)
            return null;


        List<CheckersMove> jumpList = new ArrayList<CheckersMove>();

        if(curPiece == RED || curPiece == RED_KING || curPiece == BLACK_KING){
            //Jump top left
            if(isValidJump(player, row-1, col-1, row-2, col-2))
                jumpList.add(new CheckersMove(row, col, row-2, col-2));
            //Jump top right
            if(isValidJump(player, row-1, col+1, row-2, col+2))
                jumpList.add(new CheckersMove(row, col, row-2, col+2));
        }
        if(curPiece == BLACK || curPiece == RED_KING || curPiece == BLACK_KING){
            //Jump bottom left
            if(isValidJump(player, row+1, col-1, row+2, col-2))
                jumpList.add(new CheckersMove(row, col, row+2, col-2));
            //Jump bottom right
            if(isValidJump(player, row+1, col+1, row+2, col+2))
                jumpList.add(new CheckersMove(row, col, row+2, col+2));
        }

        //If there are valid jumps... copy moveList to a static size array
        if(jumpList.size() != 0){
            CheckersMove[] jumpArray = new CheckersMove[jumpList.size()];
            jumpList.toArray(jumpArray);

            return jumpArray;
        }
        return null;
    }



    boolean isValidMove(int REnd, int CEnd){
        //If move is out of bounds...
        if(REnd < 0 || CEnd < 0 || REnd > 7 || CEnd > 7)
            return false;

        //If end coord isn't empty...
        if(board[REnd][CEnd] != EMPTY)
            return false;

        return true;
    }

    boolean isValidJump(int player, int RMid, int CMid, int REnd, int CEnd){

        if(!isValidMove(REnd, CEnd))
            return false;

        int otherColor = (player == RED || player == RED_KING) ? BLACK : RED;

        //If the middle square is empty or not of the opposite color...
        if(board[RMid][CMid] == EMPTY || (board[RMid][CMid] != otherColor && board[RMid][CMid] != otherColor+1))
            return false;

        return true;
    }
}



















