/*
 * 
 */
package smartcontroller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import application.Main;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.util.Duration;
import media.Audio;
import media.Media;
import tools.ActionTool;
import tools.InfoTool;

/**
 * The default context menu for song items of application.
 *
 * @author GOXR3PLUS
 */
public class MediaContextMenu extends ContextMenu {

    /**
     * The node based on which the Rename or Star Window will be position
     */
    private Node node;

    /** The media. */
    private Media media;

    /** The controller. */
    private SmartController controller;

    /** The players. */
    Menu playOn = new Menu("Play on", InfoTool.getImageViewFromDocuments("circledPlay24.png"));

    /** The player 0. */
    MenuItem player0 = new MenuItem("xPlayer ~0");

    /** The player 1. */
    MenuItem player1 = new MenuItem("xPlayer ~1");

    /** The player 2. */
    MenuItem player2 = new MenuItem("xPlayer ~2");

    /** The players. */
    Menu stopPlayer = new Menu("Stop Player", InfoTool.getImageViewFromDocuments("Stop Sign-24.png"));

    /** The player 0. */
    MenuItem splayer0 = new MenuItem("xPlayer ~0");

    /** The player 1. */
    MenuItem splayer1 = new MenuItem("xPlayer ~1");

    /** The player 2. */
    MenuItem splayer2 = new MenuItem("xPlayer ~2");

    //Start:--Search on Web
    Menu searchOnWeb = new Menu("Search on Web..", InfoTool.getImageViewFromDocuments("searchWeb24.png"));

    MenuItem soundCloud = new MenuItem("SoundCloud", InfoTool.getImageViewFromDocuments("soundcloud24.png"));
    MenuItem jamendo = new MenuItem("Jamendo", InfoTool.getImageViewFromDocuments("jamendo24.png"));
    MenuItem tunein = new MenuItem("tunein", InfoTool.getImageViewFromDocuments("tunein24.png"));
    MenuItem amazon = new MenuItem("amazon", InfoTool.getImageViewFromDocuments("amazon24.png"));

    MenuItem lastfm = new MenuItem("Last.fm", InfoTool.getImageViewFromDocuments("lastfm24.png"));
    MenuItem librefm = new MenuItem("Libre.fm", InfoTool.getImageViewFromDocuments("librefm24.png"));

    MenuItem youtube = new MenuItem("Youtube", InfoTool.getImageViewFromDocuments("youtube24.png"));
    MenuItem vimeo = new MenuItem("Vimeo", InfoTool.getImageViewFromDocuments("vimeo24.png"));

    MenuItem google = new MenuItem("Google", InfoTool.getImageViewFromDocuments("google24.png"));
    MenuItem duckduckgo = new MenuItem("DuckDuckgo", InfoTool.getImageViewFromDocuments("duckduckgo24.png"));
    MenuItem bing = new MenuItem("Bing", InfoTool.getImageViewFromDocuments("bing24.png"));
    MenuItem yahoo = new MenuItem("Yahoo", InfoTool.getImageViewFromDocuments("yahoo24.png"));

    //END:--Search on Web

    /** The add on. */
    Menu addOn = new Menu("Add on");

    /** The x player 0. */
    MenuItem xPlayer0 = new MenuItem("xPlayer ~0 PlayList");

    /** The x player 1. */
    MenuItem xPlayer1 = new MenuItem("xPlayer ~1 PlayList");

    /** The x player 2. */
    MenuItem xPlayer2 = new MenuItem("xPlayer ~2 PlayList");

    /** The more. */
    //Menu more = new Menu("More...", InfoTool.getImageViewFromDocuments("more.png"))

    //--------------------------------

    /** The information. */
    //MenuItem information = new MenuItem("Information (I)", InfoTool.getImageViewFromDocuments("tag.png"))

    /** The stars. (S) */
    MenuItem stars = new MenuItem("Stars ", InfoTool.getImageViewFromDocuments("smallStar.png"));

    /** Show File (P) */
    MenuItem showFile = new MenuItem("Show File ", InfoTool.getImageViewFromDocuments("path.png"));

    /** Find Lyrics (L) */
    MenuItem findLyrics = new MenuItem("Find Lyrics[Comming..]", InfoTool.getImageViewFromDocuments("Puzzle-20.png"));

    /** Show Info (I) */
    MenuItem showInfo = new MenuItem("Show Info[Comming..]", InfoTool.getImageViewFromDocuments("tag.png"));

    //--------------------------------

    /** The copy. (C/M) */
    MenuItem copy = new MenuItem("copy/move ", InfoTool.getImageViewFromDocuments("copyFile.png"));

    /** The move. */
    //MenuItem move = new MenuItem("moveTo(M)")

    /** The rename. (R) */
    MenuItem rename = new MenuItem("Rename ", InfoTool.getImageViewFromDocuments("rename.png"));

    /** The simple delete. */
    MenuItem simpleDelete = new MenuItem("Delete (Delete)", InfoTool.getImageViewFromDocuments("delete2.png"));

    /** The storage delete. */
    MenuItem storageDelete = new MenuItem("Delete (Shift+Delete)", InfoTool.getImageViewFromDocuments("delete.png"));

    /** The separator 1. */
    SeparatorMenuItem separator1 = new SeparatorMenuItem();

    /** The separator 2. */
    SeparatorMenuItem separator2 = new SeparatorMenuItem();

    /** The previous genre. */
    Genre previousGenre = Genre.UNKNOWN;

    /**
     * Constructor.
     */
    public MediaContextMenu() {

	//Add all the items
	findLyrics.setDisable(true);
	showInfo.setDisable(true);
	getItems().addAll(new TitleMenuItem("Common"), playOn, stopPlayer, new TitleMenuItem("More"), searchOnWeb, stars, showFile, findLyrics,
		showInfo, new TitleMenuItem("File Edit"), rename, simpleDelete, storageDelete, new TitleMenuItem("Organize"), copy);

	//---play

	playOn.getItems().addAll(player0, player1, player2);
	playOn.getItems().forEach(item -> item.setOnAction(this::onAction));

	//--stop
	stopPlayer.getItems().addAll(splayer0, splayer1, splayer2);
	stopPlayer.getItems().forEach(item -> item.setOnAction(this::onAction));

	//---searchOnWeb
	getItems().addAll();

	//Start:--Search on Web
	searchOnWeb.getItems().addAll(new TitleMenuItem("Popular"), soundCloud, jamendo, tunein, new TitleMenuItem("Shop"), amazon,
		new TitleMenuItem("Radios"), librefm, lastfm, new TitleMenuItem("Video Sites"), youtube, vimeo, new TitleMenuItem("Search Engines"),
		google, duckduckgo, bing, yahoo);
	searchOnWeb.getItems().forEach(item -> item.setOnAction(this::onAction2));

	//END:--Search on Web

	// add on deck play list 0,1,2
	addOn.setDisable(true);
	addOn.getItems().addAll(xPlayer0, xPlayer1, xPlayer2);
	addOn.getItems().forEach(item -> item.setOnAction(this::onAction));

	// More
	//more.getItems().addAll(stars, showFile)
	//more.getItems().forEach(item -> item.setOnAction(this::onAction))
	stars.setOnAction(this::onAction);
	showFile.setOnAction(this::onAction);

	copy.setOnAction(this::onAction);
	//move.setOnAction(this::onAction)
	simpleDelete.setOnAction(this::onAction);
	storageDelete.setOnAction(this::onAction);
	rename.setOnAction(this::onAction);

    }

    /**
     * Shows the context menu based on the variables below.
     *
     * @param media1
     *            the media
     * @param genre
     *            the genre
     * @param x
     *            the d
     * @param y
     *            the e
     * @param controller1
     *            the controller
     */
    public void showContextMenu(Media media1, Genre genre, double x, double y, SmartController controller1, Node node) {

	// Don't waste resources
	if (previousGenre != genre) {
	    if (media1.getGenre() == Genre.LIBRARYSONG) {
		addOn.setVisible(true);
		stars.setVisible(true);
		copy.setVisible(true);
		//move.setVisible(true)
		rename.setVisible(true);
		simpleDelete.setVisible(true);
		storageDelete.setVisible(true);
		separator1.setVisible(true);
		separator2.setVisible(true);
		//		 } else if (button instanceof TopCategorySong) {
		//		 addOn.setVisible(false);
		//		 stars.setVisible(false);
		//		 copy.setVisible(false);
		//		 move.setVisible(false);
		//		 rename.setVisible(false);
		//		 simpleDelete.setVisible(false);
		//		 storageDelete.setVisible(false);
		//		 separator1.setVisible(false);
		//		 separator2.setVisible(false);
	    } else if (media1.getGenre() == Genre.XPLAYLISTSONG) {
		addOn.setVisible(false);
		stars.setVisible(false);
		copy.setVisible(false);
		//move.setVisible(false)
		rename.setVisible(false);
		simpleDelete.setVisible(true);
		storageDelete.setVisible(true);
		separator1.setVisible(false);
		separator2.setVisible(false);
	    }
	}

	this.node = node;
	this.media = media1;
	this.controller = controller1;

	// Show it
	show(Main.window, x - 5 - super.getWidth() + super.getWidth() * 14 / 100, y - 1);
	previousGenre = genre;

	//Y axis
	double yIni = y - 50;
	double yEnd = super.getY();
	super.setY(yIni);
	final DoubleProperty yProperty = new SimpleDoubleProperty(yIni);
	yProperty.addListener((ob, n, n1) -> super.setY(n1.doubleValue()));

	//X axis
	//	double xIni = screenX - super.getWidth() + super.getWidth() * 14 / 100 + 30;
	//	double xEnd = screenX - super.getWidth() + super.getWidth() * 14 / 100;
	//	super.setX(xIni);
	//	final DoubleProperty xProperty = new SimpleDoubleProperty(xIni);
	//	xProperty.addListener((ob, n, n1) -> super.setY(n1.doubleValue()));

	//Timeline
	Timeline timeIn = new Timeline();
	timeIn.getKeyFrames().addAll(new KeyFrame(Duration.seconds(0.35), new KeyValue(yProperty, yEnd, Interpolator.EASE_BOTH)));
	//new KeyFrame(Duration.seconds(0.5), new KeyValue(xProperty, xEnd, Interpolator.EASE_BOTH)))
	timeIn.play();

    }

    /**
     * Shows a popOver with informations for this Song.
     *
     * @param x
     *            the x
     * @param y
     *            the y
     */
    public void showPopOver(double x, double y) {
	// this.media = media
	// pop.show(media)
    }

    /**
     * On action.
     * 
     * @param e
     *            the a [[SuppressWarningsSpartan]]
     */
    public void onAction(ActionEvent e) {

	// --------------------play on deck 0
	if (e.getSource() == player0) {
	    ((Audio) media).playOnDeck(0, controller);

	    // play on deck 1
	} else if (e.getSource() == player1) {
	    ((Audio) media).playOnDeck(1, controller);

	    // play on deck 2
	} else if (e.getSource() == player2) {

	    ((Audio) media).playOnDeck(2, controller);

	    // ------------------stop deck 0
	} else if (e.getSource() == splayer0) {
	    Main.xPlayersList.getXPlayer(0).stop();

	    // stop deck 1
	} else if (e.getSource() == splayer1) {
	    Main.xPlayersList.getXPlayer(1).stop();

	    // stop deck 2
	} else if (e.getSource() == splayer2) {
	    Main.xPlayersList.getXPlayer(2).stop();
	}

	// add on xPlayList 0
	// } else if (a.getSource() == xPlayer0)
	// Main.xPlayersList.getXPlayerUI(0).xPlayList.addItem(media.getSongPath(),
	// true, true);
	//
	// // add on xPlayList 1
	// else if (a.getSource() == xPlayer1)
	// Main.xPlayersList.getXPlayerUI(1).xPlayList.addItem(media.getSongPath(),
	// true, true);
	//
	// // add on xPlayList 2
	// else if (a.getSource() == xPlayer2)
	// Main.xPlayersList.getXPlayerUI(2).xPlayList.addItem(media.getSongPath(),
	// true, true);

	// delete from list
	else if (e.getSource() == simpleDelete)
	    media.prepareDelete(false, controller);
	// delete from Storage medium
	else if (e.getSource() == storageDelete)

	    media.prepareDelete(true, controller);

	// rename
	else if (e.getSource() == rename) {
	    media.rename(controller, node);
	    //else if (e.getSource() == information) { // information
	    // showPopOver(media);
	} else if (e.getSource() == stars)
	    media.updateStars(controller, node);
	else if (e.getSource() == showFile) // File path
	    ActionTool.openFileLocation(media.getFilePath());
	else if (e.getSource() == copy) // copyTo
	    Main.exportWindow.show(controller);

    }

    /**
     * It is used for action events
     * 
     * @param action
     */
    public void onAction2(ActionEvent action) {
	Object source = action.getSource();
	String encoding = "UTF-8";

	//media!=null [warning]
	if (media != null) {
	    try {

		//Music Sites
		if (source == soundCloud)
		    ActionTool.openWebSite("https://soundcloud.com/search?q=" + URLEncoder.encode(media.getTitle(), encoding));
		else if (source == jamendo)
		    ActionTool.openWebSite("https://www.jamendo.com/search?q=" + URLEncoder.encode(media.getTitle(), encoding));
		else if (source == tunein)
		    ActionTool.openWebSite("http://tunein.com/search/?query=" + URLEncoder.encode(media.getTitle(), encoding));
		else if (source == amazon)
		    ActionTool.openWebSite("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dpopular&field-keywords="
			    + URLEncoder.encode(media.getTitle(), encoding));

		else if (source == lastfm)
		    ActionTool.openWebSite("https://www.last.fm/search?q=" + URLEncoder.encode(media.getTitle(), encoding));
		else if (source == librefm)
		    ActionTool.openWebSite(
			    "https://libre.fm/search.php?search_term=" + URLEncoder.encode(media.getTitle(), encoding) + "&search_type=artist");

		//Video WebSites
		else if (source == youtube)
		    ActionTool.openWebSite("https://www.youtube.com/results?search_query=" + URLEncoder.encode(media.getTitle(), encoding));
		else if (source == vimeo)
		    ActionTool.openWebSite("https://vimeo.com/search?q=" + URLEncoder.encode(media.getTitle(), encoding));

		//Search-Engines
		else if (source == google)
		    ActionTool.openWebSite("https://www.google.com/search?q=" + URLEncoder.encode(media.getTitle(), encoding));
		else if (source == duckduckgo)
		    ActionTool.openWebSite("https://duckduckgo.com/?q=" + URLEncoder.encode(media.getTitle(), encoding));
		else if (source == bing)
		    ActionTool.openWebSite("http://www.bing.com/search?q=" + URLEncoder.encode(media.getTitle(), encoding));
		else if (source == yahoo)
		    ActionTool.openWebSite("https://search.yahoo.com/search?p=" + URLEncoder.encode(media.getTitle(), encoding));

	    } catch (UnsupportedEncodingException ex) {
		ex.printStackTrace();
	    }
	}

    }

}
