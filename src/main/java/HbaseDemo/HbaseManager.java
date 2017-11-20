package HbaseDemo;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseManager extends Thread{
	public static Configuration config;
	public  HbaseManager() throws IOException {
		config=HBaseConfiguration.create();
		config.set("hbase.master", "master:60000");
		//set hbase master
		config.set("hbase.zookeeper.property.clientPort", "2181");
		//set zookeeper prot
		config.set("hbase.zookeeper.quorum","master");
		//set zookeeper point
	}
}
