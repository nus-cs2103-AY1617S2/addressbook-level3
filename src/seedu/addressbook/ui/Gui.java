package seedu.addressbook.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.addressbook.logic.Logic;
import seedu.addressbook.Main;

import java.io.File;
import java.io.IOException;

/**
 * The GUI of the App
 */
public class Gui {

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    public static final int INITIAL_WINDOW_WIDTH = 800;
    public static final int INITIAL_WINDOW_HEIGHT = 600;
    private final Logic logic;

    private MainWindow mainWindow;
    private String version;

    public Gui(Logic logic, String version) {
        this.logic = logic;
        this.version = version;
    }

    public void start(Stage stage, Stoppable mainApp) throws IOException {
        mainWindow = createMainWindow(stage, mainApp);
        mainWindow.displayWelcomeMessage(version, logic.getStorageFilePath());
    }

    private MainWindow createMainWindow(Stage stage, Stoppable mainApp) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        
        /* Note: When calling getResource(), use '/', instead of File.separator or '\\'
         * More info: http://docs.oracle.com/javase/8/docs/technotes/guides/lang/resources.html#res_name_context
         */
        loader.setLocation(Main.class.getResource("ui/mainwindow.fxml"));
        
        VBox vBox = addMenuToVBox(loader);
        stage.getIcons().add(new Image("seedu/addressbook/ui/icon.png"));
        stage.setTitle(version);
        stage.setScene(new Scene(vBox, INITIAL_WINDOW_WIDTH, INITIAL_WINDOW_HEIGHT));
        stage.show();
        MainWindow mainWindow = loader.getController();
        mainWindow.setLogic(logic);
        mainWindow.setMainApp(mainApp);
        return mainWindow;
    }
    
    /**
     * Add the theme menu which allows switching between dark and bright themes
     * @param loader which is a VBox
     * @return a VBox
     * @throws IOException
     */
    private VBox addMenuToVBox(FXMLLoader loader) throws IOException {
        String darkTheme = Main.class.getResource("ui/DarkTheme.css").toExternalForm();
        String brightTheme = Main.class.getResource("ui/BrightTheme.css").toExternalForm();
        
        VBox vBox = loader.load();
        MenuBar menuBar = (MenuBar) vBox.getChildren().get(0);
        Menu menu = menuBar.getMenus().get(0);
        CheckMenuItem dark = (CheckMenuItem) menu.getItems().get(0);
        CheckMenuItem bright = (CheckMenuItem) menu.getItems().get(1);
        
        dark.setOnAction((event) -> {
            dark.setSelected(true);
            bright.setSelected(false);
            vBox.getStylesheets().remove(brightTheme);
            if(!vBox.getStylesheets().contains(darkTheme)) {
                vBox.getStylesheets().add(darkTheme);
            }
        });
        
        bright.setOnAction((event) -> {
            dark.setSelected(false);
            bright.setSelected(true);
            if(!vBox.getStylesheets().contains(brightTheme)) {
                vBox.getStylesheets().add(brightTheme);
            }
        });
        
        return vBox;
    }

}
