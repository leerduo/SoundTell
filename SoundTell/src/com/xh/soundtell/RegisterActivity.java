package com.xh.soundtell;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener {

	EditText name, pwd, pwdagain, phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		name = (EditText) findViewById(R.id.register_name);
		pwd = (EditText) findViewById(R.id.register_pwd);
		pwdagain = (EditText) findViewById(R.id.register_pwdagain);
		phone = (EditText) findViewById(R.id.register_phone);
		findViewById(R.id.register_suc).setOnClickListener(this);
		findViewById(R.id.register_back).setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register_suc:
			if(TextUtils.isEmpty(name.getText().toString())||TextUtils.isEmpty(pwd.getText().toString())||TextUtils.isEmpty(pwdagain.getText().toString())||TextUtils.isEmpty(phone.getText().toString())){
				Toast.makeText(this, "请将信息填写完整", 0).show();
				 break;}
			if(!isMobileNO(phone.getText().toString())){
				Toast.makeText(this, "请输入正确的电话号码", 0).show();
		       break;
			}
			if(!pwd.getText().toString().equals(pwdagain.getText().toString())){
				Toast.makeText(this, "密码输入不一致", 0).show();
				break;
			}
			SharedPreferences preferences=getSharedPreferences("user",Activity.MODE_PRIVATE );
			Editor edit = preferences.edit();
			edit.clear();
			edit.commit();
			edit.putString("phone", phone.getText().toString());			
            edit.putString("name", name.getText().toString());			
            edit.putString("pwd", pwd.getText().toString());			
			edit.commit();
			Toast.makeText(this, "注册成功", 0).show();
			finish();
			break;
		case R.id.register_back:
			onBackPressed();
			break;
		}
	}
	/**************************** 判断是否是正确的电话号码 *************************/
	/**
	 * @param mobiles
	 *            电话号码字符串
	 * @return ture为电话号码 反之不是
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

}
