package com.villevalta.thingspeakclient.ui.toolbar;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;


import com.villevalta.thingspeakclient.R;
import com.villevalta.thingspeakclient.ui.views.RecyclerListView;

/**
 * Created by villevalta on 31.3.2015.
 */
public class HideableToolbar extends Toolbar implements RecyclerListView.ScrollListener {

	private int autoHideMinY;
	private int autoHideSensitivity;
	private int autoHideSignal = 0;
	int lastFvi = 0;
	private final int ITEMS_THRESHOLD = 1;
	private boolean shown = true;

	public HideableToolbar(Context context) {
		super(context);
		init();
	}

	public HideableToolbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public HideableToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init(){
		autoHideMinY = getResources().getDimensionPixelSize(R.dimen.action_bar_auto_hide_min_y);
		autoHideSensitivity = getResources().getDimensionPixelSize(R.dimen.action_bar_auto_hide_sensivity);
	}


	@Override
	public void onContentScrolled(RecyclerView view) {

		int firstVisibleItem = ((LinearLayoutManager)view.getLayoutManager()).findFirstVisibleItemPosition();

		int currentY = firstVisibleItem <= ITEMS_THRESHOLD ? 0 : Integer.MAX_VALUE;
		int deltaY = lastFvi - firstVisibleItem > 0 ? Integer.MIN_VALUE : lastFvi == firstVisibleItem ? 0 : Integer.MAX_VALUE;

		if (deltaY > autoHideSensitivity) {
			deltaY = autoHideSensitivity;
		} else if (deltaY < -autoHideSensitivity) {
			deltaY = -autoHideSensitivity;
		}

		if (Math.signum(deltaY) * Math.signum(autoHideSignal) < 0) {
			// deltaY is a motion opposite to the accumulated signal, so reset signal
			autoHideSignal = deltaY;
		} else {
			// add to accumulated signal
			autoHideSignal += deltaY;
		}

		boolean shouldShow = currentY < autoHideMinY || (autoHideSignal <= -autoHideSensitivity);
		animateHideShow(shouldShow);
		lastFvi = firstVisibleItem;
	}


	public void resetScroll() {
		autoHideSignal = 0;
		lastFvi = 0;
		animateHideShow(true);
	}

	private void animateHideShow(boolean show){
		if(show == shown) return;
		shown = show;
		if (show) {
			animate().translationY(0).alpha(1).setDuration(300).setInterpolator(new DecelerateInterpolator());
		} else {
			animate().translationY(-getBottom()).alpha(0).setDuration(300).setInterpolator(new DecelerateInterpolator());
		}
	}

}
