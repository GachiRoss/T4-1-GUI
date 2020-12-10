package Room_related;

public class RecyclingCenter extends Room {
    private Object[][] coordinateSystem;
    private Trash can = new Trash( "can", TrashType.METAL);
    private Trash battery = new Trash( "battery", TrashType.HAZARDOUSWASTE);
    private Trash pizzaBox = new Trash( "pizzaBox", TrashType.RESIDUALWASTE);
    private Trash plasticBag = new Trash( "plasticBag", TrashType.PLASTIC);
    private Trash porcelainPlate = new Trash( "porcelainPlate", TrashType.HAZARDOUSWASTE);
    private Container metalContainer = new Container("Metal Container", TrashType.METAL);
    private Container hazardousContainer = new Container("Hazardous Container", TrashType.HAZARDOUSWASTE);
    private Container residualContainer = new Container("Residual Container", TrashType.RESIDUALWASTE);
    private Container plasticContainer = new Container("Plastic Container", TrashType.PLASTIC);
    private Container[] containers = {metalContainer, hazardousContainer, residualContainer, plasticContainer};
    public RecyclingCenter(String description) {
        super(description);
        coordinateSystem = new Object[29][29];

        coordinateSystem[10][10] = battery;
        coordinateSystem[5][5] = can;
        coordinateSystem[7][8] = pizzaBox;
        coordinateSystem[3][20] = plasticBag;
        coordinateSystem[20][20] = porcelainPlate;

        coordinateSystem[11][11] = metalContainer;
    }
    /*
    public Container[] getContainers() {
        return containers;
    }

     */
    @Override
    public Object[][] getCoordinateSystem() {
        return coordinateSystem;
    }
    @Override
    public void setCoordinateSystem(Object[][] coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
    }
}
