package com.example.ipc.messenger;


import com.example.ipc.R;
import com.example.ipc.R.layout;
import com.example.ipc.aidl.Book;
import com.example.ipc.aidl.IBookManager;
import com.example.ipc.aidl.IBookManagerImpl;

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

public class MainActivity_1 extends Activity {
	private final static String TAG = "AidlService";
	
	private IBookManager iBook;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent i = new Intent(this, AidlService.class);
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
			iBook =  (IBookManager)IBookManagerImpl.asInterface(arg1);
			try {
				Log.e(TAG, iBook.getBookList().size() + " size  add befor");
				Book book = new Book();
				book.setId(0);
				book.setName("一本好书");
				iBook.addBook(book);
				
				Log.e(TAG, iBook.getBookList().size() + " size  add after");
				Log.e(TAG,  iBook.getBookList().get(0).getName());
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	


}
