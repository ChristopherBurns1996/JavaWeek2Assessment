package com.qa.theInfiniteTesselatingSwamp;

import java.util.Scanner;

public class App {
    public static void main(String[] args){

        //variables used for mvp
        int swampsize;
        String direction;
        int treasureX;
        int treasureY;
        int playerX;
        int playerY;

        //variable used for adding max turns
        int turnCounter = 0;
        int daysLeft;

        //random events
        int randomChance;
        StuckInMud sim = new StuckInMud();
        HelpfulHermit hh = new HelpfulHermit();
        MonsterFight mf = new MonsterFight();
        EventlessDay ed = new EventlessDay();

        //Intro and input of swamp size
        Scanner sc = new Scanner(System.in);
        System.out.println("You are a novice adventurer looking for a legendary weapon in order to slay a horrific demon who plans to attack your home.");
        System.out.println("Your search has lead you to a dark and dangerous swamp.");
        System.out.println("You have been provided with a magical compass by a mystical wizard that will tell you the distance between yourself and the object you are searching for.");
        System.out.println("Please enter the size of the swamp you have found (X by X)");
        swampsize = sc.nextInt();
        sc.nextLine();
        System.out.println("");

        //randomisation of player and treasure coordinates
        treasureX = (int) (Math.random() * 100);
        while(treasureX > swampsize){
            treasureX = (int) (Math.random() * 100);
        }
        treasureY = (int) (Math.random() * 100);
        while(treasureY > swampsize){
            treasureY = (int) (Math.random() * 100);
        }
        playerX = (int) (Math.random() * 100);
        while((playerX > swampsize) || (playerX == treasureX)){
            playerX = (int) (Math.random() * 100);
        }
        playerY = (int) (Math.random() * 100);
        while((playerY > swampsize) || (playerY == treasureY)){
            playerY = (int) (Math.random() * 100);
        }

        Treasure treasure = new Treasure(treasureX, treasureY);
        Player player = new Player(playerX, playerY);

        //creation of compass and continuation of story
        Compass compass = new Compass();
        System.out.println("The swamp you are in is " + swampsize + " tiles by " + swampsize + " tiles.");
        System.out.println("Each time you move, your compass will tell you your distance from the legendary weapon. Use this information to track it down.");
        System.out.println("The demon will arrive at your home and destroy it within 10 days.");
        System.out.println("You have enough food and water to last for 20 days in the swamp.");
        System.out.println("Find the treasure before then!");
        System.out.println("");

        //"Gameplay" and a loop to keep the game going until the player has found the treasure
        while(!((player.findCoordinates()[0] == treasure.findCoordinates()[0]) && (player.findCoordinates()[1] == treasure.findCoordinates()[1]))){
            System.out.println("Please enter a direction to travel in (North, South, East, or West): ");
            direction = sc.nextLine();
            //switch statement to change the players location after they move in a direction
            switch (direction){
                case "North":
                    System.out.println("You have travelled north.");
                    if(player.getY_coordinate() >= swampsize){
                        player.setY_coordinate(0);
                    } else {
                        player.setY_coordinate(player.getY_coordinate() + 1);
                    }
                    System.out.println("You are currently " + compass.findDistance(player, treasure, swampsize) + " tiles away from the treasure.");
                    break;
                case "South":
                    if(player.getY_coordinate() <= 0){
                        player.setY_coordinate(swampsize);
                    } else {
                        player.setY_coordinate(player.getY_coordinate()-1);
                    }
                    System.out.println("You are currently " + compass.findDistance(player, treasure, swampsize) + " tiles away from the treasure.");
                    break;
                case "East":
                    if(player.getX_coordinate() >= swampsize){
                        player.setX_coordinate(0);
                    } else {
                        player.setX_coordinate(player.getX_coordinate()+1);
                    }
                    System.out.println("You are currently " + compass.findDistance(player, treasure, swampsize) + " tiles away from the treasure.");
                    break;
                case "West":
                    if(player.getX_coordinate() <= 0){
                        player.setX_coordinate(swampsize);
                    } else {
                        player.setX_coordinate(player.getX_coordinate()-1);
                    }
                    System.out.println("You are currently " + compass.findDistance(player, treasure, swampsize) + " tiles away from the treasure.");
                    break;
                default:
                    System.out.println("Unknown direction. You wander aimlessly around the swamp, only to end up back where you started, wasting a day of travel and resources.");
            }
            if(turnCounter>= 20){
                break;
            }
            turnCounter++;
            //randomly activates a random event based on a randomly generated number
            randomChance = (int)(Math.random() * 100);
            if(randomChance>=0 && randomChance<20){
                turnCounter++;
                sim.event();
            } else if (randomChance>=20 && randomChance<40){
                turnCounter--;
                hh.event();
            } else if (randomChance>=40 && randomChance<60){
                mf.event();
            } else{
                ed.event();
            }

            daysLeft = 20-turnCounter;
            System.out.println("You have " + daysLeft + " until you run out of supplies.");
            System.out.println("");
        }

        //informing the player that they have beaten the game
        //ending the received is based on the number of "days"(turns) they had left
        if(turnCounter >= 20){
            System.out.println("You were unable to find the treasure, and became lost in the swamp with no food or water.");
            System.out.println("With no one to stop him, the demon proceeds to lay waste to your home.");
            System.out.println("You were never heard from again.");
            System.out.println("BAD END");
        } else if(turnCounter >= 10 && turnCounter < 20){
            System.out.println("Congratulations, you have found the legendary weapon!");
            System.out.println("Unfortunately, the demon had already destroyed your home by the time you returned. =(");
            System.out.println("You avenge your family and friends by slaying the demon.");
            System.out.println("MEH END");
        } else {
            System.out.println("Congratulations, you have found the legendary weapon!");
            System.out.println("After claiming your prize, you made your way triumphantly out of the swamp and defeated the demon.");
            System.out.println("You are hailed as a hero for the rest of your days, and go on to defeat many monsters with the legendary weapon.");
            System.out.println("GOOD END");
        }
        System.out.println("Exiting game.");
    }
}
