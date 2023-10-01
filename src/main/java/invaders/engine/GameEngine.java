package invaders.engine;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import invaders.GameObject;
import invaders.entities.Player;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class manages the main loop and logic of the game
 */
public class GameEngine {

	private List<GameObject> gameobjects;
	private List<Renderable> renderables;
	private List<GameObject> gameObjects;
	private Player player;

	private boolean left;
	private boolean right;

	private static GameEngine instance = null;

	public GameEngine(String config){
		// read the config here
		gameobjects = new ArrayList<GameObject>();
		renderables = new ArrayList<Renderable>();

		JSONParser parser = new JSONParser();
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream("config.json");
			JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(is));
			JSONObject playerObject = (JSONObject) jsonObject.get("Player");
			JSONObject positionObject = (JSONObject) playerObject.get("position");
			double x = ((Long) positionObject.get("x")).doubleValue();
			double y = ((Long) positionObject.get("y")).doubleValue();

			player = new Player(new Vector2D(x, y)); // Player's position is set from config.json
			renderables.add(player);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates the game/simulation
	 */
	public void update(){
		movePlayer();
		for(GameObject go: gameobjects){
			go.update();
		}

		// ensure that renderable foreground objects don't go off-screen
		for(Renderable ro: renderables){
			if(!ro.getLayer().equals(Renderable.Layer.FOREGROUND)){
				continue;
			}
			if(ro.getPosition().getX() + ro.getWidth() >= 640) {
				ro.getPosition().setX(639-ro.getWidth());
			}

			if(ro.getPosition().getX() <= 0) {
				ro.getPosition().setX(1);
			}

			if(ro.getPosition().getY() + ro.getHeight() >= 400) {
				ro.getPosition().setY(399-ro.getHeight());
			}

			if(ro.getPosition().getY() <= 0) {
				ro.getPosition().setY(1);
			}
		}
	}

	public static GameEngine getInstance(){
		if(instance == null){
			instance = new GameEngine("/resources/config.json");
		}
		return instance;
	}

	public List<Renderable> getRenderables(){
		return renderables;
	}


	public void leftReleased() {
		this.left = false;
	}

	public void rightReleased(){
		this.right = false;
	}

	public void leftPressed() {
		this.left = true;
	}
	public void rightPressed(){
		this.right = true;
	}

	public boolean shootPressed(){
		player.shoot();
		return true;
	}

	private void movePlayer(){
		if(left){
			player.left();
		}

		if(right){
			player.right();
		}
	}

	public void addGameObject(GameObject gameObject) {
		this.gameobjects.add(gameObject);
		if (gameObject instanceof Renderable) {
        	this.renderables.add((Renderable) gameObject);
    	}
	}
}
