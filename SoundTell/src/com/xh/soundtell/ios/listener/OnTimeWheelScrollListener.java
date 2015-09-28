package com.xh.soundtell.ios.listener;

import com.xh.soundtell.ios.view.TimeWheelView;

/**
 * Wheel scrolled listener interface.
 */
public interface OnTimeWheelScrollListener {
	/**
	 * Callback method to be invoked when scrolling started.
	 * 
	 * @param wheel
	 *            the wheel view whose state has changed.
	 */
	void onScrollingStarted(TimeWheelView wheel);

	/**
	 * Callback method to be invoked when scrolling ended.
	 * 
	 * @param wheel
	 *            the wheel view whose state has changed.
	 */
	void onScrollingFinished(TimeWheelView wheel);
}
