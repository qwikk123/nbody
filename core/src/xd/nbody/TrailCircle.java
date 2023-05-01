package xd.nbody;

public class TrailCircle {
    protected double radius;
    protected double x;
    protected double y;
    protected double decay;
    protected double alpha;

    public TrailCircle(double radius, double x, double y, double decay, double alpha) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.decay = decay;
        this.alpha = alpha;
    }

    public void decay() {
        alpha -= decay;
    }
}
