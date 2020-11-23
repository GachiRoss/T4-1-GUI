import package.command.*;

public class Game {
    // Der erklæres to variabler
    private Parser parser;
    private static Room currentRoom;
    private Player player;

    // constructor - kører metode CreateRooms og laver et nyt objekt
    public Game()
    {
        parser = new Parser();
    }

    // metode
    private void createRooms()
    {
        Room park, beach, street, conSite, foodTruck, home, reCenter;
        // rum dannes som objekter
        park = new Room("outside in a nice ass park", "park");
        beach = new Room("outside on a cool beach", "beach");
        street = new Room("out on the streets, take a knife with you >:)", "street");
        conSite = new Room("on a wack ass construction site >:(", "conSite");
        foodTruck = new Room("next to a dope ass food truck... mmmm it do be smelling good", "foodTruck");
        home = new Room("in your nasty as hell apartment... wait was that a rat!?", "home");
        reCenter = new RecyclingCenter("The place you sort trash", "reCenter");

        //exits til rummene erklæres via metoden setExit
        home.setExit("south", foodTruck);

        foodTruck.setExit("north", home);
        foodTruck.setExit("east", conSite);
        foodTruck.setExit("west", street);

        conSite.setExit("west", foodTruck);

        street.setExit("east", foodTruck);
        street.setExit("west", beach);
        street.setExit("south", park);
        street.setExit("north", reCenter);

        beach.setExit("east", street);

        park.setExit("north", street);

        reCenter.setExit("south", street);

        currentRoom = home;
    }

    //ny metode
    public void play() {
        int finished = 1;
        while (finished == 1) {
            createRooms();
            printWelcome();
            player = new Player("Bob");
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
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
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
            player.inspectItem(command);
        }
        else if (commandWord == CommandWord.PICKUP) {
            player.pickUp(command);
        }
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

    //handbook. Should print out text describing the game and the basic trashsorting function
    /*
    private void help() {

        System.out.println("The game operates with four kinds of trash: Metal, plastic, residual waste and dangerous waste");
        System.out.println("You'll get more points if you sort the trash correctly");
        System.out.println("Incorrect sorting will result in a loss of points");
        System.out.println("Metallic trash generally consists of things like cans and other metal objects");
        System.out.println("Plastic trash is a very common type of trash. Plastic bottles, bags, lids and many types of toys (Without the mechanical parts of course)");
        System.out.println("Residual waste is everything that cannot be reused. Things like kitchen waste is one of the most common types of residual waste");
        System.out.println("Dangerous waste is classified as trash dangerous to humans or nature. This includes hospital waste, ceramics, chemicals and cleaning reagents");

        //should show the commands available to the player
        parser.showCommands();
    }
    */












    public static Room getCurrentRoom() {
        return currentRoom;
    }

    private void handbook (){
        if (currentRoom.getName().equals("reCenter")){
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
}
