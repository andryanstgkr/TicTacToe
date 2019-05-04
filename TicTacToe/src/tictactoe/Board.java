/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author andryan
 */
public class Board {// Named-constants for the dimensions

    // package access
    Cell[][] cells;  // a board composes of ROWS-by-COLS Cell instances
    int currentRow, currentCol;  // the current seed's row and column
    int i = 0;

    /**
     * Constructor to initialize the game board
     *
     * @param gridSize
     */
    public Board(int gridSize) {
        cells = new Cell[gridSize][gridSize];  // allocate the array
        for (int row = 0; row < gridSize; ++row) {
            for (int col = 0; col < gridSize; ++col) {
                cells[row][col] = new Cell(row, col); // allocate element of the array
            }
        }
    }

    /**
     * Initialize (or re-initialize) the contents of the game board
     * @param gridSize
     */
    public void init(int gridSize) {
        for (int row = 0; row < gridSize; ++row) {
            for (int col = 0; col < gridSize; ++col) {
                cells[row][col].clear();  // clear the cell content
            }
        }
    }

    /**
     * Return true if it is a draw (i.e., no more EMPTY cell)
     *
     * @param gridSize
     * @return
     */
    public boolean isDraw(int gridSize) {
        for (int row = 0; row < gridSize; ++row) {
            for (int col = 0; col < gridSize; ++col) {
                if (cells[row][col].content == Seeds.EMPTY) {
                    return false; // an empty seed found, not a draw, exit
                }
            }
        }
        return true; // no empty cell, it's a draw
    }

    /**
     * Return true if the player with "theSeed" has won after placing at
     * (currentRow, currentCol)
     *
     * @param theSeed
     * @param gridSize
     * @param currentPlayer
     * @return
     */
    public boolean hasWon(Seeds theSeed, int gridSize, Seeds currentPlayer) {
        // Counters
        int col = 0, row = 0, diag = 0, adiag = 0;
        for (int i = 0; i < gridSize; i++) {
            if (cells[currentRow][i].content == currentPlayer) {
                row++;
            }
            if (cells[i][currentCol].content == currentPlayer) {
                col++;
            }
            if (cells[i][i].content == currentPlayer) {
                diag++;
            }
            if (cells[i][(gridSize-1)-i].content == currentPlayer) {
                adiag++;
            }
        }
        if (row == gridSize || col == gridSize || diag == gridSize || adiag == gridSize) {
            return true;
        }
        return false;
    }

    /**
     * Paint itself
     * @param gridSize
     */
    public void paint(int gridSize) {
        for (int row = 0; row < gridSize; ++row) {
            for (int col = 0; col < gridSize; ++col) {
                cells[row][col].paint();
                if (col < gridSize - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            for (int col = 0; col < gridSize; ++col) {
            if (row < gridSize - 1) {
                System.out.print("----");
            }
            }
            System.out.println();
        }
    }
}
