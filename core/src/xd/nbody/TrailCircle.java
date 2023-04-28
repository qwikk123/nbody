package xd.nbody;

public class TrailCircle {
    double radius;
    double x;
    double y;
    double decay;
    double alpha;

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
