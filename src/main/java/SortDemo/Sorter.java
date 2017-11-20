package SortDemo;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Sorter {

	public static void main(String[] args) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
//		if (args!=null&&args.length==2) {
			Job job =new Job(new Configuration(),"SortDemo");
			job.setJarByClass(Sorter.class);
			job.setMapperClass(SortMapper.class);
			job.setReducerClass(SortReducer.class);
			//			job.setNumReduceTasks(1);
//			FileInputFormat.setMaxInputSplitSize(job, 10);
			job.setOutputKeyClass(IntWritable.class);
			job.setOutputValueClass(IntWritable.class);
			
//			job.setMapOutputKeyClass(Text.class);
//			job.setMapOutputValueClass(IntWritable.class);
//			
			FileInputFormat.addInputPath(job,new Path(args[0]) );
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			
			System.exit(job.waitForCompletion(true)?0:1);
//		}else {
//			System.out.println("<usage>:UidCollector <input> <output>...");
//		}
	}
	}


