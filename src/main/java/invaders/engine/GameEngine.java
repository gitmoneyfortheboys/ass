package invaders.engine;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import invaders.GameObject;
import invaders.entities.Player;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class manages the main loop and logic of the game
 */
public class GameEngine {

	private List<GameObject> gameobjects;
	private List<Renderable> renderables;
	private Player player;

	private boolean left;
	private boolean right;

	private int gameWidth;
    private int gameHeight;

	public GameEngine(String config){
    gameobjects = new ArrayList<GameObject>();
    renderables = new ArrayList<Renderable>();

    JSONParser parser = new JSONParser();
    try {
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(config));
        JSONObject game = (JSONObject) jsonObject.get("Game");
        JSONObject size = (JSONObject) game.get("size");
        this.gameWidth = ((Long) size.get("x")).intValue();
        this.gameHeight = ((Long) size.get("y")).intValue();

        // Create game objects here using the parsed values...

        JSONObject player = (JSONObject) jsonObject.get("Player");
        JSONObject playerPosition = (JSONObject) player.get("position");
        this.player = new Player(new Vector2D(((Long) playerPosition.get("x")).intValue(), ((Long) playerPosition.get("y")).intValue()));
		renderables.add(this.player);

        JSONArray bunkers = (JSONArray) jsonObject.get("Bunkers");
        for (Object o : bunkers) {
            JSONObject bunker = (JSONObject) o;
            // Create bunker objects here using the parsed values...
        }

    } catch (IOException | ParseException e) {
        e.printStackTrace();
    }
}

	public int getGameWidth() {
        return this.gameWidth;
    }

    public int getGameHeight() {
        return this.gameHeight;
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
}
