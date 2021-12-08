package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadGame {
    private String player1;
    private String player2;
    private boolean changeplayer;
    private Piece[][] matrix = new Piece[Board.getRows()][Board.getColumns()];
    private String openFilename;


    /**
     * constructor
     * @param openFilename  the name of the file to open
     */
    public LoadGame(String openFilename) {
        this.openFilename = openFilename;
    }



    /**
     * method for reading save game file
     */
    public boolean readFile() {
        boolean success = true;
        try {
            // instantiate new BufferedReader that reads from the file
            BufferedReader loadGameBuffer = new BufferedReader(new FileReader(openFilename));

            // add to the variables the value of each line read
            player1 = loadGameBuffer.readLine();
            player2 = loadGameBuffer.readLine();
            String turn = loadGameBuffer.readLine();

            if (!(turn == null)) {
                // convert turn from string to boolean to set the players' turn
                if (turn.equals("false")) {
                    changeplayer = false;
                }
                else if (turn.equals("true")){
                    changeplayer = true;
                }
                else {
                    success = false;
                }


                if (success) {
                    // iterate through the 2D array and for each line read, assign the colour read to tokenColour
                    for (int r = 0; r < Board.getRows(); r++) {
                        for (int c = 0; c < Board.getColumns(); c++) {
                            String tokenColour = loadGameBuffer.readLine();

                            // if tokenColour is X or O create a new token and add it to the grid position [r][c]
                            // if the tokenColour is "empty" then no tokens will be added
                            if (tokenColour.equals("X")) {
                                matrix[r][c] = new Piece();
                                matrix[r][c].setColor("X");
                            } else if (tokenColour.equals("O")) {
                                matrix[r][c] = new Piece();
                                matrix[r][c].setColor("O");
                            }
                            else if (!tokenColour.equals("empty")) {
                                success = false;
                                break;
                            }

                        }

                        if (!success) {
                            break;
                        }

                    }
                }
            }
            else {
                success = false;
            }



            loadGameBuffer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return success;

    }



    /**
     * getter method
     * @return  the name of player 1
     */
    public String getPlayer1Name() {
        return player1;
    }


    /**
     * getter method
     * @return  the name of player 2
     */
    public String getPlayer2Name() {
        return player2;
    }


    /**
     * getter method
     * @return  the players' turn
     */
    public boolean getTurn() {
        return changeplayer;
    }


    /**
     * getter method
     * @return  the grid filled with the pieces of the saved game
     */
    public Piece[][] getBoard() {
        return matrix;
    }
}