package com.ligadata.twitterfeeds.zookeeperclient;

import org.apache.curator.framework.CuratorFramework;

import com.ligadata.twitterfeeds.zookeeperclient.utl.ServicesFields;
import com.ligadata.twitterfeeds.zookeeperclient.utl.ServicesUtil;



public class Client {
	private static CuratorFramework client;
	private static String connStr = "localhost:2181";
	
	public Client() {
		client = ServicesUtil.createSimpleClient(connStr);

		client.start();
	}
	
	public String getHashTag(String znode) throws Exception {
		return ServicesUtil.getValue(client, ServicesFields.HASHTAGS+"/"+znode);
	}
	
	public String getHashTagData(String znode) throws Exception {
		return ServicesUtil.getValue(client, ServicesFields.HASHTAGS+"/"+znode+"/data");
	}
	
	public String getAction(String znode) throws Exception {
		return ServicesUtil.getValue(client, ServicesFields.HASHTAGS+"/"+znode+"/data");
	}
	
	public String setAction(String znode) throws Exception {
		return ServicesUtil.getValue(client, ServicesFields.HASHTAGS+"/"+znode+"/data");
	}
	
	public String getData() throws Exception {
		return ServicesUtil.getValue(client, ServicesFields.DATA);
	}
	
	public void setData(String value) throws Exception {
		ServicesUtil.setValue(client, ServicesFields.DATA, value);
	}
	
	public void setHashTag(String znode, String value) throws Exception {
		ServicesUtil.setValue(client, ServicesFields.HASHTAGS+"/"+znode, value);
	}
	
	public void setHashTagData(String znode, String value) throws Exception {
		ServicesUtil.setValue(client, ServicesFields.HASHTAGS+"/"+znode+"/data", value);
	}
	
	public void createHashTag(String znode, String value) throws Exception {
		ServicesUtil.createZNode(client, ServicesFields.HASHTAGS+"/"+znode, value);
		ServicesUtil.createZNode(client, ServicesFields.HASHTAGS+"/"+znode+"/data", "{}");
	}
	
	public boolean hashTagExists(String znode) throws Exception {
		return ServicesUtil.zNodeExists(client, ServicesFields.HASHTAGS+"/"+znode);
	}
	
	public void close() throws Exception {
		client.close();
	}
}
