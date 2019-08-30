package com.qa.theInfiniteTesselatingSwamp;

public class Compass {
    public double findDistance(Player player, Treasure treasure, int swampsize){
        //variables used to find the distance
        int playerX = player.getX_coordinate();
        int playerY = player.getY_coordinate();
        int treasureX = treasure.getX_coordinate();
        int treasureY = treasure.getY_coordinate();
        int a, b, c;

        //checks the values of the coordinates to decide how to work out the distance
        if(playerX < treasureX){
            a = treasureX - playerX;
        } else{
            a = playerX - treasureX;
        }
        if (playerY < treasureY){
            b = treasureY - playerY;
        } else{
            b = playerY - treasureY;
        }

        //attempt to check if it would be a smaller distance to go over the edge of the board
        //was causing the distance to occasionally display 0 even the player was not on the treasures location
        /*
        if(a > ((swampsize - treasureX) + playerX)){
            a = (swampsize - treasureX) + playerX;
        } else if (a > ((swampsize - playerX) + treasureX)){
            a = (swampsize - playerX) + treasureX;
        }
        if(b > ((swampsize - treasureY) + playerY)){
            b= (swampsize - treasureY) + playerY;
        } else if (b > ((swampsize - playerY) + treasureY)){
            b = (swampsize - playerY) + treasureY;
        }
        */

        //finds c squared
        c = (a*a) + (b*b);

        //returns the squareroot of c
        return Math.sqrt(c);
    }
}
