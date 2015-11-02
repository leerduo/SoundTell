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
import com.xh.soundtell.RegisterActivity;
import com.xh.soundtell.model.UserInfo;
import com.xh.soundtell.setting.SettingHelper;
import com.xh.soundtell.util.ToastUtil;
import com.xh.soundtell.util.UserData;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 文件名称：LoginActivity.java
 */
public class LoginActivity extends Activity implements OnClickListener {

	EditText account, pwd;

	private UserData mUserData;
	private Cursor mCursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		mUserData = new UserData(this);
		mCursor = mUserData.select();

		account = (EditText) findViewById(R.id.login_account);
		pwd = (EditText) findViewById(R.id.login_pwd);

		findViewById(R.id.login_bt).setOnClickListener(this);
		findViewById(R.id.singrecord_back).setOnClickListener(this);
		findViewById(R.id.loginarray_return).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.singrecord_back:
			LoginActivity.this.finish();
			break;
		case R.id.loginarray_return:
			Intent intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			LoginActivity.this.finish();
			break;
		case R.id.login_bt:
			if (TextUtils.isEmpty(account.getText().toString())
					|| TextUtils.isEmpty(pwd.getText().toString())) {
				Toast.makeText(this, "请填写完整", 0).show();
				return;
			}

			if (mCursor.getCount() == 0) {
				ToastUtil.makeToast(this, "账号或密码错误");
				return;
			}

			for (int i = 0; i < mCursor.getCount(); i++) {
				mCursor.moveToPosition(i);
				String name = mCursor.getString(1);
				String nickname = mCursor.getString(2);
				String pwds = mCursor.getString(3);
				System.out.println("name" + name);
				if (account.getText().toString().equals(name)
						&& pwd.getText().toString().equals(pwds)) {
					ToastUtil.makeToast(this, "登陆成功");
					UserInfo userInfo = new UserInfo(""
							+ (mCursor.getCount() + 1), name, nickname, pwds);
					SettingHelper.getInstance().setUserInfo(userInfo);
					startActivity(new Intent(this, MainActivity.class));
					return;
				} else {
					ToastUtil.makeToast(this, "账号或密码错误");
				}
			}

			// SharedPreferences preferences = getSharedPreferences("user",
			// Activity.MODE_PRIVATE);
			// String sharePhone = preferences.getString("phone", "");
			// if (TextUtils.isEmpty(sharePhone)) {
			// Toast.makeText(this, "当前账号未注册", 0).show();
			// break;
			// }
			// String sharePwd = preferences.getString("pwd", "");
			// if (account.getText().toString().equals(sharePhone)
			// && pwd.getText().toString().equals(sharePwd)) {
			// Toast.makeText(this, "登录成功", 0).show();
			// Intent intent1 = new Intent(this, MainActivity.class);
			// startActivity(intent1);
			// finish();
			// }
			// ToastUtil.makeToast(LoginActivity.this, "账号或密码错误");
			// LoginActivity.this.finish();
			break;

		default:
			break;
		}
	}
}
