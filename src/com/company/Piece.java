package com.company;

public class Piece {
    private String color;

    /**
     * getter method
     * @return  the color of the token (X or O)
     */
    public String getColor() {
        return color;
    }


    /**
     * method for setting the content of a grid cell
     * @param color  X or O
     */
    public void setColor(String color) {
        this.color = color;
    }

}