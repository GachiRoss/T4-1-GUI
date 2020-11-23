package Room_related;

public class RecyclingCenter extends Room {
    public RecyclingCenter(String description, String name) {
        super(description, name);
    }

    private Container metalContainer = new Container("Metal Container", 1);
    private Container hazardousContainer = new Container("Hazardous Container", 2);
    private Container residualContainer = new Container("Residual Container", 3);
    private Container plasticContainer = new Container("Plastic Container", 4);
    private Container[] containers = {metalContainer, hazardousContainer, residualContainer, plasticContainer};

    @Override
    public Container[] getContainers() {
        return containers;
    }
}
