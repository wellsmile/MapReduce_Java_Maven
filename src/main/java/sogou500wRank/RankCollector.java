package sogou500wRank;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import sogou1w.UidCollector;
import sogou1w.UidMapper;
import sogou1w.UidReducer;

public class RankCollector {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		if (args!=null&&args.length==2) {
			Job job =new Job(new Configuration(),"RankCollector");
			job.setJarByClass(RankCollector.class);
			job.setMapperClass(RankMapper.class);
//			job.setNumReduceTasks(0);
			job.setReducerClass(RankReducer.class);
//			FileInputFormat.setMaxInputSplitSize(job, 10);
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(DoubleWritable.class);
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			
			FileInputFormat.addInputPath(job,new Path(args[0]) );
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			
			System.exit(job.waitForCompletion(true)?0:1);
		}else {
			System.out.println("<usage>:RankCollector <input> <output>...");
		}
	}

}
