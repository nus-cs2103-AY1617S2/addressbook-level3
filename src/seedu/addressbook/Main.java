package seedu.addressbook;

import seedu.addressbook.logic.Logic;
import seedu.addressbook.ui.Gui;
import seedu.addressbook.ui.Stoppable;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main entry point to the application.
 */
public class Main extends Application implements Stoppable{

    /** Version info of the program. */
    public static final String VERSION = "AddessBook Level 3 - Version 1.0";
    private static final String ADDRESS_BOOK_ICON_PATH = "file:resources/images/address_book_icon.png";

    private Gui gui;

    @Override
    public void start(Stage primaryStage) throws Exception{
        gui = new Gui(new Logic(), VERSION);
        primaryStage.getIcons().add(new Image(ADDRESS_BOOK_ICON_PATH));
        gui.start(primaryStage, this);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Platform.exit();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}


