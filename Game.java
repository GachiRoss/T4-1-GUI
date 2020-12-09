import command.*;
import Room_related.*;

import java.util.Random;

public class Game {
    // Der erklæres to variabler
    private Parser parser;
    private static Room currentRoom;
    private static Player player;


    // constructor - kører metode CreateRooms og laver et nyt objekt
    public Game()
    {
        parser = new Parser();
    }

    // metode
    private void createRooms()
    {
        Room park, beach, street, conSite, forest, home, reCenter;
        // rum dannes som objekter
        park = new Room("You've reached the park... refreshing.");
        beach = new Room("You've reached the beach. Lots of sand... everywhere!");
        street = new Room("You're walking through the street. Ahh the sweet smell of... pollution?");
        conSite = new Room("At a construction site. Construction will eventually resume at an unspecified time in the future.");
        forest = new Room("You've reached the forest. Everyone's favourite place to be!");
        home = new Room("You're at home. Smells kinda funny in here.");
        reCenter = new RecyclingCenter("You're at the recycling center. The place where you... well... SORT YOUR TRASH!!");

        //exits til rummene erklæres via metoden setExit
        home.setExit("south", forest);

        forest.setExit("north", home);
        forest.setExit("east", conSite);
        forest.setExit("west", street);

        conSite.setExit("west", forest);

        street.setExit("east", forest);
        street.setExit("west", beach);
        street.setExit("south", park);
        street.setExit("north", reCenter);

        beach.setExit("east", street);

        park.setExit("north", street);


        reCenter.setExit("south", street);

        home.createTrash();
        forest.createTrash();
        conSite.createTrash();
        beach.createTrash();
        street.createTrash();
        park.createTrash();

        currentRoom = reCenter;
    }


    //ny metode
    public void play() {
        int finished = 1;
        while (finished == 1) {
            createRooms();
            printWelcome();
            player = new Player("tobber00");
            finished = 0;
            while (finished == 0) {
                Command command = parser.getCommand();
                finished = processCommand(command);
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of TRASH!");
        System.out.println("World of TRASH is a new, incredibly cool trash sorting game!");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private int processCommand(Command command) {
        int wantToQuit = 0;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return 0;
        }

        if (commandWord == CommandWord.HELP) {
            //printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == commandWord.RESTART) {
            wantToQuit = restart(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        else if (commandWord == CommandWord.INSPECTITEM) {
            /*player.inspectItem(command);*/
        }
        /*
        else if (commandWord == CommandWord.PICKUP) {
            player.pickUp(command);
        }

         */
        else if (commandWord == CommandWord.OPENINVENTORY) {
            player.openInventory(command);
        }
        else if (commandWord == CommandWord.SEARCH) {
            player.search();
        }
        else if (commandWord == CommandWord.DROP) {
            //player.dropItem(command);
        }
        /*
        else if (commandWord == CommandWord.HANDBOOK) {
            handbook();
        }

         */
        return wantToQuit;
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private int restart(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Restart what?");
            return 0;
        } else {
            return 1;
        }
    }

    private int quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return 0;
        } else {
            return 2;
        }
    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }

    public static Player getPlayer() {
        return player;
    }

    public static String getHelp() {
        String string = "Welcome to the world of TRASH! \n" +
                "A trash collecting/sorting game \n" +
                "You can walk around the world using \"W, A, S, D\"\n" +
                "And collect trash using the \"F\" key, and sort it at a container using the \"G\" key \n" +
                "You get a point by sorting the trash correctly, but lose one if you sort it incorrectly \n";
        return string;
    }

}
