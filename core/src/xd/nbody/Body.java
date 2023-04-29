package xd.nbody;

import java.util.ArrayList;

public class Body {
    static ArrayList<Body> removeList = new ArrayList<>();
    double x;
    double y;
    double vX;
    double vY;
    double aX;
    double aY;
    double mass;
    ArrayList<TrailCircle> trailList;

    public Body(double x, double y, double vX, double vY, double mass) {
        this.x = x;
        this.y = y;
        this.vX = vX;
        this.vY = vY;
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
        trailList.add(new TrailCircle(getRadius()/2,x,y,0.02,1));
    }

    public void gravity(Body towards) {
        double dx = Math.abs(x-towards.x);
        double dy = Math.abs(y-towards.y);

        if (dx < getRadius() && dy < getRadius()) {
            if (mass >= towards.mass) {
                mass += towards.mass;
                removeList.add(towards);
                vX += towards.vX*(towards.mass/mass);
                vY += towards.vY*(towards.mass/mass);
            }
            else {
                towards.mass += mass;
                removeList.add(this);
                towards.vX += vX*(mass/towards.mass);
                towards.vY += vY*(mass/towards.mass);
            }
            return;
        }

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

    public double getRadius() {
        return 1+Math.sqrt(mass/2);
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
                ", radius=" + getRadius() +
                ", mass=" + mass +
                '}';
    }
}
