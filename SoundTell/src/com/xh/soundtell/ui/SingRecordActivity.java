package com.xh.soundtell.ui;

import com.xh.soundtell.R;
import com.xh.soundtell.R.layout;
import com.xh.soundtell.music.AudioRecordFunc;
import com.xh.soundtell.music.ErrorCode;
import com.xh.soundtell.music.MusicHelper;
import com.xh.soundtell.util.DensityUtil;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.TextView;
import android.widget.Toast;

public class SingRecordActivity extends Activity implements OnClickListener{

	
	ImageView  ivPlay;
	private boolean isPlaying;
	private SeekBar bar;
	TextView tvTimeShow;
	TextView tvTitle;
	
    private final static int FLAG_WAV = 0;
    private int mState = -1;    //-1:没再录制，0：录制wav，1：录制amr
    private final static int CMD_RECORDING_TIME = 2000;
    private final static int CMD_RECORDFAIL = 2001;
    private final static int CMD_STOP = 2002;
	
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				if(bar.getProgress()<bar.getMax()-1){
					@SuppressWarnings("unused")
					int getProgress=bar.getProgress();
					bar.setMax( MusicHelper.mediaPlayer.getDuration()+20);
					bar.setProgress(MusicHelper.mediaPlayer.getCurrentPosition()+10);
					tvTimeShow.setText(formatLongToTimeStr(MusicHelper.mediaPlayer.getCurrentPosition()));
					if(isPlaying)
					 handler.sendEmptyMessageDelayed(0, 1000);
			}
				break;	
			}
			super.handleMessage(msg);
		}
	};
	private PopupWindow popupWindow;
	private ImageView ivPrepare;
	private ImageView ivChoose;
	private TextView tvChoose;
	private TextView tvGeCi;
	
	private boolean isRecord=false;
	
	 private int musicTime=-1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sing_record);
		geci = getResources().getString(R.string.geci);
		geci1 = getResources().getString(R.string.geci1);
	    bar = (SeekBar) findViewById(R.id.singrecord_seeker);
	    bar.setMax(4*60+32+10);//初始值在10上面 才能显示拖动的图标
	    bar.setProgress(10);
	    ivPlay=(ImageView) findViewById(R.id.singrecord_play);
	    ivPlay.setOnClickListener(this);
	    findViewById(R.id.singrecord_back).setOnClickListener(this);
	    findViewById(R.id.singrecord_tone).setOnClickListener(this);
	    
	    ivPrepare = (ImageView) findViewById(R.id.singrecord_prepare);
	    ivPrepare.setOnClickListener(this);
	    ivChoose = (ImageView) findViewById(R.id.singrecord_choose);
	    ivChoose.setOnClickListener(this);
	    tvChoose = (TextView) findViewById(R.id.singrecord_choose_tv);

	    tvTimeShow=(TextView) findViewById(R.id.singrecord_time);
	    tvTitle=(TextView) findViewById(R.id.singrecord_title);
	    tvGeCi=(TextView) findViewById(R.id.geci);
	    
	    
	 findViewById(R.id.singrecord_qu).setOnClickListener(this);
	 findViewById(R.id.singrecord_ci).setOnClickListener(this);
	
	}
	
	
	
	
	int mResult = -1;
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.singrecord_back:
			onBackPressed();
			break;
		case R.id.singrecord_qu:
			onBackPressed();
			break;
		case R.id.singrecord_ci:
			if(tvTitle.getText().toString().equals("因你而在")){
				tvTitle.setText("因你而在-林俊杰");
				tvGeCi.setText(geci);
			}else{
				tvTitle.setText("因你而在");
				tvGeCi.setText(geci1);
			}
			 if(isPlaying){
	              isPlaying=false;
	              ivPrepare.setImageResource(R.drawable.record_save_ib);
	              handler.sendEmptyMessageDelayed(1, 0);
	              MusicHelper.stopMusic();
	              stop();
				}
			
			break;
		case R.id.singrecord_prepare:
			ivPlay.setVisibility(View.GONE);
			tvChoose.setVisibility(View.GONE);

			ivPrepare.setImageResource(R.drawable.record_done_ib);
			ivChoose.setImageResource(R.drawable.record_restart);
			if(isPlaying){
	              isPlaying=false;
	              ivPrepare.setImageResource(R.drawable.record_save_ib);
	              handler.sendEmptyMessageDelayed(1, 0);
	              MusicHelper.stopMusic();
	              stop();
				}else{
					 isPlaying=true;
					 ivPrepare.setImageResource(R.drawable.record_done_ib);
					 MusicHelper.startMusic(this, false);
			         record(FLAG_WAV);
			         handler.sendEmptyMessageDelayed(0, 1000);
					 }
			break;
		case R.id.singrecord_play:
			if(isPlaying){
              isPlaying=false;
              ivPlay.setImageResource(R.drawable.record_play);
              handler.sendEmptyMessageDelayed(1, 0);
              MusicHelper.stopMusic();
			}else{
				 isPlaying=true;
				 ivPlay.setImageResource(R.drawable.record_pause);
				 handler.sendEmptyMessageDelayed(0, 1000);
				 MusicHelper.startMusic(this, false);
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
		case R.id.singrecord_choose:
			 if (View.GONE==tvChoose.getVisibility()) {
				 getRecordPopwindow();
			}else{
				Toast.makeText(this, "您点击了素材库", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.popupalter_cancel:
			showDisRecordPopwindow();
			break;
		case R.id.popupalter_again:
			showDisRecordPopwindow();
			break;
		case R.id.popupalter_getup:
			showDisRecordPopwindow();
			break;
		case R.id.popupalter_ok:
			showDisRecordPopwindow();
			break;
		}
	}
    /**
     * 开始录音
     * @param mFlag，0：录制wav格式，1：录音amr格式
     */
    private void record(int mFlag){
        if(mState != -1){
            Message msg = new Message();
            Bundle b = new Bundle();// 存放数据
            b.putInt("cmd",CMD_RECORDFAIL);
            b.putInt("msg", ErrorCode.E_STATE_RECODING);
            msg.setData(b); 
 
            return;
        } 
        int mResult = -1;
        switch(mFlag){        
        case FLAG_WAV:
            AudioRecordFunc mRecord_1 = AudioRecordFunc.getInstance();
            mResult = mRecord_1.startRecordAndFile();            
            break;
        }
        if(mResult == ErrorCode.SUCCESS){
            mState = mFlag;
        }else{
            Message msg = new Message();
            Bundle b = new Bundle();// 存放数据
            b.putInt("cmd",CMD_RECORDFAIL);
            b.putInt("msg", mResult);
            msg.setData(b); 
 
        }
    }
    /**
     * 停止录音
     */
    private void stop(){
        if(mState != -1){
            switch(mState){
            case FLAG_WAV:
                AudioRecordFunc mRecord_1 = AudioRecordFunc.getInstance();
                mRecord_1.stopRecordAndFile();
                break;
            }            
            Message msg = new Message();
            Bundle b = new Bundle();// 存放数据
            b.putInt("cmd",CMD_STOP);
            b.putInt("msg", mState);
            msg.setData(b);
            mState = -1;
        }
    } 
	
	
   private  PopupWindow recordPopwindow;
private String geci;
private String geci1;
    private void getRecordPopwindow() {
    	final View	popView = LayoutInflater.from(this).inflate(R.layout.popup_record_alter,
				null);
    	View cancel=popView .findViewById(R.id.popupalter_cancel);
		View getup=popView .findViewById(R.id.popupalter_getup);
		View again=popView .findViewById(R.id.popupalter_again);
		View ok=popView .findViewById(R.id.popupalter_ok);
    	
		cancel.setOnClickListener(this);
		getup.setOnClickListener(this);
		again.setOnClickListener(this);
		ok.setOnClickListener(this);
		recordPopwindow = new PopupWindow(popView,DensityUtil.getScreenWidthAndHeight(this)[0],
     			DensityUtil.getScreenWidthAndHeight(this)[1]);
		recordPopwindow.setFocusable(true);
		recordPopwindow.setOutsideTouchable(true);
		recordPopwindow.setBackgroundDrawable(new BitmapDrawable());
		recordPopwindow
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
		showDisRecordPopwindow();
    }
    
    public void showDisRecordPopwindow() {
		if (recordPopwindow != null) {
			if (recordPopwindow.isShowing()) {
				recordPopwindow.dismiss();
			} else {
				recordPopwindow.showAtLocation(getWindow().getDecorView(),
						Gravity.TOP, 0, 0);
			}
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
	
	
	
	
	
	  private static String formatLongToTimeStr(int l) {
	        int minute = 0;
	        int second = l/ 1000;;
	        if (second > 60) {
	            minute = second / 60;
	            second = second % 60;
	        }
	        return (getTwoLength(minute)  + ":"  + getTwoLength(second));
	    }
	    private static String getTwoLength(final int data) {
	        if(data < 10) {
	            return "0" + data;
	        } else {
	            return "" + data;
	        }
	    }
	
	
	
	}