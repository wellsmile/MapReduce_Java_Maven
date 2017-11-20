package HbaseDemo;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

public class HdfsToHbase {

	public static void main(String[] args) throws Exception {
		final Configuration configuration = new Configuration();
		//设置zookeeper运行节点
		configuration.set("hbase.zookeeper.quorum", "master");
		//设置hbase表名称 ******************
		configuration.set(TableOutputFormat.OUTPUT_TABLE, "blog");
		//防止hbase超时退出
		configuration.set("dfs.socket.timeout", "180000");
		
		final Job job = new Job(configuration, HdfsToHbase.class.getSimpleName());
		job.setJarByClass(HdfsToHbase.class); ////
		job.setMapperClass(MyMap.class);
		job.setReducerClass(MyReducer.class);
		//设置map的输出，不设置reduce的输出类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		//不设置输出路径，直接设置输出格式类型
		job.setOutputFormatClass(TableOutputFormat.class);
		
		FileInputFormat.setInputPaths(job, "hdfs://master:9000/hbaseTOhdfs/part-m-00000");
		
		job.waitForCompletion(true);

	}
	static class MyMap extends Mapper<LongWritable, Text, Text, Text>{
		Text v2 = new Text();
		@Override
		protected void map(LongWritable key, Text value,
				Context context)
				throws IOException, InterruptedException {
			final String[] splited = value.toString().split("\t");
			try {
				String rowKey = splited[0]+System.currentTimeMillis();
				v2.set(value.toString());
				context.write(new Text(rowKey), v2);
			} catch (NumberFormatException e) {
				System.out.println("出错了"+splited[0]+" "+e.getMessage());
			}
		}
	}
	static class MyReducer extends TableReducer<Text, Text, NullWritable>{
		@Override
		protected void reduce(Text k2, Iterable<Text> v2s,
				Context context)
				throws IOException, InterruptedException {
			for (Text text : v2s) {
				final String[] splited = text.toString().split("\t");
				//将k2作为RowKey
				final Put put = new Put(Bytes.toBytes(k2.toString()));
				
				put.add(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes(splited[3]));
				//***********************
				context.write(NullWritable.get(), put);
			}
		}
	}
}
