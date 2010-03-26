/*
 * Lancaster University
 * Computing Department
 * 
 * Created by Eduardo Figueiredo
 * Date: 22 Jun 2007
 * 
 */
package lancs.mobilemedia.core.ui.controller;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import lancs.mobilemedia.core.ui.MainUIMidlet;
import lancs.mobilemedia.core.ui.datamodel.AlbumData;


/**
 * [EF] Added in scenario 04. 
 * Purpose: (i) to structure controllers and (ii) simplify method handleCommand.
 * @author Eduardo Figueiredo
 *
 */
public abstract class AbstractController implements CommandListener, ControllerInterface {

	protected MainUIMidlet midlet;
	
	//Define a successor to implement the Chain of Responsibility design pattern
	private ControllerInterface nextController;

	private AlbumData albumData;

	//Define the basic screens
	// [NC] Changed in the scenario 07: just the first line below to support generic AbstractController
	private List albumListScreen;

	/**
	 * @param midlet
	 * @param nextController
	 * @param albumData
	 * @param albumListScreen
	 * @param currentScreenName
	 */
	// [NC] Changed in the scenario 07: just the first line below to support generic AbstractController
	public AbstractController(MainUIMidlet midlet, AlbumData albumData, List albumListScreen) {
		this.midlet = midlet;
		this.albumData = albumData;
		this.albumListScreen = albumListScreen;
		// [EF] Senario 04: A singleton ScreenSingleton was created in order to all other access it. 
		// [EF] I think some data need to be unique (e.g. currentScreenName) to make them consistent for all controllers.
	}
	
	/* (non-Javadoc)
	 * @see ubc.midp.MobileMedia.core.ui.controller.ControllerInterface#postCommand(javax.microedition.lcdui.Command, javax.microedition.lcdui.Displayable)
	 */
	public void postCommand(Command command) {
        System.out.println("AbstractController::postCommand - Current controller is: " + this.getClass().getName());
        //If the current controller cannot handle the command, pass it to the next
        //controller in the chain.
        if (handleCommand(command) == false) {
        	ControllerInterface next = getNextController();
            if (next != null) {
                System.out.println("Passing to next controller in chain: " + next.getClass().getName());
                next.postCommand(command);
            } else {
                System.out.println("AbstractController::postCommand - Reached top of chain. No more handlers for command: " + command);
            }
        }

	}

	/* 
	 * Handle events. For now, this just passes control off to a 'wrapper'
	 * so we can ensure, in order to use it in the aspect advice
	 * (non-Javadoc)
	 * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command, javax.microedition.lcdui.Displayable)
	 */
	public void commandAction(Command c, Displayable d) {
		postCommand(c);
	}


    public void setAlbumListAsCurrentScreen(Alert a) {
    	setCurrentScreen(a, albumListScreen);
    }
	
    /**
	 * Set the current screen for display, after alert
	 */
    public void setCurrentScreen(Alert a, Displayable d) {
        Display.getDisplay(midlet).setCurrent(a, d);
    } 

    /**
     * [EF] RENAMED in Scenario 04: remove "Name". Purpose: avoid method name conflict
	 * Get the current screen name that is displayed
	 */
    public Displayable getCurrentScreen() {
        return Display.getDisplay(midlet).getCurrent();
    } 
    
    /**
	 * Set the current screen for display
	 */
    public void setCurrentScreen(Displayable d) {
        Display.getDisplay(midlet).setCurrent(d);
    } 

	/**
	 * @return the albumData
	 */
	public AlbumData getAlbumData() {
		return albumData;
	}

	/**
	 * @param albumData the albumData to set
	 */
	public void setAlbumData(AlbumData albumData) {
		this.albumData = albumData;
	}
	
	/**
	 * @return the nextController
	 */
	public ControllerInterface getNextController() {
		return nextController;
	}

	/**
	 * @param nextController the nextController to set
	 */
	public void setNextController(ControllerInterface nextController) {
		this.nextController = nextController;
	}

	/**
	 * [EF] Scenario 04: Just forward method.
	 * @return the currentStoreName
	 */
	public String getCurrentStoreName() {
		return ScreenSingleton.getInstance().getCurrentStoreName();
	}

	/**
	 * @return the albumListScreen
	 */
	// [NC] Changed in the scenario 07: just the first line below to support generic AbstractController
	public List getAlbumListScreen() {
		return albumListScreen;
	}
}
