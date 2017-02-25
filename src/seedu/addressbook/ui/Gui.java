package seedu.addressbook.ui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seedu.addressbook.logic.Logic;
import seedu.addressbook.Main;

import java.io.File;
import java.io.IOException;

import javax.swing.KeyStroke;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;

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
    
    private Provider hotkeyManager;

    public Gui(Logic logic, String version) {
        this.logic = logic;
        this.version = version;
    }

    public void start(Stage stage, Stoppable mainApp) throws IOException {
        initializeSystemHotkey(stage);
        mainWindow = createMainWindow(stage, mainApp);
        mainWindow.displayWelcomeMessage(version, logic.getStorageFilePath());
    }
    
    public void stop(){
        /* Unbind hotkey */
        hotkeyManager.reset();
        hotkeyManager.stop();
    }

    private MainWindow createMainWindow(Stage stage, Stoppable mainApp) throws IOException{
        FXMLLoader loader = new FXMLLoader();

        /* Note: When calling getResource(), use '/', instead of File.separator or '\\'
         * More info: http://docs.oracle.com/javase/8/docs/technotes/guides/lang/resources.html#res_name_context
         */
        loader.setLocation(Main.class.getResource("ui/mainwindow.fxml"));

        stage.setTitle(version);
        stage.setScene(new Scene(loader.load(), INITIAL_WINDOW_WIDTH, INITIAL_WINDOW_HEIGHT));
        stage.show();
        MainWindow mainWindow = loader.getController();
        mainWindow.setLogic(logic);
        mainWindow.setMainApp(mainApp);
        return mainWindow;
    }
    
    /**
     * Initialize HotkeyManager and bind show/hide hotkey
     */
    private void initializeSystemHotkey(Stage stage) {
        hotkeyManager = Provider.getCurrentProvider(false);
        hotkeyManager.register(KeyStroke.getKeyStroke("control alt D"), new HotKeyListener() {
            public void onHotKey(HotKey hotKey) {
                System.out.println(hotKey);
                Platform.runLater(()-> {stage.setIconified(true);});
            }
        });   
    }
    
}
