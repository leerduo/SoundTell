/*************************************************************************************************
 * 版权所有 (C)2015,  成都市商联汇通技术有限公司
 * 
 * 文件名称：StartGallery.java
 * 内容摘要：升级服务
 * 当前版本： TODO
 * 作        者： 李加蒙
 * 完成日期：2015-10-30 上午10:09:27
 * 修改记录：
 * 修改日期：2015-10-30 上午10:09:27
 * 版   本  号：
 * 修   改  人：
 * 修改内容：
 ************************************************************************************************/

package com.xh.soundtell.view;

import com.xh.soundtell.ui.AboutActivity;
import com.xh.soundtell.ui.AboutActivity1;
import com.xh.soundtell.ui.MainActivity;
import com.xh.soundtell.util.PrefUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Gallery;

/**
 * 文件名称：StartGallery.java
 */
@SuppressWarnings("deprecation")
public class StartGallery extends Gallery {
	private Context context;

	public StartGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	public StartGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setImageActivity(AboutActivity context) {

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		int kEvent;
		if (isScrollingLeft(e1, e2)) {
			kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
		} else {
			kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
		}
		onKeyDown(kEvent, null);

		if (this.getSelectedItemPosition() == (AboutActivity.picture.length - 1)) {// 实现后退功能
			// this.setSelection(AboutActivity.picture.length);
			System.out.println("11111111111111");
			AboutActivity1.pictureGallery.setVisibility(View.GONE);
			context.startActivity(new Intent(context, MainActivity.class));
			// prefUtil.setFirst("1");
			((Activity) context).finish();
			PrefUtil.getInstance().setFirst("1");

		}
		return false;

	}

	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
		return e2.getX() > e1.getX();
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return super.onScroll(e1, e2, distanceX, distanceY);
	}
}
