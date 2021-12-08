package com.company;

public class BoardTester {

    public static void main(String[] args) {

        Board newgame = new Board();
        newgame.printBoard();
        System.out.println();
        newgame.setCell(0, "X");
        newgame.setCell(1, "X");
        newgame.setCell(1, "O");
        newgame.printBoard();
        System.out.println();

        newgame.setCell(1, "O");
        newgame.setCell(1, "O");
        newgame.setCell(1, "X");
        newgame.printBoard();
        boolean winner = newgame.isWinner("O");
        System.out.println(winner);


        /**
         //setta i nomi iniziali
         Scanner in = new Scanner(System.in);
         System.out.println("Insert player 1: ");
         String player1 = in.next();
         System.out.println("Insert player 2: ");
         String player2 = in.next();
         System.out.println();

         System.out.println(player1 + " will have X and " + player2 + " will have O. Let's start!");
         System.out.println();

         //setta la griglia vuota
         Grid newgame = new Grid();
         newgame.printBoard();
         System.out.println();

         newgame.setCell(0, "X");
         newgame.setCell(1, "X");
         newgame.setCell(1, "O");
         newgame.printBoard();

         **/


    }

}
