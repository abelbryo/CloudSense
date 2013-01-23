package com.cloudsense.icqa;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A custom ArrayAdapter to display an icon and a text in a single row in a
 * FragmentList.
 */

public class CustomArrayAdapter extends ArrayAdapter<CustomData> {

	private Activity mContext;
	private int mRowId;
	private CustomData[] mData;

	public CustomArrayAdapter(Context context, int rowId, CustomData[] data) {
		super(context, rowId, data);
		mContext = (Activity) context;
		mRowId = rowId;
		mData = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup container) {
		View row = convertView;
		CustomDataHolder holder;
		if (row == null) {
			holder = new CustomDataHolder();
			LayoutInflater inflater = mContext.getLayoutInflater();
			row = inflater.inflate(mRowId, container, false);
			holder.icon = (ImageView) row.findViewById(R.id.image_icon);
			holder.title = (TextView) row.findViewById(R.id.row_text);
			row.setTag(holder);
		} else {
			holder = (CustomDataHolder) row.getTag();
		}

		CustomData data = mData[position];
		holder.icon.setImageResource(data.getIcon());
		holder.title.setText(data.getTitle());

		return row;
	}

	private static class CustomDataHolder {
		ImageView icon;
		TextView title;
	}

}
