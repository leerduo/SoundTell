package com.xh.soundtell.ios.listener;

import com.xh.soundtell.ios.view.TimeWheelView;
/**
 * Wheel changed listener interface.
 * <p>The currentItemChanged() method is called whenever current wheel positions is changed:
 * <li> New Wheel position is set
 * <li> Wheel view is scrolled
 */
public interface OnTimeWheelChangedListener {
	/**
	 * Callback method to be invoked when current item changed
	 * @param wheel the wheel view whose state has changed
	 * @param oldValue the old value of current item
	 * @param newValue the new value of current item
	 */
	void onChanged(TimeWheelView wheel, int oldValue, int newValue);  
}