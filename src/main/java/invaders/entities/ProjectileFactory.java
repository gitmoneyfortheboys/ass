package invaders.entities;

import invaders.physics.Vector2D;

public class ProjectileFactory {
    public Projectile createProjectile(String type, Vector2D position, Vector2D velocity) {
        if (type.equals("fast_straight")) {
            return new FastStraightProjectile(position, velocity);
        } else if (type.equals("slow_straight")) {
            return new SlowStraightProjectile(position, velocity);
        } else {
            throw new IllegalArgumentException("Invalid projectile type: " + type);
        }
    }
}