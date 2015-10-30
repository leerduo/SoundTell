package com.xh.soundtell;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xh.soundtell.DataActivity.BooksListAdapter;
import com.xh.soundtell.model.UserInfo;
import com.xh.soundtell.setting.SettingHelper;
import com.xh.soundtell.ui.LoginActivity;
import com.xh.soundtell.ui.MainActivity;
import com.xh.soundtell.util.ToastUtil;
import com.xh.soundtell.util.UserData;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener {

	EditText name, pwd, pwdagain, phone;

	private UserData mUserData;
	private Cursor mCursor;
//	private ListView userlist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		mUserData = new UserData(this);
		mCursor = mUserData.select();

		name = (EditText) findViewById(R.id.register_name);
		pwd = (EditText) findViewById(R.id.register_pwd);
		pwdagain = (EditText) findViewById(R.id.register_pwdagain);
		phone = (EditText) findViewById(R.id.register_phone);
		findViewById(R.id.register_suc).setOnClickListener(this);
		findViewById(R.id.register_back).setOnClickListener(this);

		// userlist = (ListView) findViewById(R.id.userlist);
		// userlist.setAdapter(new BooksListAdapter(this, mCursor));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register_suc:
			if (TextUtils.isEmpty(name.getText().toString())
					|| TextUtils.isEmpty(pwd.getText().toString())
					|| TextUtils.isEmpty(pwdagain.getText().toString())
					|| TextUtils.isEmpty(phone.getText().toString())) {
				Toast.makeText(this, "请将信息填写完整", 0).show();
				break;
			}
			// if (!isMobileNO(phone.getText().toString())) {
			// Toast.makeText(this, "请输入正确的电话号码", 0).show();
			// break;
			// }
			if (!pwd.getText().toString().equals(pwdagain.getText().toString())) {
				Toast.makeText(this, "密码输入不一致", 0).show();
				break;
			}

			for (int i = 0; i < mCursor.getCount(); i++) {
				mCursor.moveToPosition(i);
				String name = mCursor.getString(1);
				System.out.println("name" + name);
				if (phone.getText().toString().equals(name)) {
					ToastUtil.makeToast(this, "该手机号已经注册，请更换手机");
					return;
				}
			}

			// SharedPreferences preferences = getSharedPreferences("user",
			// Activity.MODE_PRIVATE);
			// Editor edit = preferences.edit();
			// edit.clear();
			// edit.commit();
			// edit.putString("phone", phone.getText().toString());
			// edit.putString("name", name.getText().toString());
			// edit.putString("pwd", pwd.getText().toString());
			// edit.commit();
			// Toast.makeText(this, "注册成功", 0).show();

			add(phone.getText().toString(), name.getText().toString(), pwd
					.getText().toString());
			break;
		case R.id.register_back:
			onBackPressed();
			break;
		}
	}

	public void add(String phone, String name, String pwd) {
		mUserData.insert(phone, name, pwd);
		// UserInfo userInfo = new UserInfo("" + (mCursor.getCount() + 1),
		// phone,
		// name, pwd);
		// SettingHelper.getInstance().setUserInfo(userInfo);
		mCursor.requery();
		// userlist.invalidateViews();
		ToastUtil.makeToast(this, "注册成功");
		startActivity(new Intent(this, LoginActivity.class));
		setResult(RESULT_OK);
		finish();
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

	// public class BooksListAdapter extends BaseAdapter {
	// private Context mContext;
	// private Cursor mCursor;
	//
	// public BooksListAdapter(Context context, Cursor cursor) {
	//
	// mContext = context;
	// mCursor = cursor;
	// }
	//
	// @Override
	// public int getCount() {
	// return mCursor.getCount();
	// }
	//
	// @Override
	// public Object getItem(int position) {
	// return null;
	// }
	//
	// @Override
	// public long getItemId(int position) {
	// return 0;
	// }
	//
	// @Override
	// public View getView(int position, View convertView, ViewGroup parent) {
	// TextView mTextView = new TextView(mContext);
	// mCursor.moveToPosition(position);
	// mTextView.setText(mCursor.getString(1) + "___"
	// + mCursor.getString(3));
	// System.out.println(mCursor.getString(1) + "___"
	// + mCursor.getString(3));
	// return mTextView;
	// }
	//
	// }

}
