package invaders.entities;

import invaders.GameObject;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;

public class Projectile implements GameObject {
    private Vector2D position;
    private double speed;
    private double damage;
    private double width;
    private double height;

    public Projectile(Vector2D position, double speed, double damage, double width, double height) {
        this.position = position;
        this.speed = speed;
        this.damage = damage;
        this.width = width;
        this.height = height;
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
}
