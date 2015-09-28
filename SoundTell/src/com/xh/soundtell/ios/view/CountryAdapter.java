package com.xh.soundtell.ios.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.xh.soundtell.R;
import com.xh.soundtell.ios.data.AddressData;

/**
 * Adapter for countries 城市的Adapter
 */
public class CountryAdapter extends AbstractWheelTextAdapter {

	/**
	 * Adapter for countries 城市的Adapter
	 */
	// Countries names
	private String countries[] = AddressData.PROVINCES;

	/**
	 * Constructor
	 */
	public CountryAdapter(Context context) {
		super(context, R.layout.layout_wheel_citytext, NO_RESOURCE);
		setItemTextResource(R.id.wheelcity_country_name);
	}

	@Override
	public View getItem(int index, View cachedView, ViewGroup parent) {
		View view = super.getItem(index, cachedView, parent);
		return view;
	}

	@Override
	public int getItemsCount() {
		return countries.length;
	}

	@Override
	protected CharSequence getItemText(int index) {
		return countries[index];
	}
}
