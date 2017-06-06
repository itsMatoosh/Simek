package me.matoosh.simek.ui;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.IntBuffer;

import org.joml.Vector2i;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

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
	 * Default window constructor.
	 * @param width
	 * @param height
	 * @param title
	 */
	public Window(int width, int height, String title) {
		//Initializing the window.
		System.out.println("Creating window: " + title + "...");
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_DECORATED, GLFW_TRUE);
		glfwWindowHint(GLFW_FOCUSED, GLFW_TRUE);
		
		id = glfwCreateWindow(640, 480, "simek", 0, 0);
		if(id == 0) {
			throw new IllegalStateException("Couldn't create the window: " + title);
		}	
		videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		
		//Centering the window.
		center();
		
		//Showing the window.
		glfwShowWindow(id);
		
		//Creating the opengl context.
		glfwMakeContextCurrent(id);
		GL.createCapabilities();
		
		//Initializing the window main loop.
		loop();
	}
	/**
	 * The window loop.
	 */
	private void loop() {
		//Checking whether the window should close.
		while(!glfwWindowShouldClose(id)) {
			glfwPollEvents();
			
			glfwSwapBuffers(id);
		}
	}
	/**
	 * Sets the title of the window.
	 * @param title
	 */
	public void setTitle(String title) {
		glfwSetWindowTitle(id, title);
	}
	/**
	 * Sets the visibility of the window.
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		if(visible) {
			glfwShowWindow(id);
		} else {
			glfwHideWindow(id);
		}
	}
	/**
	 * Centers the window on screen.
	 */
	public void center() {
		Vector2i size = getSize();
		glfwSetWindowPos(id, (videoMode.width() - size.x)/2, (videoMode.height() - size.y)/2);
	}
	/**
	 * Disposes the window.
	 */
	public void dispose() {
		glfwDestroyWindow(id);
	}
	
	/**
	 * Gets the size of the window.
	 * @return
	 */
	public Vector2i getSize() {
		IntBuffer w = BufferUtils.createIntBuffer(1);
		IntBuffer h = BufferUtils.createIntBuffer(1);
		glfwGetWindowSize(id, w, h);
		
		return new Vector2i(w.get(0), h.get(0));
	}
}
