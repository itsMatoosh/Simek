package me.matoosh.simek.ui;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWVidMode;

/**
 * Represents a single window.
 * @author Mateusz Rebacz
 *
 */
public class Window {
	/**
	 * ID of the window.
	 */
	public long id;
	/**
	 * Video mode of the window.
	 */
	public GLFWVidMode videoMode;
	
	/**
	 * The width of the window.
	 */
	public int width;
	/**
	 * The height of the window.
	 */
	public int height;
	
	/**
	 * Default window constructor.
	 * @param width
	 * @param height
	 * @param title
	 */
	public Window(int width, int height, String title) {
		//Initializing the window.
		System.out.println("Creating window: " + title + "...");
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		this.width = 640;
		this.height = 480;
		id = glfwCreateWindow(width, height, "simek", 0, 0);
		if(id == 0) {
			throw new IllegalStateException("Couldn't create the window: " + title);
		}	
		videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		
		//Centering the window.
		center();
		
		//Showing the window.
		glfwShowWindow(id);
		
		//Initializing the window main loop.
		loop();
	}
	/**
	 * The window loop.
	 */
	private void loop() {
		while(!glfwWindowShouldClose(id)) {
			glfwPollEvents();
		}
	}
	/**
	 * Centers the window on screen.
	 */
	public void center() {
		glfwSetWindowPos(id, (videoMode.width() - width)/2, (videoMode.height() - height)/2);
	}
}
