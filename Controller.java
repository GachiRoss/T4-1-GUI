import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.*;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class Controller {

    @FXML
    private Button button;

    @FXML
    void doThing(ActionEvent event) {
        System.out.println("HELLO WORLD");
    }

    @FXML
    void printKey(KeyEvent key) {
        Game.getPlayer().keyPressed(key);
        button.setLayoutX(Game.getPlayer().getX() * 10);
        button.setLayoutY(Game.getPlayer().getY() * 10);
    }
}

