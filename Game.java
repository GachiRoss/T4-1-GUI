import command.*;
import Room_related.*;
import javafx.scene.control.TextArea;

import java.util.Random;

public class Game {
    // Der erklæres to variabler
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private boolean wantsToRestart = false;


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

        currentRoom = home;
    }


    //ny metode
    public void play(String name) {
        player = new Player(name);
        createRooms();
    }

    public String getWelcomeText() {
        String string = "Welcome to the World of TRASH!\n" +
                "World of TRASH is a new, incredibly cool trash sorting game!\n" +
                "Your mission is to walk around the world and collect TRASH!\n" +
                "Yeah i know, it sounds absolutely amazing doesn't it\n" +
                "Oh yeah, and by the way, you get points depending on your sorting skills!\n" +
                "If you need more information about how to play the game, press the \"help\" button\n" +
                "Well, that's all for now. Good luck adventurer, and may the gods of trash watch over you!";
        return string;
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
            //wantToQuit = restart(command);
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
        /*
        else if (commandWord == CommandWord.SEARCH) {
            player.search();
        }
        else if (commandWord == CommandWord.DROP) {
            //player.dropItem(command);
        }
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

    public void restart(TextArea textArea) {
        if (wantsToRestart == true) {
            textArea.setText("Restarting game");
            play("Tobber00");
            wantsToRestart = false;
        }
        else {
            textArea.setText("Are you sure you want to restart?\n" +
                    "Press the \"restart\" button again if you do\n" +
                    "Press the \"help\" button if you don't");
            wantsToRestart = true;
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

    
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Player getPlayer() {
        return player;
    }
     

    public String getHelp() {
        wantsToRestart = false;
        String string = "Welcome to the world of TRASH! \n" +
                "A trash collecting/sorting game \n" +
                "You can walk around the world using \"W, A, S, D\"\n" +
                "And collect trash using the \"F\" key, and sort it at a container using the \"G\" key \n" +
                "You get a point by sorting the trash correctly, but lose one if you sort it incorrectly \n";
        return string;
    }

}
