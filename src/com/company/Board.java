package com.company;


public class Board {

    private static final int ROW = 6; // x size
    private static final int COL = 7; // y size
    Piece[][]matrix = new Piece[ROW][COL];

    /**
     * constructor
     * scrolls the grid by rows and columns and initializes all cells to null
     *
     */
    public Board() {
        for (int x = 0; x < ROW; x++) {
            for (int y = 0; y < COL; y++) {
                matrix[x][y] = null;
            }
        }
    }

    /**
     * getter method
     * @return  the grid
     */
    public Piece[][] getBoard() {
        return matrix;
    }


    /**
     * getter method
     * @return  number of columns
     */
    public static int getColumns() {
        return COL;
    }


    /**
     * getter method
     * @return  number of rows
     */
    public static int getRows() {
        return ROW;
    }


    /**
     * method that loads the saved game grid into the current board
     * @param openFilename  the name of the file from which to take the saved grid
     */
    public void loadBoard(String openFilename) {
        LoadGame newLoad = new LoadGame(openFilename);
        newLoad.readFile();
        matrix = newLoad.getBoard();
    }



    /**
     * method to print the grid on the console
     */
    public void printBoard() {
        for (int x = 0; x < ROW; x++) {
            System.out.print("|");
            for (int y = 0; y < COL; y++) {
                if (matrix[x][y] == null) {
                    // if the cell is empty, print an underscore
                    System.out.print("_");
                }
                else {
                    // if the cell is not empty, print the token
                    System.out.print(matrix[x][y].getColor());
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }






    /**
     * method to add tokens
     * @param colToSet  which column did the user try to add a piece to
     * @param color  the piece to be inserted (X or O)
     * @return  false: if the token has not been added (if there are no more empty cells or if the column number is out of range)
     *          true and an int: if the token has been added and in which row
     */
    public boolean setCell(int colToSet, String color) {
        if (colToSet >= 0 && colToSet < COL) {
            // if the first row of the chosen column is null, it means that the column is not all full so it can add the piece
            if (matrix[0][colToSet] == null) {
                boolean added = false;
                for (int r = ROW - 1; r >= 0; r--) {
                    if (matrix[r][colToSet] == null) {
                        matrix[r][colToSet] = new Piece();
                        matrix[r][colToSet].setColor(color);
                        added = true;
                        break;
                    }
                }

                return added;

            }

            else {
                System.out.println("This column is full, please choose another one!");
                return false;
            }
        }

        else {
            System.err.println("You cannot add the token in that column!");
            return false;
        }
    }



    /**
     * method to check if the player has won
     * @param  player  token to check
     * @return  true if the player has won, false otherwise
     */
    public boolean isWinner(String player) {
        boolean win = false;

        // check horizontal
        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL - 3; c++) {
                if (matrix[r][c] != null && matrix[r][c].getColor().equals(player) &&
                        matrix[r][c + 1] != null && matrix[r][c + 1].getColor().equals(player) &&
                        matrix[r][c + 2] != null && matrix[r][c + 2].getColor().equals(player) &&
                        matrix[r][c + 3] != null && matrix[r][c + 3].getColor().equals(player)) {
                    win = true;
                    break;
                }
            }
        }


        // check vertical
        for (int r = 0; r < ROW - 3; r++) {
            for (int c = 0; c < COL; c++) {
                if (matrix[r][c] != null && matrix[r][c].getColor().equals(player) &&
                        matrix[r + 1][c] != null && matrix[r + 1][c].getColor().equals(player) &&
                        matrix[r + 2][c] != null && matrix[r + 2][c].getColor().equals(player) &&
                        matrix[r + 3][c] != null && matrix[r + 3][c].getColor().equals(player)) {
                    win = true;
                    break;
                }
            }
        }


        // check main diagonal
        for (int r = 0; r < ROW - 3; r++) {
            for (int c = 0; c < COL - 3; c++) {
                if (matrix[r][c] != null && matrix[r][c].getColor().equals(player) &&
                        matrix[r + 1][c + 1] != null && matrix[r + 1][c + 1].getColor().equals(player) &&
                        matrix[r + 2][c + 2] != null && matrix[r + 2][c + 2].getColor().equals(player) &&
                        matrix[r + 3][c + 3] != null && matrix[r + 3][c + 3].getColor().equals(player)) {
                    win = true;
                    break;
                }
            }
        }


        // check secondary diagonal
        for (int r = 3; r < ROW; r++) {
            for (int c = 0; c < COL - 3; c++) {
                if (matrix[r][c] != null && matrix[r][c].getColor().equals(player) &&
                        matrix[r - 1][c + 1] != null && matrix[r - 1][c + 1].getColor().equals(player) &&
                        matrix[r - 2][c + 2] != null && matrix[r - 2][c + 2].getColor().equals(player) &&
                        matrix[r - 3][c + 3] != null && matrix[r - 3][c + 3].getColor().equals(player)) {
                    win = true;
                    break;
                }
            }
        }

        return win;

    }



    /**
     * method to check if the grid is full
     * @return  true if the grid is full, false otherwise
     */
    public boolean fullBoard() {
        boolean full = true;
        // if at least one column of row 0 of the grid is empty, then the grid is not full
        for (int c = 0; c < COL; c++) {
            if (matrix[0][c] == null) {
                full = false;
                break;
            }
        }

        return full;

    }


}