import Room_related.MapObjekt;
import com.sun.prism.Image;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class Controller {

    @FXML
    private ListView inventory;

    @FXML
    private TextArea textArea;

    @FXML
    private Button help;

    @FXML
    private Button handbook;

    @FXML
    private Button restart;

    @FXML
    private ImageView player;

    @FXML
    void keyPressed(KeyEvent key) {
        switch (key.getCode().toString()) {
            case "W":
                if (Game.getCurrentRoom().getCoordinateSystem()[Game.getPlayer().getX()][Game.getPlayer().getY() - 1] != MapObjekt.NONWALKABLE) {
                    Game.getPlayer().setY(Game.getPlayer().getY() - 1);
                    player.setY(player.getY() - 40);
                }
                break;
            case "S":
                if (Game.getCurrentRoom().getCoordinateSystem()[Game.getPlayer().getX()][Game.getPlayer().getY() + 1] != MapObjekt.NONWALKABLE) {
                    Game.getPlayer().setY(Game.getPlayer().getY() + 1);
                    player.setY(player.getY() + 40);
                }
                break;
            case "A":
                if (Game.getCurrentRoom().getCoordinateSystem()[Game.getPlayer().getX() - 1][Game.getPlayer().getY()] != MapObjekt.NONWALKABLE) {
                    Game.getPlayer().setX(Game.getPlayer().getX() - 1);
                    player.setX(player.getX() - 40);
                }
                break;
            case "D":
                if (Game.getCurrentRoom().getCoordinateSystem()[Game.getPlayer().getX() + 1][Game.getPlayer().getY()] != MapObjekt.NONWALKABLE) {
                    Game.getPlayer().setX(Game.getPlayer().getX() + 1);
                    player.setX(player.getX() + 40);
                }
                break;
            case "F":
                if (Game.getPlayer().getInventoryList().size() < 5)
                    inventory.getItems().add(Game.getPlayer().pickUp());
                break;
            case "G":
                //skraldet bliver ved med at være der
                ListView listView = Game.getPlayer().dropItem(inventory);
                inventory = listView;
                break;
        }
        System.out.println(Game.getPlayer().getX() + ", " + Game.getPlayer().getY());
    }

    @FXML
    void printHandbook(MouseEvent event) {
        textArea.setText(Game.getPlayer().getHandbook());
    }

    @FXML
    void printHelp(MouseEvent event) {
        textArea.setText(Game.getHelp());
    }

    @FXML
    void restart(MouseEvent event) {
        textArea.clear();
    }
}

