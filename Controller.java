import Room_related.ChangeObjekt;
import Room_related.MapObjekt;
import Room_related.Trash;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;


public class Controller {

    private Game game = new Game();

    @FXML
    private Parent gameLayout;

    @FXML
    private Scene scene;

    @FXML
    private ImageView room;

    @FXML
    private ListView inventory;

    @FXML
    private TextArea textArea;

    @FXML
    private ImageView player;

    @FXML
    private ImageView parkScene = new ImageView("park.jpg");

    public Controller() {
        game.play("bob");
    }

    @FXML
    void keyPressed(KeyEvent key) throws IOException {
        switch (key.getCode().toString()) {
            case "W":
                if (game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX()][game.getPlayer().getY() - 1] != MapObjekt.NONWALKABLE) {
                    game.getPlayer().setY(game.getPlayer().getY() - 1);
                    player.setY(game.getPlayer().getY() * 40);
                }
                break;
            case "S":
                if (game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX()][game.getPlayer().getY() + 1] != MapObjekt.NONWALKABLE) {
                    game.getPlayer().setY(game.getPlayer().getY() + 1);
                    player.setY(game.getPlayer().getY() * 40);
                }
                break;
            case "A":
                if (game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX() - 1][game.getPlayer().getY()] != MapObjekt.NONWALKABLE) {
                    game.getPlayer().setX(game.getPlayer().getX() - 1);
                    player.setX(game.getPlayer().getX() * 40);
                }
                break;
            case "D":
                if (game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX() + 1][game.getPlayer().getY()] != MapObjekt.NONWALKABLE) {
                    game.getPlayer().setX(game.getPlayer().getX() + 1);
                    player.setX(game.getPlayer().getX() * 40);
                }
                break;
            case "F":
                String trash = game.getPlayer().pickUp(game.getCurrentRoom());
                if (game.getPlayer().getInventoryList().size() < 5 && trash != null) {
                    inventory.getItems().add(trash);
                }
                break;
            case "G":
                //skraldet bliver ved med at være der
                game.getPlayer().dropItem(inventory, game.getCurrentRoom());
                break;
            case "C":
                //skifter til park.jpg //hvorfor bruger vi jpgs? Vil vi gerne have at det ligner billeder fra Sovjetunionen?
                if (game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX() + 1][game.getPlayer().getY()].equals(ChangeObjekt.SCENECHANGER));
                gameLayout = FXMLLoader.load(getClass().getResource("park.fxml"));
                scene = new Scene(gameLayout, 1920, 1161);
                Stage window = (Stage) ((Node) key.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
        }
    }


    @FXML
    void printHandbook(MouseEvent event) {
        textArea.setText(game.getPlayer().getHandbook());
    }

    @FXML
    void printHelp(MouseEvent event) {
        textArea.setText(game.getHelp());
    }

    @FXML
    void restart(MouseEvent event) {
        game.restart(textArea);
        player.setY(game.getPlayer().getY() * 40);
        player.setX(game.getPlayer().getX() * 40);
    }

    //menu
    @FXML
    void startGame(MouseEvent event) throws IOException {
        gameLayout = FXMLLoader.load(getClass().getResource("game.fxml"));
        scene = new Scene(gameLayout, 1920, 1161);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}

