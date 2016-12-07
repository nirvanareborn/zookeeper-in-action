package com.bytebeats.zookeeper;

import java.io.IOException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZKCrudDemo extends BaseConfigDemo {

	private String path = "/zuka/app/provider/";

	private ZooKeeper zk;

	protected void start0() throws IOException {
		
		zk = new ZooKeeper(address, timeout, new Watcher() {

			@Override
			public void process(WatchedEvent event) {
				String path = event.getPath();
				System.out.println("watch:"+event.getType()+",path:"+path);
				
			}
		});
		
//		create();
//		get();
//		
//		set();
		get();
		
		delete();
		get();
		
		close();
	}
	
	public void create(){
		
		String data = "hello world!";
		
		System.out.println("create data:"+data);
		
		try {
			String result = zk.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println(result);
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void get(){
		
		String result = null;  
        try {  
            byte[] bytes = zk.getData(path, null, null);  
            result = new String(bytes);  
       } catch (Exception e) {  
           	e.printStackTrace();
       }
       System.out.println("get data:"+result);
	}
	
	public void set(){
		
		String data = "hello ricky!";
		
		System.out.println("set data:"+data);
		try {
			zk.setData(path, data.getBytes(), -1);
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(){
		
		try {
			zk.delete(path, -1);
		} catch (InterruptedException | KeeperException e) {
			e.printStackTrace();
		}
	}
	
	public void close(){
		
		try {
			zk.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}