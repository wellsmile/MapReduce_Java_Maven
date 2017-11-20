package DateSplit;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import sogou1w.UidCollector;
import sogou1w.UidMapper;
import sogou1w.UidReducer;

public class DateSpliter {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		Job job =new Job(new Configuration(),"DateSpliter");
		job.setJarByClass(DateSpliter.class);
		job.setMapperClass(DateMapper.class);
//		job.setNumReduceTasks(0);
//		job.setReducerClass(UidReducer.class);
		
//		job.setMapOutputKeyClass(Text.class);
//		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
		FileInputFormat.addInputPath(job,new Path(args[0]) );
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		System.exit(job.waitForCompletion(true)?0:1);
	}

}
