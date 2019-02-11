package com.view_flipper_demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	private ViewFlipper flip;
	private float initialX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		flip = (ViewFlipper) findViewById(R.id.viewFlipper1);

		// Setting IN and OUT animation for view flipper
		flip.setInAnimation(this, R.anim.right_enter);
		flip.setOutAnimation(this, R.anim.left_out);

	}

	// Implementing touch event for view flipper
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			// Getting intitial by event action down
			initialX = event.getX();
			break;

		case MotionEvent.ACTION_UP:

			// On action up the flipper will start and showing next item
			float finalX = event.getX();
			if (initialX > finalX) {

				// Show items are 4
				if (flip.getDisplayedChild() == 4)
					break;

				// Flip show next will show next item
				flip.showNext();
			} else {

				// If flip has no items more then it will display previous item
				if (flip.getDisplayedChild() == 0)
					break;
				flip.showPrevious();
			}
			break;
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.flip_by_interval:

			// This will set the flipper automatic and timing is 2sec between
			// flipping
			flip.setFlipInterval(2000);
			flip.startFlipping();
			break;

		case R.id.flip_on_click:

			// If flipper is already flipping it will stop flipping and show
			// next if not flipping
			if (flip.isFlipping()) {
				flip.stopFlipping();
			}
			flip.showNext();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
