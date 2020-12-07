package Room_related;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashMap;

public class Room {
    private String description;

    // Trash instance variable:
    public ArrayList<Trash> trash = new ArrayList<Trash>(5); // creates an ArrayList
    private HashMap<String, Room> exits; //?
    private Object[][] coordinateSystem;
    private Random random = new Random();

    /*
    private Trash can = new Trash( "can", TrashType.METAL);
    private Trash battery = new Trash( "battery", TrashType.HAZARDOUSWASTE);
    private Trash pizzaBox = new Trash( "pizzaBox", TrashType.RESIDUALWASTE);
    private Trash plasticBag = new Trash( "plasticBag", TrashType.PLASTIC);
    private Trash porcelainPlate = new Trash( "porcelainPlate", TrashType.HAZARDOUSWASTE);

     */


    // Constructor: adds trash objects of Trash to ArrayList trash


    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<String, Room>();

        coordinateSystem = new Object[29][29];

        /*
        coordinateSystem[10][10] = battery;
        coordinateSystem[5][5] = can;
        coordinateSystem[7][8] = pizzaBox;
        coordinateSystem[3][20] = plasticBag;
        coordinateSystem[20][20] = porcelainPlate;

         */


    }

    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    public Container[] getContainers() {
        System.out.println("you're not at the Recycling Center, wait til you get there.");
        return null;
    }

    public Object[][] getCoordinateSystem() {
        return coordinateSystem;
    }

    public void setCoordinateSystem(Object[][] coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
    }

    public void createTrash(){
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

                if (coordinateSystem[x][y] == null) {
                    Object[][] newCoordinateSystem = coordinateSystem;
                    newCoordinateSystem[x][y] = trash;

                    coordinateSystem = newCoordinateSystem;
                }
                else {
                    i--;
                }
                System.out.println(trash.getName());
            }
        }
    }
}
