package invaders.entities;

import java.io.File;

import invaders.logic.Damagable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import javafx.scene.image.Image;

public class Bunker implements Damagable, Renderable {
    private Vector2D position;
    private String color;
    private double health;
    private double width;
    private double height;
    private Image image;
    private int hitCount = 0;
    private boolean isDestroyed = false;

    public Bunker(Vector2D position, String color, double health, double width, double height) {
        this.position = position;
        this.color = color;
        this.health = health;
        this.width = width;
        this.height = height;
        this.image = new Image(new File("src/main/resources/bunker.png").toURI().toString(), width, height, true, true);
    }

    @Override
    public void takeDamage(double amount) {
        this.health -= amount;
        this.hitCount++;

        // Change color based on hit count
        switch (this.hitCount) {
            case 1:
                this.color = "YELLOW";
                break;
            case 2:
                this.color = "RED";
                break;
            case 3:
                this.isDestroyed = true;
                break;
        }
    }

    @Override
    public boolean isAlive() {
        return !this.isDestroyed;
    }

    @Override
    public double getHealth() {
        return this.health;
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
    public Vector2D getPosition() {
        return this.position;
    }

    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND;
    }

    public boolean isDestroyed() {
        return this.isDestroyed;
    }
}
