package xd.nbody;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class Universe extends ApplicationAdapter {
	ShapeRenderer shapeRenderer;
	ArrayList<Body> bodyList;
	static final double G = 1;

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		bodyList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			bodyList.add(new Body(
					((Math.random() * (900 - 100)) + 100)
					,((Math.random() * (900 - 100)) + 100)
					,1.5,0,5,10));
		}
		bodyList.add(new Body(500,500, 0,0,10, 1000));

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.YELLOW);
		bodyGravity();
		bodyMove();
		for (Body b : bodyList) {
			shapeRenderer.circle((float)b.x, (float) b.y, (float)b.radius);
		}
		shapeRenderer.end();
	}

	public void bodyGravity() {
		for (int i = 0; i < bodyList.size(); i++) {
			for (int j = 0; j < bodyList.size(); j++) {
				if (i == j) continue;
				bodyList.get(i).gravity(bodyList.get(j));
			}
		}
	}
	public void bodyMove() {
		for (Body b : bodyList) {
			b.move();
		}
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}
