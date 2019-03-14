package com.example.ipc.messenger;


import com.example.ipc.R;
import com.example.ipc.R.layout;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	private final static String TAG = "MessengerService";

	private final static Messenger messenger = new Messenger(new MassengerHandler());
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent i = new Intent(this, MessengerService.class);
		bindService(i, conn, BIND_AUTO_CREATE);
	}
	
	private ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			// TODO Auto-generated method stub
			Messenger mMessenger = new Messenger(arg1);
			Message msg = Message.obtain(null, 0);
			msg.replyTo = messenger;
			Bundle data  = new Bundle();
			data.putString("msg", "this is client");
			
			msg.setData(data);
			try {
				mMessenger.send(msg);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	
	private static class MassengerHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
				case 1:
					Bundle bundle = msg.getData();
					String s = bundle.getString("msg");
					Log.e(TAG, "receive reply from service: " + s);
					break;
				default:
					super.handleMessage(msg);
					break;
				
			}
		}
	}

}
