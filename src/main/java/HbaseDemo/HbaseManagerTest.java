package HbaseDemo;

import java.io.IOException;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;


public class HbaseManagerTest {
	public static String TABLE_NAME="java";
	static HTableDescriptor hDescriptor=new HTableDescriptor(TABLE_NAME);
	static Configuration config;
	static {
		config=new Configuration();
		config.set("hbase.master", "master:60000");
		config.set("hbase.zookeeper.property.clientPort", "2181");
		config.set("hbase.zookeeper.quorum","master");
	}
	public static void main(String[] args) throws Exception {	
		HTable table=new HTable(config, Bytes.toBytes(TABLE_NAME));
		HBaseAdmin admin=new HBaseAdmin(config);
		String[] families={"info","address"};
		if (!admin.tableExists(TABLE_NAME)) {
			createTable(admin,families);
		}else {
			deleteTable(admin,TABLE_NAME);
			createTable(admin,families);
		}
		putData(table,"stu1","address","city","tianjin");
		putData(table,"stu1","info","name","wangwu");
		putData(table,"stu2","address","city","beijing");
		putData(table,"stu2","info","name","zhangsan");
//scan whole table
		scanWholetable(table);
//get row data
		getData(table,"stu1");
//scan row data or range [start-stop)
		scanRow(table,"stu1","stu3");
//delete row
		deleteRow(table,"stu1");
//delete column family 
		admin.deleteColumn(TABLE_NAME, "info");
		System.out.println("=============================");
		scanWholetable(table);
	}
	
	private static void deleteRow(HTable table,String deleteRowKey) throws IOException {
		Delete delete=new Delete(Bytes.toBytes(deleteRowKey));
		table.delete(delete);
	}
	private static void scanWholetable(HTable table) throws IOException {
		Scan scan=new Scan();
		ResultScanner rScanner=table.getScanner(scan);
		for (Result result2 : rScanner) {
			List<KeyValue> list=result2.list();
			for (KeyValue keyvalue:list) {
				System.out.print(Bytes.toString(keyvalue.getRow())+" ");
				System.out.print(Bytes.toString(keyvalue.getFamily())+" ");
				System.out.print(Bytes.toString(keyvalue.getQualifier())+" ");
				System.out.println(Bytes.toString(keyvalue.getValue())+" ");
			}
		}
	}
	private static void scanRow(HTable table,String startRowKey,String endRowKey) throws IOException {
		Scan scan=new Scan(Bytes.toBytes(startRowKey), Bytes.toBytes(endRowKey));
		ResultScanner rScanner=table.getScanner(scan);
		for (Result result2 : rScanner) {
			List<KeyValue> list=result2.list();
			for (KeyValue keyvalue:list) {
				System.out.print(Bytes.toString(keyvalue.getRow())+" ");
				System.out.print(Bytes.toString(keyvalue.getFamily())+" ");
				System.out.print(Bytes.toString(keyvalue.getQualifier())+" ");
				System.out.println(Bytes.toString(keyvalue.getValue())+" ");
			}
		}
	}
	private static void getData(HTable table,String rowKey) throws IOException {
		Get get=new Get(Bytes.toBytes(rowKey));
		Result result=table.get(get);
		List<KeyValue> list=result.list();
		for (KeyValue keyvalue:list) {
			System.out.print(Bytes.toString(keyvalue.getRow())+" ");
			System.out.print(Bytes.toString(keyvalue.getFamily())+" ");
			System.out.print(Bytes.toString(keyvalue.getQualifier())+" ");
			System.out.println(Bytes.toString(keyvalue.getValue())+" ");
		}
	}
	private static void deleteTable(HBaseAdmin admin,String tableName) throws IOException {
		admin.disableTable(tableName);
		admin.deleteTable(tableName);
	}
	private static void putData(HTable table,String rowKey,String columnFamily,String columnName,String value)throws Exception {
		Put put=new Put(Bytes.toBytes(rowKey));
		put.add(Bytes.toBytes(columnFamily), Bytes.toBytes(columnName), Bytes.toBytes(value));
		table.put(put);
	}
	private static void createTable(HBaseAdmin admin,String[] families) throws IOException {
		for (String family:families) {
			HColumnDescriptor hColumnDescriptor=new HColumnDescriptor(family);
			hDescriptor.addFamily(hColumnDescriptor);
		}
		admin.createTable(hDescriptor);
	}
}