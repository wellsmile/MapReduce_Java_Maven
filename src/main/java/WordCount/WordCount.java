package WordCount;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		String input=null;
		String output=null;
//		if (args!=null&&args.length==2) {
			input =args[0];
			output=args[1];
			Job job =new Job(new Configuration(),"Word Count");
			job.setJarByClass(WordCount.class);
			job.setMapperClass(LineSplitMapper.class);
			job.setReducerClass(SumReducer.class);
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			
			FileInputFormat.addInputPath(job,new Path(input) );
			FileOutputFormat.setOutputPath(job, new Path(output));
			
			System.exit(job.waitForCompletion(true)?0:1);
//		}else {
//			System.out.println("<usage>:WordCount <input> <output>...");
//		}
		
	}

}
