package xd.nbody;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class Universe extends ApplicationAdapter {
	ShapeRenderer shapeRenderer;
	OrthographicCamera camera;
	ArrayList<Body> bodyList;
	static final double G = 0.2;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 1000);

		shapeRenderer = new ShapeRenderer();
		bodyList = new ArrayList<>();
		bodyList.add(new Body(500,500, 0,0,20, 10000));
		for (int i = 0; i < 200; i++) {
			bodyList.add(new Body(
					((Math.random() * (900 - 100)) + 100)
					,((Math.random() * (900 - 100)) + 100)
					,Math.random()*(3 -(-3)) -3,Math.random()*(3 -(-3)) -3,5,10));
		}

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		bodyGravity();
		bodyMove();

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setProjectionMatrix(camera.combined);
		camera.position.set((float) bodyList.get(0).x, (float) bodyList.get(0).y, 0);
		camera.update();

		shapeRenderer.setColor(Color.GRAY);
		createTrail(shapeRenderer);
		shapeRenderer.setColor(Color.YELLOW);
		for (Body b : bodyList) {
			shapeRenderer.circle((float)b.x, (float) b.y, (float)b.radius);
		}
		shapeRenderer.end();
	}

	public void bodyGravity() {
		for (int i = 0; i < bodyList.size()-1; i++) {
			for (int j = i+1; j < bodyList.size(); j++) {
				bodyList.get(i).gravity(bodyList.get(j));
				bodyList.get(j).gravity(bodyList.get(i));

			}
		}
	}

	public void bodyMove() {
		for (Body b : bodyList) {
			b.move();
		}
	}
	public void createTrail(ShapeRenderer shapeRenderer) {
		for (Body b : bodyList) {
			for (TrailCircle tc : b.getTrail()) {
				shapeRenderer.circle((float) tc.x,(float) tc.y,(float) tc.radius);
				tc.decay();
			}
		}
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}
