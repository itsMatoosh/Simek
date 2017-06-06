package me.matoosh.simek.ui;

import java.util.ArrayList;

/**
 * Manages the app window.
 * @author Mateusz Rebacz
 *
 */
public class WindowManager {
	/**
	 * The list of all the created windows.
	 */
	public static ArrayList<Window> windows = new ArrayList<Window>();
	
	/**
	 * Creates the main app window.
	 * @return
	 */
	public static boolean createMainWindow() {
		try {
			windows.add(new Window(640, 480, "simek"));
			return true;
		} catch (IllegalStateException e) {
			System.out.println("Error while creating the main application window!");
			e.printStackTrace();
			return false;
		}
	}
}
