package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGame {
    private String player1;
    private String player2;
    private boolean changeplayer;
    private Piece[][] matrix;
    private String filename;



    /**
     * constructor
     * takes in all variables that need to be saved
     * @param player1  save player 1's name
     * @param player2  save player 2's name
     * @param changeplayer  save the players' turn
     * @param matrix  save the grid
     * @param filename  the name of the file in which to save the game (i.e. in which to save all the variables passed as constructor parameters)
     */
    public SaveGame(String player1, String player2, boolean changeplayer, Piece[][] matrix, String filename) {
        this.player1 = player1;
        this.player2 = player2;
        this.changeplayer = changeplayer;
        this.matrix = matrix;
        this.filename = filename;
    }



    /**
     * method to save the variables into file using buffered writer
     */
    public void writeToFile() {
        // convert changeplayer to string as the writer only writes strings
        String turn;
        if (changeplayer) {
            turn = "true";
        } else {
            turn = "false";
        }

        try {
            // instantiate a new BufferedWriter that creates and writes on the "savedGame.txt" file
            BufferedWriter saveGameBuffer = new BufferedWriter(new FileWriter(filename));
            // write into the file
            saveGameBuffer.write(player1);
            saveGameBuffer.newLine();
            saveGameBuffer.write(player2);
            saveGameBuffer.newLine();
            saveGameBuffer.write(turn);
            saveGameBuffer.newLine();

            // iterate through the 2D array, and if the position [r][c] is not empty, write the colour of the token there
            for (int r = 0; r < Board.getRows(); r++) {
                for (int c = 0; c < Board.getColumns(); c++) {
                    if (matrix[r][c] != null) {
                        Piece newToken = matrix[r][c];
                        saveGameBuffer.write(newToken.getColor());
                    } else {
                        saveGameBuffer.write("empty");
                    }

                    saveGameBuffer.newLine();

                }

            }

            saveGameBuffer.flush();
            saveGameBuffer.close();
            System.out.println();
            System.out.println("\nFile written Successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}