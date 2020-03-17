/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room frontYard, foyer;
      
        // create the rooms
        String[] frontYardFirstDesc = {"You find yourself outside the large structure,",
        "the still smoldering wreck of your ship at your back.",
        "You look around and see a large set of doors in front",
        "of you. You can make out a sign that reads \"Labeller Mansion\".",
        "Unfortunately for you, you can't read, but perhaps",
        "the locals find it useful."};
        
        String[] foyerFirstDesc = {"You approach the structure but halt just before",
        "the doors. You peer through the window and see",
        "several of the locals dressed in formal wear.",
        "You can't go in there looking like you do!",
        "You do what anyone from your planet would do and",
        "transform into your best approximation of a native.",
        "Confident in your looks, you walk through the doors",
        "and find another sign on the wall that reads \"Grand Foyer\".",
        "Again, you're not sure what it means but maybe",
        "someone finds it useful."};
        
        frontYard = new Room(frontYardFirstDesc, "outside of the \"Labeller Mansion\", in the front yard.");
        foyer = new Room(foyerFirstDesc, "inside, in the Grand Foyer.");
        
        frontYard.setExit("north", foyer);
        foyer.setExit("south", frontYard);
        
        // initialise room exits

        currentRoom = frontYard;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Oh No! It appears your spaceship is out of control!");
        System.out.println("You knew you shouldn't have wandered so close to that");
        System.out.println("planet. You couldn't help getting a closer look at the");
        System.out.println("beautiful blue color. Fortunately for you, the steering");
        System.out.println("isn't completely compromised. You take it down for a soft");
        System.out.println("crash near what looks to be a living space for the natives.");
        System.out.println("(Type help for the current command list and look if ");
        System.out.println("you forget where you are.)");
        System.out.println();
        currentRoom.visitRoom();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
             
            case LOOK:
                System.out.println(currentRoom.getReturnDescription());
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            currentRoom.visitRoom();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
