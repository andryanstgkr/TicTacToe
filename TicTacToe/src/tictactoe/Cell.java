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
public class Cell {

    // package access
    Seeds content;
    int row, col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        clear();  // clear content
    }

    public void clear() {
        content = Seeds.EMPTY;
    }

    public void paint() {
        switch (content) {
            case CROSS:
                System.out.print(" X ");
                break;
            case NOUGHT:
                System.out.print(" O ");
                break;
            case EMPTY:
                System.out.print("   ");
                break;
        }
    }
}
