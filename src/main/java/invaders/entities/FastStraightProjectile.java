package invaders.entities;

import invaders.GameObject;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import javafx.scene.image.Image;

public class FastStraightProjectile implements Projectile, GameObject, Renderable {
    private Vector2D position;
    private Vector2D velocity;
    private Image image;
    private double width;
    private double height;

    public FastStraightProjectile(Vector2D position, Vector2D velocity) {
        this.position = position;
        this.velocity = velocity;
        this.image = new Image(getClass().getResource("/projectile.png").toString());
        this.width = 10;
        this.height = 10;
    }

    @Override
    public Vector2D getPosition() {
        return this.position;
    }

    @Override
    public void move() {
        this.position.add(this.velocity);
    }

    @Override
    public void start() {
        // Add any initialization code needed when the game starts
    }

    @Override
    public void update() {
        // Add any code needed to update the state of the projectile each frame
        this.move(); // For example, you might want to move the projectile each frame
    }

    @Override
    public Image getImage() {
        return this.image;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND; // Replace with the actual layer
    }
}