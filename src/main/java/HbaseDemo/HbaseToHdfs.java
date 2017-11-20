package HbaseDemo;


import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class HbaseToHdfs {
	public static final String NAME="Blog Test1";
	public static final String TMP_INDEX_PATH="/hbaseTOhdfs";
	public static final String INPUT_TABLE="stus";
	
	public static void main(String[] args) throws Exception {
		
		Configuration configuration = HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.quorum", "master");

		Scan scan=new Scan();
		scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"));
		
		Path tempIndexPath = new Path(TMP_INDEX_PATH);
		FileSystem fs = FileSystem.get(configuration);
		if(fs.exists(tempIndexPath)){
			fs.delete(tempIndexPath,true);
		}
		
		Job job = new Job(configuration, NAME);
		job.setJarByClass(HbaseToHdfs.class); 
		
		TableMapReduceUtil.initTableMapperJob(INPUT_TABLE, scan, ExampleMapper.class, 
				Text.class, Text.class, job);
		
		job.setNumReduceTasks(0);
		job.setOutputFormatClass(TextOutputFormat.class);
		FileOutputFormat.setOutputPath(job, tempIndexPath); 
		
		job.waitForCompletion(true);
		
	}
	static class ExampleMapper extends TableMapper<Writable, Writable>{
		private Text k=new Text();
		private Text v=new Text();
		
		//public static final String FIELD_COMMON_SEPARATOR="\u0001";
		@Override
		protected void map(ImmutableBytesWritable row, Result columns,
				Context context)
				throws IOException, InterruptedException {
			String value=null;
			String rowkey=new String(row.get());
			byte[]columnFamily=null;
			byte[]columnQualifier=null;
			long ts=0L;
			
			try {
				List<KeyValue> list = columns.list();
				for (KeyValue kv : list) {
					value=Bytes.toStringBinary(kv.getValue());
					columnFamily=kv.getFamily();
					columnQualifier=kv.getQualifier();
					ts=kv.getTimestamp();
					
					if("tom".equals(value)){
						k.set(rowkey);
						v.set(Bytes.toString(columnFamily)+"\t"+
						      Bytes.toString(columnQualifier)+"\t"+
						      value+"\t"+ts);
						
						context.write(k, v);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Error:"+e.getMessage()+",Row:"+
				Bytes.toString(row.get())+",Value:"+value);
			}
		}
	}

}
