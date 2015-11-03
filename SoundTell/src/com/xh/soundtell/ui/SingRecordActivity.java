package com.xh.soundtell.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xh.soundtell.R;
import com.xh.soundtell.model.MusicRecordModel;
import com.xh.soundtell.music.AudioRecordFunc;
import com.xh.soundtell.music.ErrorCode;
import com.xh.soundtell.music.MusicHelper;
import com.xh.soundtell.util.DensityUtil;

public class SingRecordActivity extends Activity implements OnClickListener{
	ImageView  ivPlay;
	private boolean isPlaying;
	private SeekBar bar;
	TextView tvTimeShow;
	TextView tvTitle;
	TextView tvSinger;
	TextView tvType;
	
	
    private final static int FLAG_WAV = 0;
    private int mState = -1;    //-1:没再录制，0：录制wav，1：录制amr
    private final static int CMD_RECORDING_TIME = 2000;
    private final static int CMD_RECORDFAIL = 2001;
    private final static int CMD_STOP = 2002;
	
    private ArrayList<MusicRecordModel>  musicRecordModels; 
    
    MusicRecordModel model1;
    MusicRecordModel model2;
    MusicRecordModel model3;
    
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
		deleteAllFiles(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/音诉音乐Cache/"));
		musicRecordModels=new ArrayList<MusicRecordModel>();
		initMusicModel();
		musicRecordModels.add(model1);
		musicRecordModels.add(model2);
		musicRecordModels.add(model3);
		
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
	    
	    strBgMusicsStrs = new String[] {"激昂-2-130-F(正常品质)","伤心-1-78-E(正常品质)","摇滚-2-84-G(正常品质)"};
	    tvTimeShow=(TextView) findViewById(R.id.singrecord_time);
	    tvTitle=(TextView) findViewById(R.id.singrecord_title);
	    tvGeCi=(TextView) findViewById(R.id.geci);
	    tvSinger=(TextView) findViewById(R.id.singrecord_singer);
	    tvType=(TextView) findViewById(R.id.singrecord_type);
	    
	    
	 findViewById(R.id.singrecord_qu).setOnClickListener(this);
	 findViewById(R.id.singrecord_ci).setOnClickListener(this);
	 findViewById(R.id.singrecord_report).setOnClickListener(this);
	}
	int mResult = -1;
	int ciPosition,quPosition;
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.singrecord_back:
			onBackPressed();
			break;
		case R.id.singrecord_qu:
			quPosition=quPosition+1;
			quPosition=quPosition%musicRecordModels.size();
			tvType.setText(strBgMusicsStrs[quPosition]);
			if(isPlaying){
	              isPlaying=false;
	              ivPrepare.setImageResource(R.drawable.record_save_ib);
	              handler.sendEmptyMessageDelayed(1, 0);
	              MusicHelper.stopMusic();
	              stop();
				}
			break;
		case R.id.singrecord_ci:
			ciPosition=ciPosition+1;
			ciPosition=ciPosition%musicRecordModels.size();
			tvTitle.setText(musicRecordModels.get(ciPosition).getMusicTitle());
			tvGeCi.setText(musicRecordModels.get(ciPosition).getMusicLyric());
			tvSinger.setText(musicRecordModels.get(ciPosition).getMusicSinger());
			tvType.setText(musicRecordModels.get(ciPosition).getMusicType());
			break;
			
		case R.id.singrecord_prepare://中点录制音乐按钮
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
//					 ivPrepare.setImageResource(R.drawable.record_done_ib);
					 ivPrepare.setImageResource(R.drawable.record_pause_ib);
					 MusicHelper.startMusic(this,musicRecordModels.get(quPosition).getMusicId(),false);
					 
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
				 MusicHelper.startMusic(this,musicRecordModels.get(quPosition).getMusicId(),false);
				 }
			break;
			
		case R.id.singrecord_tone:
			getPopwindow();
			break;
		case R.id.singrecord_report:
			reportDialog();
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
				 isPlaying=false;
	              ivPrepare.setImageResource(R.drawable.record_save_ib);
	              handler.sendEmptyMessageDelayed(1, 0);
	              MusicHelper.stopMusic();
	              stop();
			}else{
				Intent intent=new Intent(this,PersonalOptionActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.popupalter_cancel:
			showDisRecordPopwindow();
			delRecordSong();
			finish();
			break;
		case R.id.popupalter_again:
			showDisRecordPopwindow();
//			MusicHelper.startMusic(this,musicRecordModels.get(position).getMusicId(), false);
//	        record(FLAG_WAV);
			 delRecordSong();
	         isPlaying=true;
			 ivPrepare.setImageResource(R.drawable.record_done_ib);
			 MusicHelper.startMusic(this,musicRecordModels.get(quPosition).getMusicId(),false);
	         record(FLAG_WAV);
	         handler.sendEmptyMessageDelayed(0, 1000);
			break;
		case R.id.popupalter_getup:
			delRecordSong();
			showDisRecordPopwindow();
			break;
		case R.id.popupalter_ok:
			 showDisRecordPopwindow();
			 String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/音诉音乐/";
			 File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/音诉音乐");
			 if(!file.exists())
				 {file.mkdirs();}
			 String fileBasePath1 = Environment.getExternalStorageDirectory().getAbsolutePath()+"/音诉音乐Cache/";
			 String newRecordNamePath=fileBasePath+recordName+".wav";
			 String oldRecordNamePath=fileBasePath1+recordName+".wav";
			 
			copyFile(oldRecordNamePath, newRecordNamePath);
            bar.setMax(210);//初始值在10上面 才能显示拖动的图标
    	    bar.setProgress(10);
    	    tvTimeShow.setText("00:00");
			Intent intent=new Intent (this,RecordSucActivity.class);
			startActivity(intent);
			break;
		}
	}
    /**
     * 开始录音
     * @param mFlag，0：录制wav格式，1：录音amr格式
     */
	
	private String recordName;
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
            
            recordName="录制-"+musicRecordModels.get(quPosition).getMusicTitle()+System.currentTimeMillis();
         
            mResult = mRecord_1.startRecordAndFile(recordName); 
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
	
	@Override
	protected void onDestroy() {
		if(isPlaying){
			  MusicHelper.stopMusic();
	          stop();
	          if(!TextUtils.isEmpty(recordName)){
	        	  new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						deleteAllFiles(new File( Environment.getExternalStorageDirectory().getAbsolutePath()+"/音诉音乐Cache/"+recordName+".wav"));
					}
				}, 1000);
	          if(TextUtils.isEmpty(recordName)){recordName=null;
	          
	          }  
	          }
		}
		super.onDestroy();
	}
	
	
	private void delRecordSong(){
		 if(!TextUtils.isEmpty(recordName)){
         	deleteAllFiles(new File( Environment.getExternalStorageDirectory().getAbsolutePath()+"/音诉音乐Cache/"+recordName+".wav"));
         	recordName=null;
		 }
	}
	private void deleteAllFiles(File root) {
		File files[] = root.listFiles();
		if (files != null)
			for (File f : files) {
				if (f.isDirectory()) { // 判断是否为文件夹
					deleteAllFiles(f);
					try {
						f.delete();
					} catch (Exception e) {
					}
				} else {
					if (f.exists()) { // 判断是否存在
						deleteAllFiles(f);
						try {
							f.delete();
						} catch (Exception e) {
						}
					}
				}
			}
	}
	  private void reportDialog(){
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle("举报类型");
			String [] dataArray = new String[]{"版权","色情","政治","暴力","取消"};
			dialog.setItems(dataArray, new DialogInterface.OnClickListener() {
				/***
				 * 我们这里传递给dialog.setItems方法的参数为数组，这就导致了我们下面的
				 * onclick方法中的which就跟数组下标是一样的，点击hello时返回0；点击baby返回1……
				 */
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					switch (which) {
					case 0:
					case 1:
					case 2:
					case 3:
				Toast.makeText(SingRecordActivity.this, "举报成功", 1).show();;		
						break;
					case 4:
						break;
					}
				}
			}).show();
}
	
	
	
	
	
	
	
	
	
	
	
	
   private  PopupWindow recordPopwindow;
private String geci;
private String geci1;
private String[] strBgMusicsStrs;
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
	private void initMusicModel(){
		model1=new MusicRecordModel(R.raw.exist_for_you, "因你而在", "", "林肯公园里的钟声", getString(R.string.exist_for_you), "激昂-2-130-F(正常品质)");
		model2=new MusicRecordModel(R.raw.li_byebye, "再见再见", "", "奋斗的刚子", getString(R.string.li_byebye), "伤心-1-78-E(正常品质)");
		model3=new MusicRecordModel(R.raw.w_nightdj, "午夜DJ", "", "啊·原来是小佟", getString(R.string.w_nightdj), "摇滚-2-84-G(正常品质)");
	}
	
	/** 
	* 复制单个文件 
	* @param oldPath String 原文件路径 如：c:/fqf.txt 
	* @param newPath String 复制后路径 如：f:/fqf.txt 
	* @return boolean 
	*/ 
	public void copyFile(String oldPath, String newPath) { 
	try { 
	int bytesum = 0; 
	int byteread = 0; 
	File oldfile = new File(oldPath); 
	File newfile = new File(newPath); 
	
	if(!newfile.exists()){
		newfile.createNewFile();
	}
	if (oldfile.exists()) { //文件存在时 
	InputStream inStream = new FileInputStream(oldPath); //读入原文件 
	FileOutputStream fs = new FileOutputStream(newPath); 
	byte[] buffer = new byte[1444]; 
	int length; 
	while ( (byteread = inStream.read(buffer)) != -1) { 
	bytesum += byteread; //字节数 文件大小 
	System.out.println(bytesum); 
	fs.write(buffer, 0, byteread); 
	} 
	inStream.close(); 
	oldfile.delete();
	} 
	} 
	catch (Exception e) { 
	Toast.makeText(this, "保存录音文件出错", 0).show();
	e.printStackTrace(); 

	} 

	} 
	}