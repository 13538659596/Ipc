package com.example.ipc.messenger;

import com.example.ipc.aidl.IBookManagerImpl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AidlService extends Service{

	
	private IBookManagerImpl ibook = new IBookManagerImpl();
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return ibook;
	}

}
