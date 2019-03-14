package com.example.ipc.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerService extends Service{

	private final static String TAG = "MessengerService";
	private final static Messenger mMessenger = new Messenger(new MassengerHandler());
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return mMessenger.getBinder();
	}

	private static class MassengerHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
				case 0:
					Bundle bundle = msg.getData();
					String s = bundle.getString("msg");
					Log.e(TAG, "receive message from client: " + s);
					
					Messenger mess = msg.replyTo;
					Message ms = Message.obtain(null, 1);
					Bundle data  = new Bundle();
					data.putString("msg", "嗯， 您发的消息我已收到，稍后回复您!");
					ms.setData(data);
				try {
					mess.send(ms);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
				default:
					super.handleMessage(msg);
					break;
				
			}
		}
	}

}
