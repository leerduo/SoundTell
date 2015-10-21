/*************************************************************************************************
 * 版权所有 (C)2015,  成都市商联汇通技术有限公司
 * 
 * 文件名称：ActivityFeedBack.java
 * 内容摘要：升级服务
 * 当前版本： TODO
 * 作        者： 李加蒙
 * 完成日期：2015-10-21 下午2:17:49
 * 修改记录：
 * 修改日期：2015-10-21 下午2:17:49
 * 版   本  号：
 * 修   改  人：
 * 修改内容：
 ************************************************************************************************/

package com.xh.soundtell.ui;

import com.xh.soundtell.R;
import com.xh.soundtell.util.ToastUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 文件名称：ActivityFeedBack.java
 */
public class ActivityFeedBack extends Activity implements OnClickListener {
	private TextView head_centertext;
	private ImageView head_leftimage;

	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		findView();
	}

	private void findView() {
		head_centertext = (TextView) findViewById(R.id.head_centertext);
		head_centertext.setVisibility(View.VISIBLE);
		head_centertext.setText("意见反馈");

		head_leftimage = (ImageView) findViewById(R.id.head_leftimage);
		head_leftimage.setVisibility(View.VISIBLE);
		head_leftimage.setImageResource(R.drawable.back_wihte);
		head_leftimage.setOnClickListener(this);

		button=(Button) findViewById(R.id.feedback_bt);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.head_leftimage:
			ActivityFeedBack.this.finish();
			break;
		case R.id.feedback_bt:
			ToastUtil.makeToast(ActivityFeedBack.this,"上传成功");
			ActivityFeedBack.this.finish();
			break;
		default:
			break;
		}
	}
}
