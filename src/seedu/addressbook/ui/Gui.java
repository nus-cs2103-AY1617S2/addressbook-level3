package seedu.addressbook.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
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
	public static final String ICON_FILE_NAME = "Contacts.png";
	private final Logic logic;

	private MainWindow mainWindow;
	private AddWindow addWindow;
	private DeleteWindow deleteWindow;
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
		
		stage.getIcons().add(new Image(getClass().getResourceAsStream(ICON_FILE_NAME)));
		stage.setTitle(version);
		stage.setScene(new Scene(loader.load(), INITIAL_WINDOW_WIDTH, INITIAL_WINDOW_HEIGHT));
		stage.show();
		MainWindow mainWindow = loader.getController();
		mainWindow.setLogic(logic);
		mainWindow.setMainApp(mainApp);
		return mainWindow;
	}

	/**
	 * Opens a dialog to add details for a new contact. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 * 
	 * @param person the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	/*
	private AddWindow createAddWindow(Stage stage) {
		try {
		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(Main.class.getResource("view/PersonEditDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
	
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Add Person");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(stage);
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);

		addWindow = loader.getController();
		addWindow.setDialogStage(dialogStage);

		dialogStage.showAndWait();

		return addWindow;
		} catch (IOException e) {
			e.printStackTrace();
			return addWindow;
		}
	}
	*/	
}
