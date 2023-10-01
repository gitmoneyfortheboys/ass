package invaders.entities;

import invaders.GameObject;
import invaders.physics.Vector2D;

public interface Projectile extends GameObject {
    Vector2D getPosition();
    void move();
    // Other common projectile methods...
}