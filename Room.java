import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * Class Room - a room in an adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * Each room has a first description, a return description, and a smash string
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
    private boolean canSmash = false;
    private String smashString = "";

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
     * Overflow constructor that allows setting of a specific smash string upon room initialization
     * @param firstDescription The description that is displayed when entering a room for the first time.
     * @param returnDescription The description that is displayed when returning to a room
     * @param smashString The blurb of text that is displayed when the player uses the smash command in the room
     */
    public Room(String[] firstDescription, String returnDescription, String smashString) 
    {
        this.firstDescription = firstDescription;
        this.returnDescription = returnDescription;
        this.smashString = smashString;
        exits = new HashMap<>();
    }
    /**
     * Overflow constructor that allows setting of the canSmash global variable for the room
     * @param firstDescription The description that is displayed when entering a room for the first time.
     * @param returnDescription The description that is displayed when returning to a room
     * @param canSmash determines whether or not smash can be used in the room. Set to false in other constructors
     */
    public Room(String[] firstDescription, String returnDescription, boolean canSmash)
    {
        this.firstDescription = firstDescription;
        this.returnDescription = returnDescription;
        this.canSmash = canSmash;
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
    
    /**
     * Prints out the description that is shown when the player enters the room for the first time
     */
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
    
    /**
     * Determines whether or not a room has been visited and prints the correct description
     */
    public void visitRoom(){
        if(!beenVisited){
            beenVisited = true;
            printFirstDescription();
            System.out.println(getExitString());
        }else{
            System.out.println(getReturnDescription());
        }
    }
    
    /**
     * Returns a boolean that determines whether or not the room has been visisted
     * @return true if the room has been visited, false otherwise
     */
    public boolean hasBeenVisited(){
        if(beenVisited){
            return true;
        }
        return false;
    }
    
    /**
     * Allows setting of the return string
     * @param newDescription The string that returnDescription will be set to
     */
    public void setReturnString(String newDescription){
        this.returnDescription = newDescription;
    }
    /**
     * Returns smash string
     * @return Either returns a random string if the global variable smashString has not been set, otherwise smashString is returned
     */
    public String getSmashString(){
        String[] smashStrings = {"You hear a voice in your head \"Ash, now isn\'t the time to use that.\" You are confused...",
        "You don't think now is the time for that...",
        "Maybe later...", "Not right now...", "No..."};
        if(this.smashString.equals("")){
            Random rand = new Random();
            return smashStrings[rand.nextInt(smashStrings.length)];
        }
        return this.smashString;
    }
    /**
     * Determines whether the smash command can be used in the room
     * @return value of this.canSmash
     */
    public boolean canSmash(){
        return this.canSmash;
    }
    /**
     * Sets whether or not smash can be used in the oom
     * @param smash What canSmash should be set to
     */
    public void setSmash(boolean smash){
        this.canSmash = smash;
    }
    /**
     * Sets the smash string
     * @param newSmashString What smashString should be set to
     */
    public void setSmashString(String newSmashString){
        this.smashString = newSmashString;
    }
}

