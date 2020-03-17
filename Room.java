import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private String[] firstDescription;
    private String returnDescription;
    private boolean beenVisited = false;         // keeps track of if the room has been visited.
    private HashMap<String, Room> exits;        // stores exits of this room.

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param firstDescription The description that is displayed when entering a room for the first time.
     * @param returnDescription The description that is displayed when returning to a room
     */
    public Room(String[] firstDescription, String returnDescription) 
    {
        this.firstDescription = firstDescription;
        this.returnDescription = returnDescription;
        exits = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Picks a description starter at random and returns a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getReturnDescription()
    {
        Random rand = new Random();
        String[] descriptionStarters = {"You are ", "You find yourself ", "You think you're ", "It looks like you're "};
        
        return descriptionStarters[rand.nextInt(descriptionStarters.length)]+ returnDescription + ".\n" + getExitString();
    }
    
    private void printFirstDescription()
    {
        for(int i = 0; i < firstDescription.length; i++){
            System.out.println(firstDescription[i]);
        }
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public void visitRoom(){
        if(!beenVisited){
            beenVisited = true;
            printFirstDescription();
            System.out.println(getExitString());
        }else{
            System.out.println(getReturnDescription());
        }
    }
}

