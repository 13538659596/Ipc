package com.example.ipc.aidl;

import java.util.List;

public interface IBookManager extends android.os.IInterface{

	static final java.lang.String DESCRIPTOR = "com.wrtsz.intercom.master.IBookManager";
	static final int TRANSACTION_addBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
	static final int TRANSACTION_getBookList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
	void addBook(Book book) throws android.os.RemoteException;
	List<Book> getBookList() throws android.os.RemoteException;
}
