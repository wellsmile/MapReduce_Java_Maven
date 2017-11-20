package default_package;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapred.lib.MultipleOutputFormat;
import org.apache.hadoop.util.Progressable;

public class MyMultipleOutputFormatApp {
	private static final String INPUT_PATH = "hdfs://master:9000/input";
	private static final String OUT_PATH = "hdfs://master:9000/out/";

	public static void main(String[] args) throws Exception{
		Configuration conf = new Configuration();
		final FileSystem filesystem = FileSystem.get(new URI(OUT_PATH), conf);
		filesystem.delete(new Path(OUT_PATH), true);
		/////////////////
		final JobConf job = new JobConf(conf , WordCountApp.class);
		job.setJarByClass(WordCountApp.class);
		
		FileInputFormat.setInputPaths(job, INPUT_PATH);
		job.setMapperClass(MyMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		
		job.setReducerClass(MyReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		job.setOutputFormat(MyMultipleFilesTextOutputFormat.class);
		FileOutputFormat.setOutputPath(job, new Path(OUT_PATH));
		
		JobClient.runJob(job);
	}
	
	public static class MyMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, LongWritable>{
			public void map(LongWritable key, Text value,
				OutputCollector<Text, LongWritable> output, Reporter reporter)
				throws IOException {
				
				final String line = value.toString();
				final String[] splited = line.split("\t");			
				
				for (String word : splited) {					
					output.collect(new Text(word), new LongWritable(1));
				}
		};
	}
	
	public static class MyReducer extends MapReduceBase implements  Reducer<Text, LongWritable, Text, LongWritable>{
		public void reduce(Text key, Iterator<LongWritable> values,
				OutputCollector<Text, LongWritable> output, Reporter reporter)
				throws IOException {
				long count = 0L;
				while(values.hasNext()) {
					LongWritable times = values.next();
					count += times.get();
				}
				output.collect(key, new LongWritable(count));
		};
	}
	
	public static class MyMultipleFilesTextOutputFormat extends MultipleOutputFormat<Text, LongWritable>{

		@Override
		protected org.apache.hadoop.mapred.RecordWriter<Text, LongWritable> 
		getBaseRecordWriter(
				FileSystem fs, JobConf job, String name, Progressable progress)
				throws IOException {
			final TextOutputFormat<Text, LongWritable> textOutputFormat = new TextOutputFormat<Text, LongWritable>();
			return textOutputFormat.getRecordWriter(fs, job, name, progress);
		}
		
		
		@Override
		protected String generateFileNameForKeyValue(Text key,
				LongWritable value, String name) {
			
			final String keyString = key.toString();
			if(keyString.startsWith("hello")) {
				return "hello";
			}else {
				return keyString;
			}
		}
		
	}
}
