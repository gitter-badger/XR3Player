package aaTester;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import tools.InfoTool;

/**
 * This class is used as the SideBar of the application.
 *
 * @author GOXR3PLUS
 */
public class FXMLPrototype extends BorderPane {

    //-----------------------------------------------------

   
    // -------------------------------------------------------------

  
    /** The logger. */
    private final Logger logger = Logger.getLogger(getClass().getName());


    /**
     * Constructor.
     */
    public FXMLPrototype() {

	// ------------------------------------FXMLLOADER ----------------------------------------
	FXMLLoader loader = new FXMLLoader(getClass().getResource(InfoTool.FXMLS + "SideBar.fxml"));
	loader.setController(this);
	loader.setRoot(this);

	try {
	    loader.load();
	} catch (IOException ex) {
	    logger.log(Level.SEVERE, "", ex);
	}

    }

   
    /**
     * Called as soon as .fxml is initialized
     */
    @FXML
    private void initialize() {

	
    }

}
