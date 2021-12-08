package com.company;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class Connect4 implements Game {
    private Board newgame;
    // true if player1's turn
    // false if player2's turn
    private boolean changeplayer;
    private boolean fromPause;
    private String player1;
    private String player2;


    /**
     * constructor
     */
    public Connect4() {
        newgame = new Board();
        changeplayer = true;
        fromPause = false;
    }


    /**
     * method to check if someone has won or if the grid is full and no one won
     * @param color  token to check
     * @param currentlyRunning  the game is running
     * @return  false if the game is over, true otherwise
     */
    public boolean checkEnd(String color, boolean currentlyRunning) {
        boolean winner;
        boolean filled;
        winner = newgame.isWinner(color);
        filled = newgame.fullBoard();
        String winnerName;
        if (winner || filled) {
            newgame.printBoard();
            // if someone has won
            if (winner) {
                if (color.equals("X")) {
                    System.out.println();
                    winnerName = player1 + " wins!";
                    System.out.println(winnerName);
                }
                else {
                    System.out.println();
                    winnerName = player2 + " wins!";
                    System.out.println(winnerName);
                }

            }
            // if the grid is full and no one won
            else {
                System.out.println();
                winnerName = "No one wins!";
                System.out.println("No more empty cells!" + "\nIt's a draw!");
            }

            currentlyRunning = endDialog(winnerName);

        }

        return currentlyRunning;
    }



    /**
     * method for displaying end game dialog
     * @param winnerName  the name of the player who won
     * @return  true if the player decided to continue playing, false otherwise
     */
    public boolean endDialog(String winnerName) {
        boolean running = true;
        Object[] end = {"New Game", "Load Game", "Exit"};
        ImageIcon icon = new ImageIcon(new ImageIcon("resources/iconconnect4.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        int playAgain = -1;
        while (playAgain == -1) {
            playAgain = JOptionPane.showOptionDialog(null, winnerName + "\nWhat would you like to do?", "Game ended...",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon, end, end[0]);
            if (playAgain == 0) {
                reset();
            } else if (playAgain == 1) {
                loadGame();
            } else if (playAgain == 2) {
                running = false;
            }
        }

        return running;
    }



    /**
     * method for displaying intro game dialog
     */
    public void introDialog() {
        player1 = JOptionPane.showInputDialog(null, "Enter the name of player 1: ", "Enter name...", JOptionPane.QUESTION_MESSAGE);
        player2 = JOptionPane.showInputDialog(null, "Enter the name of player 2: ", "Enter name...", JOptionPane.QUESTION_MESSAGE);
        System.out.println(player1 + " will have X, " + player2 + " will have O. Let's start the game!");
    }


    /**
     * load game method
     */
    public void loadGame() {
        boolean success = false;
        while (!success) {
            String openFilename = "";
            FileSystem chooseSlot = new FileSystem();
            while (openFilename.equals("")) {
                // call openFile() method to let the user choose the game to open
                openFilename = chooseSlot.openFile();
            }
            LoadGame newLoad = new LoadGame(openFilename);
            // call readFile() method to get saved values from text file
            success = newLoad.readFile();
            if (!success) {
                System.err.println("File not supported");
            }
            else {
                // assign those values to the program's variables
                player1 = newLoad.getPlayer1Name();
                player2 = newLoad.getPlayer2Name();
                changeplayer = newLoad.getTurn();
                newgame.loadBoard(openFilename);
            }

        }

    }

    /**
     * save game method
     */
    public void saveGame() {
        String fn = JOptionPane.showInputDialog(null, "Insert the name game: ", "Enter filename...", JOptionPane.QUESTION_MESSAGE);
        String filename = fn + ".txt";
        Piece[][] ourBoard = newgame.getBoard();
        SaveGame newSave = new SaveGame(player1, player2, changeplayer, ourBoard, filename);
        newSave.writeToFile();
    }



    /**
     * method for making the user decide what to do after pausing the game
     * @return  true if the player decided to continue playing (new game or load game), false otherwise (save game or exit)
     */
    public boolean pause() {
        boolean running = true;
        fromPause = true;
        System.out.println();
        System.out.println("Game paused...");
        newgame.printBoard();
        // Custom button text
        Object[] options = {"New Game", "Save Game", "Load Game", "Exit"};
        ImageIcon icon = new ImageIcon(new ImageIcon("resources/iconpause.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        int n = -1;
        while (n == -1) {
            n = JOptionPane.showOptionDialog(null, "What would you like to do?", "Game paused...",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);

            if (n == 0) {
                reset();
            } else if (n == 1) {
                saveGame();
                running = false;
            } else if (n == 2) {
                loadGame();
            } else if (n == 3) {
                running = false;
                System.out.println("Game ended");
            }
        }

        return running;

    }



    /**
     * method to reset the game and start a new game
     */
    public void reset() {
        this.newgame = new Board();
        changeplayer = true;
        introDialog();
    }



    /**
     * method to start the game
     */
    public void start() {
        Object[] choices = {"New Game", "Load Game"};
        ImageIcon icon = new ImageIcon(new ImageIcon("resources/iconconnect4.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        int ans = -1;
        while (ans == -1) {
            ans = JOptionPane.showOptionDialog(null, "What would you like to do?", "Welcome to Java Connect 4 game!",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon, choices, choices[0]);
        }

        if (ans == 0) {
            introDialog();
        }
        else if (ans == 1) {
            loadGame();
        }


        boolean currentlyRunning = true;
        String color;
        while (currentlyRunning) {
            System.out.println();
            newgame.printBoard();
            System.out.println();

            if (changeplayer) {
                color = "X";
                System.out.println("Player 1's turn");
                System.out.println(player1 + " choose the column: ");
            }
            else {
                color = "O";
                System.out.println("Player 2's turn");
                System.out.println(player2 + " choose the column: ");
            }



            int col = 0;
            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()) {
                col = in.nextInt();

            }
            else {
                currentlyRunning = pause();
            }


            if (!fromPause) {
                boolean added = newgame.setCell(col - 1, color);
                if (added) {
                    changeplayer = !changeplayer;
                }

                currentlyRunning = checkEnd(color, currentlyRunning);


            }


            fromPause = false;

        }


    }


}