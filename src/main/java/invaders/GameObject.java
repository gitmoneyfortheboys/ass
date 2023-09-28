package invaders;

import invaders.rendering.Renderable;

// contains basic methods that all GameObjects must implement
public interface GameObject {

    public void start();
    public void update();
    boolean collidesWith(Renderable renderable);
    double getDamage();

}
