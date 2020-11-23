package Room_related;

public class Trash {
    // Attributter:
    private String material;
    private String name;
    private int ID;
    /*
    ID = material
    1 = metal
    2 = Hazardous waste
    3 = Residual waste
    4 = Plastic
     */

    // Constructor: objects (trash) are made here
    Trash (String material, String name, int trashID){
        this.material = material;
        this.name = name;
        this.ID = trashID;
        // without constructor here we can't create trash objects in Room
    }

    public String getName()
    {
        return name;
    }

    public int getID(){
        return ID;
    }

    public String getMaterial() {
        return material;
    }
}
