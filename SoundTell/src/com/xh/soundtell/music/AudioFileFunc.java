package com.xh.soundtell.music;
 
import java.io.File;
 
import android.media.MediaRecorder;
import android.os.Environment;
 
public class AudioFileFunc {
    //音频输入-麦克风
    public final static int AUDIO_INPUT = MediaRecorder.AudioSource.MIC;
    public final static int AUDIO_INPUT1 = MediaRecorder.AudioSource.DEFAULT;
    public final static int AUDIO_INPUT2 = MediaRecorder.AudioSource.REMOTE_SUBMIX;
     
    //采用频率
    //44100是目前的标准，但是某些设备仍然支持22050，16000，11025
    public final static int AUDIO_SAMPLE_RATE = 44100;  //44.1KHz,普遍使用的频率   
    //录音输出文件
    private final static String AUDIO_RAW_FILENAME = "RawAudio.raw";
//    private final static String AUDIO_WAV_FILENAME = "FinalAudio.wav";
    private final static String AUDIO_WAV_FILENAME = ".wav";
    public final static String AUDIO_AMR_FILENAME = "FinalAudio.amr";
     
    /**
     * 判断是否有外部存储设备sdcard
     * @return true | false
     */
    public static boolean isSdcardExit(){       
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }
         
    /**
     * 获取麦克风输入的原始音频流文件路径
     * @return
     */
    public static String getRawFilePath(){
        String mAudioRawPath = "";
        if(isSdcardExit()){
            String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            mAudioRawPath = fileBasePath+"/"+AUDIO_RAW_FILENAME;
        }   
        return mAudioRawPath;
    }
    /**
     * 获取编码后的WAV格式音频文件路径
     * @return
     */
    public static String getWavFilePath(String musicFileName){
        String mAudioWavPath = "";
        if(isSdcardExit()){
        	
            String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            File file=new File(fileBasePath+"/音诉音乐Cache/");
            if(!file.exists()){
            	file.mkdirs();
            }
            mAudioWavPath = fileBasePath+"/音诉音乐Cache/"+musicFileName+AUDIO_WAV_FILENAME;//.wav
        }
        return mAudioWavPath;
    }
    /**
     * 获取编码后的AMR格式音频文件路径
     * @return
     */
    public static String getAMRFilePath(){
        String mAudioAMRPath = "";
        if(isSdcardExit()){
            String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            mAudioAMRPath = fileBasePath+"/"+AUDIO_AMR_FILENAME;
        }
        return mAudioAMRPath;
    }   
    /**
     * 获取文件大小
     * @param path,文件的绝对路径
     * @return
     */
    public static long getFileSize(String path){
        File mFile = new File(path);
        if(!mFile.exists())
            return -1;
        return mFile.length();
    }
 
}