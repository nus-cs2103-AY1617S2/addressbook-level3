package seedu.addressbook.commands;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


/**
 * Prints out nyan cat
 */
public class NyanCommand extends Command {

    public static final String COMMAND_WORD = "nyan";
    
    private static final String FILE_DIRECTORY = "src/seedu/addressbook/data/nyancat.mp3";
    
    private static final String NYAN = "_━━___━__*___━_*___┓━━╭¬¬¬¬¬━━╮\n" +
                                       "_━━___━━*____━━___┗┓ |:¬¬¬¬¬¬:::::::|:^--------^\n" + 
                                       "━*━___━━____━━*___━┗ |:¬¬¬¬¬¬:::::::| |  ｡◕‿‿◕ ｡|\n" +
                                       "━_*___━━___*━━___*━━   ╰O━━━━O╯╰--O-O--╯"; 

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Displays a nyan cat for the user!\n\t"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        playBGM();
        return new CommandResult(NYAN);
    }
    
    private void playBGM() {
        Media hit = new Media(new File(FILE_DIRECTORY).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }
}
