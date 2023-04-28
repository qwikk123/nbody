package xd.nbody;

import java.util.ArrayList;

public class Body {
    double x;
    double y;
    double vX;
    double vY;
    double aX;
    double aY;
    double radius;
    double mass;
    ArrayList<TrailCircle> trailList;

    public Body(double x, double y, double vX, double vY, double radius, double mass) {
        this.x = x;
        this.y = y;
        this.vX = vX;
        this.vY = vY;
        this.radius = radius;
        this.mass = mass;
        this.aX = 0;
        this.aY = 0;
        trailList = new ArrayList<>();
    }

    public void move() {
        vX += aX;
        vY += aY;
        x += vX;
        y += vY;
        aX = 0;
        aY = 0;
        trailList.add(new TrailCircle(radius/2,x,y,0.02,1));
    }

    public void gravity(Body towards) {
        double dx = Math.abs(x-towards.x);
        double dy = Math.abs(y-towards.y);

        if (dx < radius || dy < radius) return;

        double r = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
        double a = Universe.G*towards.mass/Math.pow(r,2);
        try {
            double theta = Math.sin(dy/r);
            aY += (y > towards.y) ? (-Math.sin(theta) * a) : (Math.sin(theta) * a);
            aX += (x > towards.x) ? (-Math.cos(theta) * a) : (Math.cos(theta) * a);

        } catch (ArithmeticException e) {/*do nothing*/}
    }

    public ArrayList<TrailCircle> getTrail() {
        trailList.removeIf(i -> i.alpha <= 0);
        return trailList;
    }

    @Override
    public String toString() {
        return "Body{" +
                "x=" + x +
                ", y=" + y +
                ", vX=" + vX +
                ", vY=" + vY +
                ", aX=" + aX +
                ", aY=" + aY +
                ", radius=" + radius +
                ", mass=" + mass +
                '}';
    }
}
