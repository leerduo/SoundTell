/*************************************************************************************************
 * 版权所有 (C)2015,  成都市商联汇通技术有限公司
 * 
 * 文件名称：LoginActivity.java
 * 内容摘要：升级服务
 * 当前版本： TODO
 * 作        者： 李加蒙
 * 完成日期：2015-10-22 下午5:20:49
 * 修改记录：
 * 修改日期：2015-10-22 下午5:20:49
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

/**
 * 文件名称：LoginActivity.java
 */
public class LoginActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		findViewById(R.id.login_bt).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ToastUtil.makeToast(LoginActivity.this, "登录成功");
				LoginActivity.this.finish();
			}
		});
		findViewById(R.id.singrecord_back).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						LoginActivity.this.finish();
					}
				});
	}
}
