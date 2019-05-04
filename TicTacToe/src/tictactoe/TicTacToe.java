/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Scanner;

/**
 *
 * @author andryan
 */
public class TicTacToe {

    private final Board board;            // the game board
    private States currentState; // the current state of the game (of enum States)
    private Seeds currentPlayer;     // the current player (of enum Seeds)

    private static Scanner in = new Scanner(System.in);  // input Scanner

    /**
     * Constructor to setup the game
     */
    public TicTacToe() {
        boolean validGridSize = false;
        int gridSize = 0;
        do{
            System.out.print("Enter board size 3 or bigger: ");
            gridSize = in.nextInt();
            if(gridSize>=3){
                validGridSize = true;
            }
        }while (!validGridSize);
       
        board = new Board(gridSize);  // allocate game-board

        // Initialize the game-board and current status
        initGame(gridSize);
        // Play the game once. Players CROSS and NOUGHT move alternately.
        do {
            playerMove(currentPlayer, gridSize); // update the content, currentRow and currentCol
            board.paint(gridSize);             // ask the board to paint itself
            updateGame(currentPlayer, gridSize); // update currentState
            // Print message if game-over
            if (currentState == States.CROSS_WON) {
                System.out.println("'X' won! Bye!");
            } else if (currentState == States.NOUGHT_WON) {
                System.out.println("'O' won! Bye!");
            } else if (currentState == States.DRAW) {
                System.out.println("It's Draw! Bye!");
            }
            // Switch player
            currentPlayer = (currentPlayer == Seeds.CROSS) ? Seeds.NOUGHT : Seeds.CROSS;
        } while (currentState == States.PLAYING);  // repeat until game-over
    }

    /**
     * Initialize the game-board contents and the current states
     */
    private void initGame(int gridSize) {
        board.init(gridSize);  // clear the board contents
        currentPlayer = Seeds.CROSS;       // CROSS plays first
        currentState = States.PLAYING; // ready to play
    }

    /**
     * The player with "theSeeds" makes one move, with input validation. Update
     * Cell's content, Board's currentRow and currentCol.
     */
    private void playerMove(Seeds theSeeds, int gridSize) {
        boolean validInput = false;  // for validating input
        do {
            if (theSeeds == Seeds.CROSS) {
                System.out.print("Player 'X', enter your move (row[1-"+gridSize+"] column[1-"+gridSize+"]): ");
            } else {
                System.out.print("Player 'O', enter your move (row[1-"+gridSize+"] column[1-"+gridSize+"]): ");
            }
            int row = in.nextInt() - 1;
            int col = in.nextInt() - 1;
            if (row >= 0 && row < gridSize && col >= 0 && col < gridSize
                    && board.cells[row][col].content == Seeds.EMPTY) {
                board.cells[row][col].content = theSeeds;
                board.currentRow = row;
                board.currentCol = col;
                validInput = true; // input okay, exit loop
            } else {
                System.out.println("This move at (" + (row + 1) + "," + (col + 1)
                        + ") is not valid. Try again...");
            }
        } while (!validInput);   // repeat until input is valid
    }

    /**
     * Update the currentState after the player with "theSeeds" has moved
     * @param theSeeds
     * @param gridSize
     */
    private void updateGame(Seeds theSeeds, int gridSize) {
        if (board.hasWon(theSeeds, gridSize, currentPlayer)) {  // check for win
            currentState = (theSeeds == Seeds.CROSS) ? States.CROSS_WON : States.NOUGHT_WON;
        } else if (board.isDraw(gridSize)) {  // check for draw
            currentState = States.DRAW;
        }
        // Otherwise, no change to current state (still States.PLAYING).
    }

    /**
     * The entry main() method
     */
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe(); // Let the constructor do the job
    }

}
