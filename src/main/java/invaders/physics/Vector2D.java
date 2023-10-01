package invaders.physics;

/**
 * A utility class for storing position information
 */
public class Vector2D {

	private double x;
	private double y;

	public Vector2D(double x, double y){
		this.x = x;
		this.y = y;
	}

	public double getX(){
		return this.x;
	}

	public double getY(){
		return this.y;
	}

	public void setX(double x){
		this.x = x;
	}

	public void setY(double y){
		this.y = y;
	}

	public void add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
    }

	public Vector2D multiply(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

	@Override
	public String toString(){
		return "Vector2D{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}
