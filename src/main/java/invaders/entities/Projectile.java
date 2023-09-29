package invaders.entities;

import java.io.File;

import invaders.GameObject;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import javafx.scene.image.Image;

public class Projectile implements GameObject, Renderable {
    private Vector2D position;
    private double speed;
    private double damage;
    private double width;
    private double height;
    private Image image;

    public Projectile(Vector2D position, double speed, double damage, double width, double height) {
        this.position = position;
        this.speed = speed;
        this.damage = damage;
        this.width = width;
        this.height = height;
        this.image = new Image(new File("src/main/resources/projectile.png").toURI().toString(), width, height, true, true);
    }

    @Override
    public void start() {
        // Implement start logic here...
    }

    @Override
    public void update() {
        this.position.setY(this.position.getY() - this.speed);
    }

    public boolean collidesWith(Renderable renderable) {
        double dx = Math.abs(this.position.getX() - renderable.getPosition().getX());
        double dy = Math.abs(this.position.getY() - renderable.getPosition().getY());
        return dx < (this.width + renderable.getWidth()) / 2 && dy < (this.height + renderable.getHeight()) / 2;
    }

    public double getDamage() {
        return this.damage;
    }

    @Override
    public Image getImage() {
        return this.image; // return the image of the projectile
    }

    @Override
    public double getWidth() {
        return this.width; // return the width of the projectile
    }

    @Override
    public double getHeight() {
        return this.height; // return the height of the projectile
    }

    @Override
    public Vector2D getPosition() {
        return this.position; // return the position of the projectile
    }

    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND; // return the layer of the projectile
    }
}
