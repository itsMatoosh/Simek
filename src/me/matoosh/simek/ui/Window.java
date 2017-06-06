package me.matoosh.simek.ui;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.IntBuffer;

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
		while(!glfwWindowShouldClose(id)) {
			glfwPollEvents();
			
			glfwSwapBuffers(id);
		}
	}
	/**
	 * Centers the window on screen.
	 */
	public void center() {
		WindowSize size = getSize();
		glfwSetWindowPos(id, (videoMode.width() - size.width)/2, (videoMode.height() - size.height)/2);
	}
	
	/**
	 * Gets the size of the window.
	 * @return
	 */
	public WindowSize getSize() {
		IntBuffer w = BufferUtils.createIntBuffer(1);
		IntBuffer h = BufferUtils.createIntBuffer(1);
		glfwGetWindowSize(id, w, h);
		
		WindowSize size = new WindowSize(w, h);
		
		return size;
	}
	/**
	 * The size of a window.
	 * @author Mateusz Rebacz
	 *
	 */
	public class WindowSize {
		public int width;
		public int height;
		
		/**
		 * Creates a new window size object based on the IntBuffer contents.
		 * @param width
		 * @param height
		 */
		public WindowSize(IntBuffer width, IntBuffer height) {
			this.width = width.get(0);
			this.height = height.get(0);
		}
	}
}
