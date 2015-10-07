package com.xh.soundtell.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HotFragment extends Fragment {
	
	Activity act;
	public HotFragment(){}
	
	 @Override
	public void onAttach(Activity activity) {
     	act=activity;
		super.onAttach(activity);
	}
  @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	return super.onCreateView(inflater, container, savedInstanceState);
}
}
