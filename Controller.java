import Room_related.MapObjekt;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private ImageView trash1;

    @FXML
    private ImageView trash2;

    @FXML
    private ImageView trash3;

    @FXML
    private ImageView trash4;

    @FXML
    private ImageView trash5;

    private ImageView[] trashImages = {trash1, trash2, trash3, trash4, trash5};

    //@FXML
    //private ImageView parkScene = new ImageView("park.jpg");

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
                    if (game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX()][game.getPlayer().getY()] instanceof MapObjekt){
                        game.moveRoom((MapObjekt) game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX()][game.getPlayer().getY()], room , player , trashImages );
                    }
                }
                break;
            case "S":
                if (game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX()][game.getPlayer().getY() + 1] != MapObjekt.NONWALKABLE) {
                    game.getPlayer().setY(game.getPlayer().getY() + 1);
                    player.setY(game.getPlayer().getY() * 40);
                    if (game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX()][game.getPlayer().getY()] instanceof MapObjekt){
                        game.moveRoom((MapObjekt) game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX()][game.getPlayer().getY()], room , player , trashImages );
                    }
                }
                break;
            case "A":
                if (game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX() - 1][game.getPlayer().getY()] != MapObjekt.NONWALKABLE) {
                    game.getPlayer().setX(game.getPlayer().getX() - 1);
                    player.setX(game.getPlayer().getX() * 40);
                    if (game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX()][game.getPlayer().getY()] instanceof MapObjekt){
                        game.moveRoom((MapObjekt) game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX()][game.getPlayer().getY()], room , player , trashImages );
                    }
                }
                break;
            case "D":
                if (game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX() + 1][game.getPlayer().getY()] != MapObjekt.NONWALKABLE) {
                    game.getPlayer().setX(game.getPlayer().getX() + 1);
                    player.setX(game.getPlayer().getX() * 40);
                    if (game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX()][game.getPlayer().getY()] instanceof MapObjekt){
                        game.moveRoom((MapObjekt) game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX()][game.getPlayer().getY()], room , player , trashImages );
                    }
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
                /*
            case "C":
                //skifter til park.jpg //hvorfor bruger vi jpgs? Vil vi gerne have at det ligner billeder fra Sovjetunionen?
                if (game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX() + 1][game.getPlayer().getY()].equals(MapObjekt.NONWALKABLE))
                    ;
                gameLayout = FXMLLoader.load(getClass().getResource("park.fxml"));
                scene = new Scene(gameLayout, 1600, 900);
                Stage window = (Stage) ((Node) key.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
                break;
            /* case "H":
                if (game.getCurrentRoom().getCoordinateSystem()[game.getPlayer().getX() + 1][game.getPlayer().getY()].equals(MapObjekt.NONWALKABLE)) ;
                //har bare kaldt værdien if statementen leder efter for changeObjectID. Kan ikke komme på andet den skal lede efter
                    if (changeObjectID = 1) {
                        ImageView street = new ImageView();
                        street.setImage(new Image("street.jpg"));
                    }
                    if (changeObjectID = 2) {
                        ImageView park = new ImageView();
                        park.setImage(new Image("park.jpg"));
                    }
                    if (changeObjectID = 3) {
                        ImageView recCenter = new ImageView();
                        recCenter.setImage(new Image("recCenter.jpg"));
                    }
                    if (changeObjectID = 4) {
                        ImageView beach = new ImageView();
                        beach.setImage(new Image("beach.jpg"));
                    }
                    if (changeObjectID = 5) {
                        ImageView forest = new ImageView();
                        forest.setImage(new Image("forest.jpg"));
                    }
                    if (changeObjectID = 6) {
                        ImageView home = new ImageView();
                        home.setImage(new Image("home.jpg"));
                    }
                    if (changeObjectID = 7) {
                        ImageView conCenter = new ImageView();
                        conCenter.setImage(new Image("conCenter.jpg"));
                    }*/
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
        game.loadTrash(trashImages);
    }

    //menu
    @FXML
    void startGame(MouseEvent event) throws IOException {
        gameLayout = FXMLLoader.load(getClass().getResource("game.fxml"));
        scene = new Scene(gameLayout, 1600, 800);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        //game.loadTrash(trashImages);
    }
}

