/* *******************************************
 * Copyright (c) 2011
 * HT srl,   All rights reserved.
 * Project      : RCS, RCSAndroid
 * File         : RCSAndroidGUI.java
 * Created      : Apr 9, 2011
 * Author		: zeno
 * *******************************************/

package com.android.gui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;

import com.android.gui.R;

// TODO: Auto-generated Javadoc
/**
 * The Class RCSAndroidGUI.
 */
public class RCSAndroidGUI extends Activity {

	protected static final String TAG = "RCSAndroidGUI";

	/**
	 * Called when the activity is first created.
	 * 
	 * @param savedInstanceState
	 *            the saved instance state
	 */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final String service = "com.android.service.app";
		// Set up click listeners
		final Button runButton = (Button) findViewById(R.id.btntoggle);
		runButton.setOnClickListener(new OnClickListener() {
			// @Override
			public void onClick(final View v) {
				if (((ToggleButton) v).isChecked()) {
					try {
						final ComponentName cn = startService(new Intent(service));

						if (cn == null) {
							Log.d("QZ", TAG + " RCS Service not started : " + cn.flattenToShortString());
						} else {
							Log.d("QZ", TAG + " RCS Service Name: " + cn.flattenToShortString());
						}
					} catch (final SecurityException se) {
						Log.d("QZ", TAG + " SecurityException caught on startService()");
					}
				} else {
					try {
						if (stopService(new Intent(service)) == true) {
							Log.d("QZ", TAG + " RCS Service " + service + " stopped");
						} else {
							Log.d("QZ", TAG + " RCS Service " + service + " doesn't exist");
						}
					} catch (final SecurityException se) {
						Log.d("QZ", TAG + " SecurityException caught on stopService()");
					}
				}
			}
		});
	}

}
