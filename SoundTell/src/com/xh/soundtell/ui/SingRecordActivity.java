package com.xh.soundtell.ui;

import com.xh.soundtell.R;
import com.xh.soundtell.R.layout;
import com.xh.soundtell.util.DensityUtil;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;

public class SingRecordActivity extends Activity implements OnClickListener{

	
	ImageView  ivPlay;
	private boolean isPlaying;
	private SeekBar bar;
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				if(bar.getProgress()<bar.getMax()-1){
					@SuppressWarnings("unused")
					int getProgress=bar.getProgress();
					bar.setProgress(bar.getProgress()+1);
					if(isPlaying)
					handler.sendEmptyMessageDelayed(0, 1000);
				
			}
				break;	
			}
			
			super.handleMessage(msg);
		}
	};
	private PopupWindow popupWindow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sing_record);
		
	    bar = (SeekBar) findViewById(R.id.singrecord_seeker);
	    bar.setMax(4*60+32+10);//初始值在10上面 才能显示拖动的图标
	    bar.setProgress(10);
	    ivPlay=(ImageView) findViewById(R.id.singrecord_play);
	    ivPlay.setOnClickListener(this);
	    findViewById(R.id.singrecord_back).setOnClickListener(this);
	    findViewById(R.id.singrecord_tone).setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.singrecord_back:
			onBackPressed();
			break;
		case R.id.singrecord_play:
			if(isPlaying){
              isPlaying=false;
              ivPlay.setImageResource(R.drawable.record_play);
              handler.sendEmptyMessageDelayed(1, 0);
			}else{
				 isPlaying=true;
				 ivPlay.setImageResource(R.drawable.record_pause);
				 handler.sendEmptyMessageDelayed(0, 1000);
				 }
			break;
		case R.id.singrecord_tone:
			getPopwindow();
			break;
		case R.id.popuprecord_yes:
			showDisPopWindow();
			break;
		case R.id.popuprecord_no:
			showDisPopWindow();
			break;
		}
	}
	private void getPopwindow() {
     	final View	popView = LayoutInflater.from(this).inflate(R.layout.popup_record,
				null);
		View llView1=popView .findViewById(R.id.popuprecord_yes);
		View llView2=popView .findViewById(R.id.popuprecord_no);
		
		llView1.setOnClickListener(this);
		llView2.setOnClickListener(this);
     	
		popupWindow = new PopupWindow(popView,DensityUtil.getScreenWidthAndHeight(this)[0],
     			DensityUtil.getScreenWidthAndHeight(this)[1]);
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow
				.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

		//		popView.setOnTouchListener(new OnTouchListener() {
//			public boolean onTouch(View v, MotionEvent event) {
//				int height = popView.findViewById(R.id.pop_layout).getTop();
//				int y = (int) event.getY();
//				if (event.getAction() == MotionEvent.ACTION_UP) {
//					if (y < height) {
//						popupWindow.dismiss();
//					}
//				}
//				return true;
//			}
//		});
		showDisPopWindow();
	}
	
	public void showDisPopWindow() {
		if (popupWindow != null) {
			if (popupWindow.isShowing()) {
				popupWindow.dismiss();
			} else {
				popupWindow.showAtLocation(getWindow().getDecorView(),
						Gravity.TOP, 0, 0);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	}