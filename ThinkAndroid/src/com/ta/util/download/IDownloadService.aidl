package com.ta.util.download;

interface IDownloadService
 {	
	void startManage();
	
	void addTask(String url);
	
	void pauseTask(String url);
	
	void pauseAll(String url);
	
	void deleteTask(String url);
	
	void continueTask(String url);
	
	void stopManage();
}
