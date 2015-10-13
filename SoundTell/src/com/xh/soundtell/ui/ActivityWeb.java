/*************************************************************************************************
 * 版权所有 (C)2015,  成都市商联汇通技术有限公司
 * 
 * 文件名称：ActivityWeb.java
 * 内容摘要：升级服务
 * 当前版本： TODO
 * 作        者： 李加蒙
 * 完成日期：2015-10-13 下午3:59:01
 * 修改记录：
 * 修改日期：2015-10-13 下午3:59:01
 * 版   本  号：
 * 修   改  人：
 * 修改内容：
 ************************************************************************************************/

package com.xh.soundtell.ui;

import com.xh.soundtell.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * 文件名称：ActivityWeb.java
 */
public class ActivityWeb extends Activity {
	private ImageView head_leftimage, head_rightimage;
	private TextView head_centertext;

	private ScrollView web_sv1, web_sv2;

	private Intent intent;
	private String id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liangxiao);
		intent = getIntent();
		id = intent.getStringExtra("web");

		findView();
	}

	private void findView() {
		head_centertext = (TextView) findViewById(R.id.head_centertext);
		head_centertext.setVisibility(View.VISIBLE);
		head_centertext.setText("活动");

		head_leftimage = (ImageView) findViewById(R.id.head_leftimage);
		head_leftimage.setImageResource(R.drawable.back_black);
		head_leftimage.setVisibility(View.VISIBLE);
		head_leftimage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityWeb.this.finish();
			}
		});

		head_rightimage = (ImageView) findViewById(R.id.head_rightimage);
		head_rightimage.setImageResource(R.drawable.huodong_share_btn);
		head_rightimage.setVisibility(View.VISIBLE);

		web_sv1 = (ScrollView) findViewById(R.id.web_sv1);
		web_sv2 = (ScrollView) findViewById(R.id.web_sv2);

		if (id.equals("0")) {
			web_sv1.setVisibility(View.VISIBLE);
		} else {
			web_sv2.setVisibility(View.VISIBLE);
		}
	}
}
