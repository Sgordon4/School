package edu.iastate.cs472.proj1;

public class AlphaBetaSearch {
    private CheckersData board;

    // An instance of this class will be created in the Checkers.Board
    // It would be better to keep the default constructor.

    public void setCheckersData(CheckersData board) {
        this.board = board;
    }

    /**
     *  You need to implement the Alpha-Beta pruning algorithm here to
     * find the best move at current stage.
     * The input parameter legalMoves contains all the possible moves.
     * It contains four integers:  fromRow, fromCol, toRow, toCol
     * which represents a move from (fromRow, fromCol) to (toRow, toCol).
     * It also provides a utility method `isJump` to see whether this
     * move is a jump or a simple move.
     *
     * @param legalMoves All the legal moves for the agent at current step.
     */
    public CheckersMove makeMove(CheckersMove[] legalMoves) {
        // The checker board state can be obtained from this.board,
        // which is a int 2D array. The numbers in the `board` are
        // defined as
        // 0 - empty square,
        // 1 - red man
        // 2 - red king
        // 3 - black man
        // 4 - black king
        System.out.println(board);
        System.out.println();

        // Here, we simply return the first legal move for demonstration.
        //return legalMoves[0];

        //If legalMoves is empty (end of the game)
        if(legalMoves.length == 0)
            return legalMoves[0];


        //Deep copy the board
        CheckersData newBoard = new CheckersData();
        //newBoard.board = board.board.clone();
        newBoard.board = java.util.Arrays.stream(board.board).map(int[]::clone).toArray($ -> board.board.clone());

        return alphaBetaStarter(newBoard, legalMoves, 10);

    }



    CheckersMove alphaBetaStarter(CheckersData currBoard, CheckersMove[] legalMoves, int maxDepth){
        CheckersMove bestMove = null;
        int bestScore = -9999;

        for(CheckersMove move : legalMoves){
            //Deep copy the board
            CheckersData newBoard = new CheckersData();
            //newBoard.board = currBoard.board.clone();
            newBoard.board = java.util.Arrays.stream(currBoard.board).map(int[]::clone).toArray($ -> currBoard.board.clone());

            //Make the move
            newBoard.makeMove(move);

            //Continue down the tree
            int val = alphaBetaPrune(newBoard, CheckersData.RED, 1, maxDepth, -9999, 9999);


            if(val > bestScore){
                bestScore = val;
                bestMove = move;
            }
        }

        return bestMove;
    }

    int alphaBetaPrune(CheckersData oldBoard, int player, int depth, int maxDepth, int alpha, int beta){

        //If we have hit max depth...
        if(depth == maxDepth)
            return evalFunc(oldBoard.board);


        //Get the next set of moves from this board
        CheckersMove[] legalMoves = oldBoard.getLegalMoves(player);

        //If there are no more valid moves...
        if(legalMoves == null || legalMoves.length == 0){
            return evalFunc(oldBoard.board);
        }


        //If black, we're maximizing
        if(player == CheckersData.BLACK){
            int best = -9999;

            for(CheckersMove move : legalMoves){
                //Deep copy the board
                CheckersData newBoard = new CheckersData();
                //newBoard.board = oldBoard.board.clone();
                newBoard.board = java.util.Arrays.stream(oldBoard.board).map(int[]::clone).toArray($ -> oldBoard.board.clone());

                //Make the move
                newBoard.makeMove(move);

                //Continue down the tree
                int val = alphaBetaPrune(newBoard, (player+1)%2, depth+1, maxDepth, alpha, beta);

                best = Math.max(best, val);
                alpha = Math.max(alpha, best);

                //Apply pruning
                if(beta <= alpha)
                    break;
            }
            return best;
        }
        else{
            int best = 9999;

            for(CheckersMove move : legalMoves){
                //Deep copy the board
                CheckersData newBoard = new CheckersData();
                //newBoard.board = oldBoard.board.clone();
                newBoard.board = java.util.Arrays.stream(oldBoard.board).map(int[]::clone).toArray($ -> oldBoard.board.clone());

                //Make the move
                newBoard.makeMove(move);

                //Continue down the tree
                int val = alphaBetaPrune(newBoard, (player+1)%2, depth+1, maxDepth, alpha, beta);

                best = Math.min(best, val);
                beta = Math.min(beta, best);

                //Apply pruning
                if(beta <= alpha)
                    break;
            }
            return best;
        }


    }

    //Score = # black pieces - # red pieces
    int evalFunc(int[][] board){
        int black = 0;
        int red = 0;

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == CheckersData.RED)
                    red++;
                if(board[i][j] == CheckersData.BLACK)
                    black++;
            }
        }
        return (black - red);
    }
}
