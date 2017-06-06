/**
 * 
 */
package me.matoosh.simek;
import static org.lwjgl.glfw.GLFW.*;

import me.matoosh.simek.ui.WindowManager;

/**
 * Main class
 * @author Mateusz Rebacz
 *
 */
public class Main {

	/**
	 * Called when the program is started.
	 * @param args
	 */
	public static void main(String[] args) {
		if(!glfwInit()) {
			throw new IllegalStateException("Failed to initialize GLFW!");
		}
		
		//Creating a window.
		WindowManager.createMainWindow();
	}

}
