package xd.nbody;

public class Body {
    double x;
    double y;
    double vX;
    double vY;
    double aX;
    double aY;
    double radius;
    double mass;

    public Body(double x, double y, double vX, double vY, double radius, double mass) {
        this.x = x;
        this.y = y;
        this.vX = vX;
        this.vY = vY;
        this.radius = radius;
        this.mass = mass;
        this.aX = 0;
        this.aY = 0;
    }

    public void move() {
        vX += aX;
        vY += aY;
        x += vX;
        y += vY;
    }

    public void gravity(Body towards) {
        double dx = Math.abs(x-towards.x);
        double dy = Math.abs(y-towards.y);

        double r = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
        double a = Universe.G*towards.mass/Math.pow(r,2);
        try {
            double theta = Math.sin(dy/r);
            aY = (y > towards.y) ? (-Math.sin(theta) * a) : (Math.sin(theta) * a);
            aX = (x > towards.x) ? (-Math.cos(theta) * a) : (Math.cos(theta) * a);

        } catch (ArithmeticException e) {/*do nothing*/}
    }
}
