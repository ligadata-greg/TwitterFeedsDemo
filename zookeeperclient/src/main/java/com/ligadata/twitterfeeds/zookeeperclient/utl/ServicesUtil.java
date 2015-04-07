package com.ligadata.twitterfeeds.zookeeperclient.utl;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.Logger;
import org.apache.zookeeper.data.Stat;

public class ServicesUtil
{
  private static Logger logger = Logger.getLogger(ServicesUtil.class);
  public static final int RETRY_SLEEP_TIME = 1000;
  public static final int MAX_RETRY = 3;
  
  public static CuratorFramework createSimpleClient(String connectionString)
  {
    ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(
      1000, 3);
    
    return CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
  }
  
  public static String getValue(CuratorFramework client, String path)
    throws Exception
  {
    try
    {
      return new String((byte[])client.getData().forPath(path));
    }
    catch (Exception e)
    {
      logger.error("Error while reading from ZooKeeper", e);
      throw e;
    }
  }
  
  public static synchronized void setValue(CuratorFramework client, String path, String value)
    throws Exception
  {
    try
    {
      client.setData().forPath(path, value.getBytes());
    }
    catch (Exception e)
    {
      logger.error("Error while writing to ZooKeeper", e);
      throw e;
    }
  }
  
  public static synchronized void createZNode(CuratorFramework client, String path, String value)
		  throws Exception
   {
	 try
	 {
		 client.create().forPath(path,value.getBytes());
	 }
	 catch (Exception e)
	 {
		 logger.error("Error while writing to ZooKeeper", e);
		 throw e;
	 }
  }
  
  public static synchronized boolean zNodeExists(CuratorFramework client, String path)
		  throws Exception
   {
	 try
	 {
		 Stat s = client.checkExists().forPath(path);
		 
		 if(s == null){
			return false; 
		 }else{
		    return true;
		 }
	 }
	 catch (Exception e)
	 {
		 logger.error("Error while writing to ZooKeeper", e);
		 throw e;
	 }
  }  
}
