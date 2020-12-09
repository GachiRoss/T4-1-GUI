import java.util.ArrayList;
import java.util.Scanner;
import command.*;
import Room_related.*;
import javafx.scene.control.ListView;

public class Player {
    // The player:
    private String name;
    private int points = 0;
    private Scanner scanner = new Scanner(System.in);
    private int whatContain;
    private Trash trash;
    private int x;
    private int y;


    // The inventory made as an ArrayList with capacity 5
    public ArrayList<Trash> inventoryList = new ArrayList<Trash>(5);

    // Constructor:
    Player(String name) {
        this.name = name;
        points = 0;

    }

    // Getters and setters for player:
    private String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }


    //Methods
    public String pickUp() {
        if (Game.getCurrentRoom().getCoordinateSystem()[x][y] instanceof Trash) {
            Object[][] roomArray = Game.getCurrentRoom().getCoordinateSystem();
            Trash trash = (Trash)roomArray[x][y];
            inventoryList.add(trash);
            roomArray[x][y] = null;
            Game.getCurrentRoom().setCoordinateSystem(roomArray);
            String item = "Slot " + (inventoryList.indexOf(trash) +1) + ": " + trash.getName();
            return item;
        }
        return null;
    }

    public void openInventory(Command command) {

        if (command.hasSecondWord() == true) {
            System.out.println("Check what inventory?!");
        } else {
            for (int i = 0; i < inventoryList.size(); i++) {
                // Prints out a description of the inventory list
                System.out.println("Slot " + (i + 1) + ": " + inventoryList.get(i).getName());
            }
            System.out.println();
        }
        System.out.println();
    }

    /*public void inspectItem(Command command) {
        if (command.hasSecondWord() == true) {
            int index = Integer.parseInt(command.getSecondWord()) - 1;
            Trash trash = inventoryList.get(index);
            System.out.println("The name of the item is \"" + trash.getName() + "\", which means it should be sorted with " + trash.getMaterial());
        } else {
            System.out.println("Check what item?!");
        }
    }*/

    //method for dropping items in the containers
    public ListView dropItem(ListView listView) {

        Object container = Game.getCurrentRoom().getCoordinateSystem()[x][y - 1];
        Trash dropTrash = null;
        for(Trash trash: inventoryList) {
            String item = "[" + "Slot " + (inventoryList.indexOf(trash) + 1) + ": " + trash.getName() + "]";
            if (item.equals(listView.getSelectionModel().getSelectedItems().toString())) {
                dropTrash = trash;
            }
        }
        if (Game.getCurrentRoom() instanceof RecyclingCenter && container instanceof Container && dropTrash != null) {
            givePoints((Container) container, dropTrash);
            inventoryList.remove(dropTrash);
            listView.getItems().removeAll();
            for (Trash trash : inventoryList) {
                String item = "Slot " + (inventoryList.indexOf(trash) +1) + ": " + trash.getName();
                listView.getItems().add(item);
            }
        }
        return listView;
    }

    private void givePoints(Room_related.Container container, Trash trash) {
        points += container.checkRecycling(trash);
        System.out.println("You got " + points + " point(s) in total!");
    }

    //method for allowing the player to search for trash in a room
    public void search() {
        //uses a for loop to iterate through the trash arraylist for the current room. Calls the getCurrentRoom method from the Game class
        for (int i = 0; i < Game.getCurrentRoom().trash.size(); i++) {
            //prints out the trash objects in the arraylist via the for loop
            System.out.println(Game.getCurrentRoom().trash.get(i).getName());
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ArrayList<Trash> getInventoryList() {
        return inventoryList;
    }

    public String getHandbook (){
        String string = "Plastic---------------------------------------------------- \n" +
                "Plastic trash is made out of plastic. Plastic trash could end up\n" +
                "in landfills, it could get incinerated or get reused. \n" +
                "Plastic trash examples:\n" +
                "Plastic bottles, plastic cutlery, plastic toys etc. \n" +
                "Metal-------------------------------------------------- \n" +
                "Trash containing Metal have to be disposed in the Metal Container.\n" +
                "Even though a lot of energi is used to reuse metal, it still won't\n" +
                "use as much energi as it takes to extract metal.\n" +
                "Metal trash examples:\n" +
                "Metal cans, metal bowls for animals, metal cutlery etc. \n" +
                "Harzardous Waste---------------------------------------------- \n" +
                "Hazardous Waste can't be disposed alongside regular trash since it-\n" +
                "might contain something harmful for either the environment or-\n" +
                "the people handling the trash. If a product is labelled with:\n" +
                "WARNING, CAUTION, FLAMMABLE, TOXIC, CORROSIVE or EXPLOSIVE it should\n" +
                "be thrown out with Hazardous Waste.\n" +
                "Hazardous Waste examples:\n" +
                "porcelain plate , battery, deodorants, paint etc. \n" +
                "Residual Waste-------------------------------------------------- \n" +
                "In Denmark Residual Waste gets burned to create electricity.\n" +
                "Residual waste is the leftover trash after sorting out -\n" +
                "reusable trash such as Plastic, Metal and sorting out-\n" +
                "Hazardous Waste.\n" +
                "Residual Waste examples:\n" +
                "Pizzabox, diapers, vacuum bags, milk og juiceboxes etc. \n";
        return string;
    }
}