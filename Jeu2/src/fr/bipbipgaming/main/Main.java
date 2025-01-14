package fr.bipbipgaming.main;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F11;

import org.lwjgl.glfw.GLFW;

import fr.bipbipgaming.engine.graphics.Renderer;
import fr.bipbipgaming.engine.graphics.Shader;
import fr.bipbipgaming.engine.io.Input;
import fr.bipbipgaming.engine.io.ModelLoader;
import fr.bipbipgaming.engine.io.Window;
import fr.bipbipgaming.engine.math.Vector3f;
import fr.bipbipgaming.engine.objects.Block;
import fr.bipbipgaming.engine.objects.Camera;

public class Main implements Runnable {
	
	public Thread game;
	public static Window window;
	public static Renderer renderer;
	public static Shader shader;
	public static Block block;
	public final static int WIDTH = 1280, HEIGHT = 760;
	
	public Camera camera = new Camera(new Vector3f(0, 0, 1), new Vector3f(0, 0, 0));
	
	public void start() {
		
		game = new Thread(this, "game");
		game.start();
		
		
	}
	
	public void init() {
		
		window = new Window(WIDTH, HEIGHT, "GAME");
		shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
		renderer = new Renderer(window, shader);
		window.create();
		shader.create();
		Block.createObject(new Vector3f(0.0f, 0.0f, 10.0f), new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(1.0f, 1.0f, 1.0f), ModelLoader.loadModel("resources/models/dragon2.obj", "/textures/gray.png"));
		Block.createObject(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(2.0f, 2.0f, 2.0f), ModelLoader.loadModel("resources/models/cube.obj", "/textures/beautiful.png"));
		Block.createObject(new Vector3f(0.0f, 0.0f, -10.0f), new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(2.0f, 2.0f, 2.0f), ModelLoader.loadModel("resources/models/cone.obj", "/textures/beautiful.png"));
		Block.createObject(new Vector3f(10.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(2.0f, 2.0f, 2.0f), ModelLoader.loadModel("resources/models/plane.obj", "/textures/image.png"));
		Block.createObject(new Vector3f(10.0f, 0.0f, 10.0f), new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.25f, 0.25f, 0.25f), ModelLoader.loadModel("resources/models/sphere.obj", "/textures/gray.png"));
		
	}
	
	public void run() {
		
		init();
		while (!window.shouldClose() && !Input.isKeyDown(GLFW_KEY_ESCAPE)) {
			
			update();
			render();
			
			if(Input.isKeyDown(GLFW_KEY_F11)) window.setFullscreen(!window.isFullscreen());
			if (Input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) window.setMouseState(true);
			if (Input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT)) window.setMouseState(false);
			
		}
		
		close();
		
	}
	
	private void update() {
		
		window.update();
		camera.update();
		
	}
	
	private void close() {
		
		window.destroy();
		Block.destroy();
		shader.destroy();
		
	}
	
	private void render() {
		
		renderer.renderCamera(camera);
		Block.render();
		window.swapBuffers();
		
	}
	
	public static void main(String[] args) {
		
		new Main().start();

	}
	
}
