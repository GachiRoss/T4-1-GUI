import command.*;
import Room_related.*;

import java.util.Random;

public class Game {
    // Der erklæres to variabler
    private Parser parser;
    private static Room currentRoom;
    private static Player player;
    private static Random random = new Random();


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

        currentRoom = home;



    }

    public static void createTrash(Room room){
        // if statement der tjekker om man er på koordinater, hvor spilleren ikke kan gå hen

        // for loop med math.random til at oprette trash objekter tilfældigt i koordinatsystemet for hvert room
        for(int i = 0; i < 5; i++){

            Trash trash;
            String[][] trashArray = {{"can","aluminium foil"},{"battery", "syringe"},{"pizza box", "milk carton"},{"plastic bag", "plastic bottle"}};
            
            int x = random.nextInt(29);
            int y = random.nextInt(29);

            int type = random.nextInt(4);
            int name = random.nextInt(trashArray[type].length);


            if (type < 4 && trashArray[type].length >= name) {
                TrashType trashType;
                switch (type) {
                    case 0:
                        trashType = TrashType.METAL;
                        break;
                    case 1:
                        trashType = TrashType.HAZARDOUSWASTE;
                        break;
                    case 2:
                        trashType = TrashType.RESIDUALWASTE;
                        break;
                    case 3:
                        trashType = TrashType.PLASTIC;
                        break;

                    default:
                        trashType = TrashType.METAL;
                }
                trash = new Trash(trashArray[type][name], trashType);

                if (room.getCoordinateSystem()[x][y] == null) {
                    Object[][] newCoordinateSystem = room.getCoordinateSystem();
                    newCoordinateSystem[x][y] = trash;

                    room.setCoordinateSystem(newCoordinateSystem);
                }
                System.out.println(trash.getName());
            }
        }
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
            printHelp();
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
            player.dropItem(command);
        }
        else if (commandWord == CommandWord.HANDBOOK) {
            handbook();
        }
        return wantToQuit;
    }



    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
        System.out.println("Important note: ");
        System.out.println("When referring to something in the room, use the name of the item.");
        System.out.println("However, if you're referring to something in your inventory, use the index of the slot it is stored in.");
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

    private void handbook (){
        if (currentRoom instanceof RecyclingCenter){
            System.out.println("Plastic---------------------------------------------------- ");
            System.out.println("Plastic trash is made out of plastic. Plastic trash could end up\n" +
                    "in landfills, it could get incinerated or get reused. \n" +
                    "Plastic trash examples:\n" +
                    "Plastic bottles, plastic cutlery, plastic toys etc.");
            System.out.println("Metal--------------------------------------------------");
            System.out.println("Trash containing Metal have to be disposed in the Metal Container.\n" +
                    "Even though a lot of energi is used to reuse metal, it still won't\n" +
                    "use as much energi as it takes to extract metal.\n" +
                    "Metal trash examples:\n" +
                    "Metal cans, metal bowls for animals, metal cutlery etc.");
            System.out.println("Harzardous Waste----------------------------------------------");
            System.out.println("Hazardous Waste can't be disposed alongside regular trash since it-\n" +
                    "might contain something harmful for either the environment or-\n" +
                    "the people handling the trash. If a product is labelled with:\n" +
                    "WARNING, CAUTION, FLAMMABLE, TOXIC, CORROSIVE or EXPLOSIVE it should\n" +
                    "be thrown out with Hazardous Waste.\n" +
                    "Hazardous Waste examples:\n" +
                    "porcelain plate , battery, deodorants, paint etc.");
            System.out.println("Residual Waste--------------------------------------------------");
            System.out.println("In Denmark Residual Waste gets burned to create electricity.\n" +
                    "Residual waste is the leftover trash after sorting out -\n" +
                    "reusable trash such as Plastic, Metal and sorting out-\n" +
                    "Hazardous Waste.\n" +
                    "Residual Waste examples:\n" +
                    "Pizzabox, diapers, vacuum bags, milk og juiceboxes etc.");
        }
        else {
            System.out.println("you're not at the Recycling Center, wait til you get there.");
        }
    }

    public static Player getPlayer() {
        return player;
    }

}
