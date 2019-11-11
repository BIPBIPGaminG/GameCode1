package fr.bipbipgaming.engine.objects;

import java.util.ArrayList;

import fr.bipbipgaming.engine.graphics.Mesh;
import fr.bipbipgaming.engine.math.Vector3f;
import fr.bipbipgaming.main.Main;

public class Block {
	
	public static ArrayList<Mesh> meshArray = new ArrayList<Mesh>();
	public static ArrayList<Vector3f> position = new ArrayList<Vector3f>();
	public static ArrayList<Vector3f> rotation = new ArrayList<Vector3f>();
	public static ArrayList<Vector3f> scale = new ArrayList<Vector3f>();
	
	public static void createObject(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh) {
		
		Block.position.add(position);
		Block.rotation.add(rotation);
		Block.scale.add(scale);
		
		mesh.create();
		
		meshArray.add(mesh);
		
	}
	
	public static void render() {
		
		for (int i = 0; i < meshArray.size(); i++) {
			Main.renderer.renderMesh(new GameObject(position.get(i), rotation.get(i), scale.get(i), meshArray.get(i)));
		}
		
	}
	
	public static void destroy() {
		
		for (int i = 0; i < meshArray.size(); i++) {
			
			meshArray.get(i).destroy();
			
		}
		
	}
}
