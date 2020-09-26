package edu.iastate.cs472.proj1;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


/**
 * This panel lets two users play checkers against each other.
 * Red always starts the game.  If a player can jump an opponent's
 * piece, then the player must jump.  When a player can make no more
 * moves, the game ends.
 *
 * The class has a main() routine that lets it be run as a stand-alone
 * application.  The application just opens a window that uses an object
 * of type Checkers as its content pane.
 */
public class Checkers extends JPanel {

    /**
     * Main routine makes it possible to run Checkers as a stand-alone
     * application.  Opens a window showing a Checkers panel; the program
     * ends when the user closes the window.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Checkers");
        Checkers content = new Checkers();
        window.setContentPane(content);
        window.pack();
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation( (screensize.width - window.getWidth())/2,
                (screensize.height - window.getHeight())/2 );
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setResizable(false);
        window.setVisible(true);
    }

    private JButton newGameButton;  // Button for starting a new game.
    private JButton resignButton;   // Button that a player can use to end
    // the game by resigning.

    private JLabel message;  // Label for displaying messages to the user.

    /**
     * The constructor creates the Board (which in turn creates and manages
     * the buttons and message label), adds all the components, and sets
     * the bounds of the components.  A null layout is used.  (This is
     * the only thing that is done in the main Checkers class.)
     */
    public Checkers() {

        setLayout(null);  // I will do the layout myself.
        setPreferredSize( new Dimension(350,250) );

        setBackground(new Color(0,150,0));  // Dark green background.

        /* Create the components and add them to the applet. */

        Board board = new Board();  // Note: The constructor for the
        //   board also creates the buttons
        //   and label.
        add(board);
        add(newGameButton);
        add(resignButton);
        add(message);

      /* Set the position and size of each component by calling
       its setBounds() method. */

        board.setBounds(20,20,164,164); // Note:  size MUST be 164-by-164 !
        newGameButton.setBounds(210, 60, 120, 30);
        resignButton.setBounds(210, 120, 120, 30);
        message.setBounds(0, 200, 350, 30);

    } // end constructor

    /**
     * This panel displays a 160-by-160 checkerboard pattern with
     * a 2-pixel black border.  It is assumed that the size of the
     * panel is set to exactly 164-by-164 pixels.  This class does
     * the work of letting the users play checkers, and it displays
     * the checkerboard.
     */
    private class Board extends JPanel implements ActionListener, MouseListener {


        CheckersData board;  // The data for the checkers board is kept here.
        //    This board is also responsible for generating
        //    lists of legal moves.

        boolean gameInProgress; // Is a game currently in progress?

        /* The next three variables are valid only when the game is in progress. */

        int currentPlayer;      // Whose turn is it now?  The possible values
        //    are CheckersData.RED and CheckersData.BLACK.

        int selectedRow, selectedCol;  // If the current player has selected a piece to
        //     move, these give the row and column
        //     containing that piece.  If no piece is
        //     yet selected, then selectedRow is -1.

        CheckersMove[] legalMoves;  // An array containing the legal moves for the
        //   current player.

        AlphaBetaSearch player = new AlphaBetaSearch(); // AI player

        /**
         * Constructor.  Create the buttons and label.  Listens for mouse
         * clicks and for clicks on the buttons.  Create the board and
         * start the first game.
         */
        Board() {
            setBackground(Color.BLACK);
            addMouseListener(this);
            resignButton = new JButton("Resign");
            resignButton.addActionListener(this);
            newGameButton = new JButton("New Game");
            newGameButton.addActionListener(this);
            message = new JLabel("",JLabel.CENTER);
            message.setFont(new  Font("Serif", Font.BOLD, 14));
            message.setForeground(Color.green);
            board = new CheckersData();
            doNewGame();
        }


        /**
         * Respond to user's click on one of the two buttons.
         */
        public void actionPerformed(ActionEvent evt) {
            Object src = evt.getSource();
            if (src == newGameButton)
                doNewGame();
            else if (src == resignButton)
                doResign();
        }


        /**
         * Start a new game
         */
        void doNewGame() {
            if (gameInProgress) {
                // This should not be possible, but it doesn't hurt to check.
                message.setText("Finish the current game first!");
                return;
            }
            board.setUpGame();   // Set up the pieces.
            currentPlayer = CheckersData.RED;   // RED moves first.
            player.setCheckersData(board);
            legalMoves = board.getLegalMoves(CheckersData.RED);  // Get RED's legal moves.
            selectedRow = -1;   // RED has not yet selected a piece to move.
            message.setText("Red:  Make your move.");
            gameInProgress = true;
            newGameButton.setEnabled(false);
            resignButton.setEnabled(true);
            repaint();
        }


        /**
         * Current player resigns.  Game ends.  Opponent wins.
         */
        void doResign() {
            if (!gameInProgress) {  // Should be impossible.
                message.setText("There is no game in progress!");
                return;
            }
            if (currentPlayer == CheckersData.RED)
                gameOver("RED resigns.  BLACK wins.");
            else
                gameOver("BLACK resigns.  RED wins.");
        }


        /**
         * The game ends.  The parameter, str, is displayed as a message
         * to the user.  The states of the buttons are adjusted so players
         * can start a new game.  This method is called when the game
         * ends at any point in this class.
         */
        void gameOver(String str) {
            message.setText(str);
            newGameButton.setEnabled(true);
            resignButton.setEnabled(false);
            gameInProgress = false;
        }


        /**
         * This is called by mousePressed() when a player clicks on the
         * square in the specified row and col.  It has already been checked
         * that a game is, in fact, in progress.
         */
        void doClickSquare(int row, int col) {

            /* If the player clicked on one of the pieces that the player
               can move, mark this row and col as selected and return.  (This
               might change a previous selection.)  Reset the message, in
               case it was previously displaying an error message. */
            for (CheckersMove legalMove : legalMoves) {
                if (legalMove.fromRow == row && legalMove.fromCol == col) {
                    selectedRow = row;
                    selectedCol = col;
                    if (currentPlayer == CheckersData.RED)
                        message.setText("RED:  Make your move.");
                    else
                        message.setText("BLACK:  Make your move.");
                    repaint();
                    return;
                }
            }

            /* If no piece has been selected to be moved, the user must first
               select a piece.  Show an error message and return. */
            if (selectedRow < 0) {
                message.setText("Click the piece you want to move.");
                return;
            }

            /* If the user clicked on a square where the selected piece can be
               legally moved, then make the move and return. */
            for (CheckersMove legalMove : legalMoves) {
                if (legalMove.fromRow == selectedRow && legalMove.fromCol == selectedCol
                        && legalMove.toRow == row && legalMove.toCol == col) {
                    doMakeMove(legalMove);
                    return;
                }
            }

         /* If we get to this point, there is a piece selected, and the square where
          the user just clicked is not one where that piece can be legally moved.
          Show an error message. */

            message.setText("Click the square you want to move to.");

        }  // end doClickSquare()


        /**
         * This is called when the current player has chosen the specified
         * move.  Make the move, and then either end or continue the game
         * appropriately.
         */
        void doMakeMove(CheckersMove move) {

            boolean isKingJump = board.makeMove(move);
            repaint();

            /* If the move was a jump, it's possible that the player has another
               jump.  Check for legal jumps starting from the square that the player
               just moved to.  If there are any, the player must jump.  The same
               player continues moving.  */
            if (!isKingJump && move.isJump()) {
                legalMoves = board.getLegalJumpsFrom(currentPlayer,move.toRow,move.toCol);
                if (legalMoves != null) {
                    if (currentPlayer == CheckersData.RED)
                        message.setText("RED:  You must continue jumping.");
                    else
                        message.setText("BLACK:  You must continue jumping.");
                    selectedRow = move.toRow;  // Since only one piece can be moved, select it.
                    selectedCol = move.toCol;
                    repaint();
                    return;
                }
            }

             /* The current player's turn is ended, so change to the other player.
                Get that player's legal moves.  If the player has no legal moves,
                then the game ends. */
            if (currentPlayer == CheckersData.RED) {
                currentPlayer = CheckersData.BLACK;
                legalMoves = board.getLegalMoves(currentPlayer);
                if (legalMoves == null) {
                    gameOver("BLACK has no moves.  RED wins.");
                } else {
                    message.setText("BLACK:  Now AI's turn.");
                }

                CheckersMove moveAI = player.makeMove(legalMoves);
                boolean isKingJumpAI = board.makeMove(moveAI);
                while (!isKingJumpAI && moveAI.isJump()) {
                    legalMoves = board.getLegalJumpsFrom(currentPlayer,moveAI.toRow, moveAI.toCol);
                    if (legalMoves != null) {
                        message.setText("BLACK:  AI has another jump.");
                        selectedRow = move.toRow;  // Since only one piece can be moved, select it.
                        selectedCol = move.toCol;
                        repaint();
                        moveAI = player.makeMove(legalMoves);
                        board.makeMove(moveAI);
                    } else {
                        break;
                    }
                }
                repaint();
            }

            currentPlayer = CheckersData.RED;
            legalMoves = board.getLegalMoves(currentPlayer);
            if (legalMoves == null)
                gameOver("RED has no moves.  BLACK wins.");
            else if (legalMoves[0].isJump())
                message.setText("RED:  Make your move.  You must jump.");
            else
                message.setText("RED:  Make your move.");

            /* Set selectedRow = -1 to record that the player has not yet selected
               a piece to move. */
            selectedRow = -1;

            /* As a courtesy to the user, if all legal moves use the same piece, then
               select that piece automatically so the user won't have to click on it
               to select it. */
            if (legalMoves != null) {
                boolean sameStartSquare = true;
                for (int i = 1; i < legalMoves.length; i++)
                    if (legalMoves[i].fromRow != legalMoves[0].fromRow
                            || legalMoves[i].fromCol != legalMoves[0].fromCol) {
                        sameStartSquare = false;
                        break;
                    }
                if (sameStartSquare) {
                    selectedRow = legalMoves[0].fromRow;
                    selectedCol = legalMoves[0].fromCol;
                }
            }

            /* Make sure the board is redrawn in its new state. */
            repaint();
        }  // end doMakeMove();


        /**
         * Draw a checkerboard pattern in gray and lightGray.  Draw the
         * checkers.  If a game is in progress, highlight the legal moves.
         */
        @Override
        public void paintComponent(Graphics g) {

            /* Draw a two-pixel black border around the edges of the canvas. */

            g.setColor(Color.black);
            g.drawRect(0,0,getSize().width-1,getSize().height-1);
            g.drawRect(1,1,getSize().width-3,getSize().height-3);

            /* Draw the squares of the checkerboard and the checkers. */

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if ( row % 2 == col % 2 )
                        g.setColor(Color.LIGHT_GRAY);
                    else
                        g.setColor(Color.GRAY);
                    g.fillRect(2 + col*20, 2 + row*20, 20, 20);
                    switch (board.pieceAt(row,col)) {
                        case CheckersData.RED:
                            g.setColor(Color.RED);
                            g.fillOval(4 + col*20, 4 + row*20, 15, 15);
                            break;
                        case CheckersData.BLACK:
                            g.setColor(Color.BLACK);
                            g.fillOval(4 + col*20, 4 + row*20, 15, 15);
                            break;
                        case CheckersData.RED_KING:
                            g.setColor(Color.RED);
                            g.fillOval(4 + col*20, 4 + row*20, 15, 15);
                            g.setColor(Color.WHITE);
                            g.drawString("K", 7 + col*20, 16 + row*20);
                            break;
                        case CheckersData.BLACK_KING:
                            g.setColor(Color.BLACK);
                            g.fillOval(4 + col*20, 4 + row*20, 15, 15);
                            g.setColor(Color.WHITE);
                            g.drawString("K", 7 + col*20, 16 + row*20);
                            break;
                    }
                }
            }

         /* If a game is in progress, highlight the legal moves.   Note that legalMoves
          is never null while a game is in progress. */

            if (gameInProgress) {
                /* First, draw a 2-pixel cyan border around the pieces that can be moved. */
                g.setColor(Color.cyan);
                for (CheckersMove legalMove : legalMoves) {
                    g.drawRect(2 + legalMove.fromCol * 20, 2 + legalMove.fromRow * 20, 19, 19);
                    g.drawRect(3 + legalMove.fromCol * 20, 3 + legalMove.fromRow * 20, 17, 17);
                }
               /* If a piece is selected for moving (i.e. if selectedRow >= 0), then
                draw a 2-pixel white border around that piece and draw green borders
                around each square that that piece can be moved to. */
                if (selectedRow >= 0) {
                    g.setColor(Color.white);
                    g.drawRect(2 + selectedCol*20, 2 + selectedRow*20, 19, 19);
                    g.drawRect(3 + selectedCol*20, 3 + selectedRow*20, 17, 17);
                    g.setColor(Color.green);
                    for (CheckersMove legalMove : legalMoves) {
                        if (legalMove.fromCol == selectedCol && legalMove.fromRow == selectedRow) {
                            g.drawRect(2 + legalMove.toCol * 20, 2 + legalMove.toRow * 20, 19, 19);
                            g.drawRect(3 + legalMove.toCol * 20, 3 + legalMove.toRow * 20, 17, 17);
                        }
                    }
                }
            }

        }  // end paintComponent()


        /**
         * Respond to a user click on the board.  If no game is in progress, show
         * an error message.  Otherwise, find the row and column that the user
         * clicked and call doClickSquare() to handle it.
         */
        @Override
        public void mousePressed(MouseEvent evt) {
            if (!gameInProgress)
                message.setText("Click \"New Game\" to start a new game.");
            else {
                int col = (evt.getX() - 2) / 20;
                int row = (evt.getY() - 2) / 20;
                if (col >= 0 && col < 8 && row >= 0 && row < 8)
                    doClickSquare(row,col);
            }
        }

        @Override
        public void mouseReleased(MouseEvent evt) { }
        @Override
        public void mouseClicked(MouseEvent evt) { }
        @Override
        public void mouseEntered(MouseEvent evt) { }
        @Override
        public void mouseExited(MouseEvent evt) { }

    }  // end class Board
} // end class Checkers
