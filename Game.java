import java.util.ArrayList;
import java.util.Random;
/**
 *  The class creates the game object and also houses the main method
 *  for running outside of BlueJ
 *  
 *  The objective of the game is to collect all parts of your spaceship
 *  and then repair it.
 * 
 * @author  Michael Kölling, David J. Barnes, and Daniel Zetterstrom
 * @version Final
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room frontYard, foyer, sittingRoom, kitchen, livingRoom, sunRoom, diningRoom, bathroom, washRoom, courtyard1;
    private Room courtyard2, teaRoom, guestBedroom, courtyard3, grandHall, poolHall;
    private Item spaceshipPart1, spaceshipPart2, spaceshipPart3, smallKey;
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private ArrayList<Room> travelHistory = new ArrayList<Room>();
    private boolean finished, wonGame, canTele;
    
    /**
     * Main method for running game outside of JBlueJ
     */
    public static void main(String[] args){
        Game game1 = new Game();
        game1.play();
    }

    
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
        // create the rooms
        String[] frontYardFirstDesc = {"You find yourself outside the large structure,",
        "the still smoldering wreck of your ship at your back.",
        "You look around and see a large set of doors in front",
        "of you. You can make out a sign that reads \"Labeller Mansion\".",
        "Underneath that is another, smaller sign that reads \"Garden Party Today!\"",
        "Unfortunately for you, you can't read, but perhaps",
        "the locals know what it means."};
        
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
        
        String[] sittingRoomFirstDesc = {"You enter the room and see several cozy",
        "looking chairs. You consider sitting in one but think",
        "better of it as you still have a spaceship that needs",
        "repairing. Towards the back of the room is a stone structure",
        "that is covered in wood ash. Perhaps there was a fire recently?",
        "Above the door you entered is yet another sign that reads",
        "\"Sitting Room\". You wonder what it means."};
        
        String[] livingRoomFirstDesc = {"You enter the room and see a large couch",
        "facing a bright glass box. Upon closer inspection, you",
        "see people moving inside the box! \"Gazorple!\" you",
        "yell to them in greeting. They can't seem to hear you.",
        "Above the glass box is another sign that reads \"TV Room\"."};
        
        String[] kitchenFirstDesc = {"You walk toward the room but stop just outside.",
        "You hear some natives yelling about a \"keesh?\".",
        "You peer around the corner and watch as one of them,",
        "dressed in all white and a tall hat, hands",
        "something over to the other on a platter. They look even angrier.",
        "So angry, in fact, that they SMASH whatever it was right back",
        "in the others face! That's something you've never seen before!",
        "You remember what they did as it may come in handy.",
        "The first storms out and the second follows closely behind.",
        "You now enter the room, alone, and peer the sign above the door",
        "that reads \"Kitchen\".",
        "", "You have unlocked the ability to SMASH!"};

        String[] sunRoomFirstDesc = {"You step into the room and feel a radiant warmth",
        "on your face. Many wicker chairs painted white litter",
        "the room. The walls are mostly of glass except the two that",
        "are shared with interior rooms. There's a sign on the",
        "wall that reads \"Sun Room\"."};
        
        String[] diningRoomFirstDesc = {"In this room you notice a long table covered in white fabric.",
        "Around the table there are at least a dozen chairs and",
        "on the table, in front of every chair, is a white disk with weapons",
        "on either side and an overturned glass object on every disk.",
        "The natives sure are strange. Above the door is a sign that reads",
        "\"Dining Room\"."};
        
        String[] washRoomFirstDesc = {"You step inside the room and are greated with the scent of trees",
        "which is quite odd because there are none to be seen,",
        "just an odd drinking bowl that is very close to the ground.",
        "Why the natives would do such a thing is beyond you.",
        "As you bend down to quench your thirst the scent of trees leaves",
        "you and you are assaulted by a stench that you can't quite put",
        "your feelers on. Not that you would want to. You stand back up",
        "and spy a sign that reads \"Wash Room\""};
        
        String[] courtyard1FirstDesc = {"Stepping outside you find an area surrounded on all sides by",
        "the mansion. You feel trapped. You peer a sign behind you that reads",
        "\"Grand Courtyard\". Perhaps this is where the natives keep their enemies."};
        
        String[] courtyard2FirstDesc = {"You step further into the courtyard and still feel",
        "trapped. You wish your mother was here to hold you."};
        
        String[] teaRoomFirstDesc = {"Back inside you find a room very similar to the",
        "sitting room. Chairs litter the space and near the back",
        "is a large container full of white cups.",
        "Upon opening the container, you realize that these cups",
        "are made from fired clay! You can use these to repair",
        "your ship! But they need to be in smaller pieces.",
        "If only there was a way you could break them up. Above the",
        "container is a sign that reads \"Tea Room\""};
        
        String[] courtyard3FirstDesc = {"You step further into the courtyard only because",
        "you must. You hate every second of it."};
        
        String[] guestBedFirstDesc = {"You enter the room and see a very nice looking bed.",
        "To your right is a large wooden box and to your left, a window.",
        "You walk over to the window and see a few of the natives moving outside.",
        "Not wanting to draw attention, you back away from the window.",
        "Above the window is a sign that reads \"Guest Bedroom\".",
        "You still can\'t read."};
        
        String[] poolFirstDesc = {"Turning the key in the lock you feel a sense of anxiousness.",
        "What could lie on the other side of the door?",
        "You can barely contain your excitement. You hopes",
        "are immediately dashed when you see a large green table",
        "with different colored spheres on top. On the opposite",
        "wall is a long reflective surface that stretches the entirety",
        "of the wall. Above the door you entered is a sign that reads",
        "\"Pool Hall\". You have no idea what the point of this",
        "room is but you hate it."};
        
        String[] grandHallFirstDesc = {"You enter the room and feel immidiately small.",
        "\"There must be a hundred chairs!\" you think to yourself.",
        "A massive table runs the length of the room and hanging over",
        "the table is perhaps the most elaborate sign of the whole",
        "place that reads \"Grand Hall\". You wish you knew what it meant."};
        
        frontYard = new Room(frontYardFirstDesc, "outside of the \"Labeller Mansion\", in the front yard.", true);
        foyer = new Room(foyerFirstDesc, "inside, in the Grand Foyer.");
        sittingRoom = new Room(sittingRoomFirstDesc, "in the Sitting Room, with a cozy fireplace at the back.");
        livingRoom = new Room(livingRoomFirstDesc, "in the TV Room, with the tiny box people still ignoring you.", true);
        kitchen = new Room(kitchenFirstDesc, "in the kitchen, the smell is enticing.");
        sunRoom = new Room(sunRoomFirstDesc, "in the Sun Room, the light from the sun warms your face");
        diningRoom = new Room(diningRoomFirstDesc, "in the Dining Room, but it really should be called the weapons room.");
        washRoom = new Room(washRoomFirstDesc, "in the Wash Room, with the odd, smelly drinking bowl.");
        courtyard1 = new Room(courtyard1FirstDesc, "in the Grand Courtyard, you feel helpless and alone.");
        courtyard2 = new Room(courtyard2FirstDesc, "in the Grand Courtyard, you call out for your mother.");
        teaRoom = new Room(teaRoomFirstDesc, "in the Tea Room, the cups temp you to break them.", true);
        courtyard3 = new Room(courtyard3FirstDesc, "in the Grand Courtyard, hating your existence.");
        guestBedroom = new Room(guestBedFirstDesc, "in the Guest Bedroom, the bed ever tempting for a nap.", true);
        poolHall = new Room(poolFirstDesc, "in the pointless green table room, at least you can wave to your reflection.", true);
        grandHall = new Room(grandHallFirstDesc, "in the Grand Hall, you can barely make out the back wall.");
        
        frontYard.setExit("north", foyer);
        
        foyer.setExit("south", frontYard);
        foyer.setExit("west", sittingRoom);
        foyer.setExit("east", kitchen);
        foyer.setExit("north", livingRoom);
        
        sittingRoom.setExit("east", foyer);
        
        kitchen.setExit("west", foyer);
        kitchen.setExit("north", sunRoom);
        kitchen.setExit("east", diningRoom);
        
        livingRoom.setExit("south", foyer);
        livingRoom.setExit("east", sunRoom);
        livingRoom.setExit("north", washRoom);
        
        sunRoom.setExit("west", livingRoom);
        sunRoom.setExit("south", kitchen);
        sunRoom.setExit("north", courtyard1);
        
        diningRoom.setExit("west", kitchen);
        
        washRoom.setExit("south", livingRoom);
        
        courtyard1.setExit("south", sunRoom);
        courtyard1.setExit("north", courtyard2);
        
        courtyard2.setExit("south", courtyard1);
        courtyard2.setExit("east", teaRoom);
        courtyard2.setExit("north", courtyard3);
        
        teaRoom.setExit("west", courtyard2);
        
        courtyard3.setExit("south", courtyard2);
        courtyard3.setExit("west", guestBedroom);
        courtyard3.setExit("north", poolHall);
        courtyard3.setExit("east", grandHall);
        
        guestBedroom.setExit("east", courtyard3);
       
        poolHall.setExit("south", courtyard3);
        
        grandHall.setExit("west", courtyard3);
        
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
                
        finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if(wonGame){
                finished = true;
            }
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
                
            case BACK:
                if(travelHistory.size() > 0){
                    goBack();
                    System.out.println(currentRoom.getReturnDescription());
                }else{
                    System.out.println("You can't go back any further!");
                }
                break;
                
            case TRANS:
                if(canTele){
                    if(travelHistory.size() > 0){
                        Random rand = new Random();
                        currentRoom = travelHistory.get(rand.nextInt(travelHistory.size()));
                        System.out.println(currentRoom.getReturnDescription());
                    }else{
                        System.out.println("Your travel history is clear!");
                    }
                }else{
                    System.out.println("You can't do that yet!");
                }
                break;
                
            case SMASH:
                if(kitchen.hasBeenVisited()){
                    if(currentRoom.canSmash()){
                        if(currentRoom == livingRoom){
                            System.out.println("You flail wildly towards the box shouting \"I\'ll save you!\"\n" + 
                            "With your eyes closed, your fist collides with the box\n" + 
                            "and you hear a loud shatter! \"Success!\" you think\n" +
                            "to yourself but your happiness is short lived...\n" + 
                            "When you open your eyes you see the box shattered but\n" +
                            "there\'s no one to be found? You must have scared them off\n" +
                            "in your rage. But what's this? Behind the glass you\n" +
                            "see some wires and pieces of electrical equipment.\n" +
                            "You can use these to repair your spaceship! You put them\n" +
                            "in your pockets for later.");
                            if(inventory.size() > 0){
                                System.out.println("\nUse \"inv\" to check your inventory!");
                            }else{
                                System.out.println("You can now check your inventory! Type \"inv\" to see");
                                System.out.println("what you've got!");
                            }
                            spaceshipPart1 = new Item("A jumble of electronic parts from the glass box", 1);
                            inventory.add(spaceshipPart1);
                            if(checkAllParts()){
                                System.out.println("That's the last part you need. You better\n"+
                                "make your way back to your ship in the front yard...");
                            }
                            currentRoom.setSmash(false);
                            currentRoom.setSmashString("The box is already broken. Haven\'t you done enough?");
                            currentRoom.setReturnString("in the TV Room, with the smashed up box.");
                            
                        }else if(currentRoom == teaRoom){
                            System.out.println("You pick up the ceramic in your fist and\n" +
                            "using all the force you can muster you slam it against the wall.\n" +
                            "What once was a cohesive cup is now nothing but shards on the ground.\n" +
                            "You pick them up victoriously and shove them in your pocket.\n" +
                            "You'll definitely be needing those.");
                            if(inventory.size() > 0){
                                System.out.println("\nUse \"inv\" to check your inventory!");
                            }else{
                                System.out.println("You can now check your inventory! Type \"inv\" to see");
                                System.out.println("what you've got!");                           
                            }
                            spaceshipPart2 = new Item("A bunch of sharp ceramic shards", 1);
                            inventory.add(spaceshipPart2);
                            if(checkAllParts()){
                                System.out.println("That's the last part you need. You better\n"+
                                "make your way back to your ship in the front yard...");
                            }
                            currentRoom.setSmash(false);
                            currentRoom.setSmashString("There's nothing left to SMASH!");
                            currentRoom.setReturnString("in the Tea Room, the ceramic smashed to bits");
                        }else if(currentRoom == guestBedroom){
                            System.out.println("You turn your rage to the wooden box.\n" +
                            "You swing for what feels like hours and finally\n" +
                            "the box gives way. Inside is a small key, it will open a\n" +
                            "locked door, but only in this area. You store it\n" +
                            "in your pocket for later use.");
                            if(inventory.size() > 0){
                                System.out.println("\nUse \"inv\" to check your inventory!");
                            }else{
                                System.out.println("You can now check your inventory! Type \"inv\" to see");
                                System.out.println("what you've got!");                           
                            }
                            smallKey = new Item("A small key", 1);
                            inventory.add(smallKey);
                            currentRoom.setSmash(false);
                            currentRoom.setSmashString("You shall not SMASH! You've already done that here!");
                            currentRoom.setReturnString("in the Guest Bedroom, the wooden box destroyed.");
                        }else if(currentRoom == poolHall){
                            System.out.println("You\'re sick of your reflections smug,\n"+
                            "stupid face. What do they know? You get\n"+
                            "so angry you throw a punch, and then another.\n"+
                            "Soon enough, the entire reflective wall is reduced to particles.\n"+
                            "Wait a minute...Is this silver!? You'll need this\n"+
                            "for repairing your ship. You scoop up as many pieces\n"+
                            "as you can and shove them in your pockets.");
                            if(inventory.size() > 0){
                                System.out.println("\nUse \"inv\" to check your inventory!");
                            }else{
                                System.out.println("You can now check your inventory! Type \"inv\" to see");
                                System.out.println("what you've got!");                           
                            }
                            spaceshipPart3 = new Item("A pile of silver dust", 1);
                            inventory.add(spaceshipPart3);
                            if(checkAllParts()){
                                System.out.println("That's the last part you need. You better\n"+
                                "make your way back to your ship in the front yard...");
                            }
                            currentRoom.setSmash(false);
                            currentRoom.setSmashString("Your reflection is gone now and you can't find the rage\nto do it again.");
                            currentRoom.setReturnString("in the green table room, you find it much less stupid now.");
                        }else if(currentRoom == frontYard){
                            System.out.println("You look at your spaceship and wonder...\n"+
                            "Surely you're not actually going to smash it but perhaps\n"+
                            "just a light tap? You give it your softest\n"+
                            "love tap and the external storage pops open.\n"+
                            "Out falls your transporter which looks a little\n"+
                            "worse for wear. What's the worst that could happen?\n"+
                            "\nYou can now use \"trans\" to teleport to a random room\n"+
                            "in your current travel history! Using the back command\n"+
                            "too much will clear your travel history.");
                            
                            canTele = true;
                            currentRoom.setSmash(false);
                            currentRoom.setSmashString("Smashing anymore and you'll damage your ship irreparably!");
                        }
                    }else{
                        System.out.println(currentRoom.getSmashString());
                    }
                }else{
                    System.out.println("You don\'t know how to do that yet...");
                }
                break;
                
            case INV:
                if(inventory.size() > 0){
                    checkInv();
                }else{
                    System.out.println("There's nothing in your pockets!");
                }
                break;
        }
        return wantToQuit;
    }


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
        if(currentRoom == courtyard3 && direction.equals("north")){
            if(!inventory.contains(smallKey)){
                System.out.println("You reach for the handle to turn but find that\n" +
                "it won\'t budge. It appears to be locked.");
                System.out.println(currentRoom.getReturnDescription());
                return;
            }
        }else if(currentRoom == foyer && direction.equals("south") && checkAllParts()){
            System.out.println("You leave the mansion with all of the parts you need to repair your ship.\n" +
            "You rush over to your ship and get to work. Combining all\n" +
            "the parts you gathered you're able to get your ship\'s computer\n" +
            "back online. You set its navigation back to your home planet\n" +
            "and begin your ascent. Just as you're about to warp away\n" +
            "you glance back down at the faint spec that is the mansion\n" +
            "and feel bittersweet. You're going to miss it.");
            
            System.out.println("\nYOU WIN!");
            wonGame = true;
            return;
        }else if(currentRoom == kitchen && direction.equals("west")){
            System.out.println("You try to exit but the door only opens one way!\n"+
            "You\'ll have to leave a different way.");
            return;
        }
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }else {
            travelHistory.add(currentRoom);
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
    /**
     * Prints out player's inventory
     */
    private void checkInv(){
        for(Item invItem : inventory){
            System.out.println(invItem.getDescription());
        }
    }
    /**
     * Checks if all space ship parts have been collected
     * @return true if all parts have been collected, false otherwise
     */
    private boolean checkAllParts(){
        if(inventory.contains(spaceshipPart1) && inventory.contains(spaceshipPart2) && inventory.contains(spaceshipPart3)){
            return true;
        }
        return false;
    }
    /**
     * Goes back one room and then removes room from list of previous rooms
     */
    private void goBack(){
        currentRoom = travelHistory.get(travelHistory.size() - 1);
        travelHistory.remove(travelHistory.size() - 1);
    }
}
