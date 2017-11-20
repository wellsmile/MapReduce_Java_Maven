package default_package;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MinimalMapReduceV2 extends Configured implements Tool {

	public int run(String[] args) throws Exception {
		if (args.length != 2) { // 判断传参
			System.err.printf("Usage: %s [generic options] <input> <output>\n",
					getClass().getSimpleName());
			ToolRunner.printGenericCommandUsage(System.err);
			return -1;
		}
		boolean useV1 = false;
		if (useV1) {
			// v1
			JobConf conf = new JobConf(getConf(), getClass());
			FileInputFormat.addInputPath(conf, new Path(args[0]));
			FileOutputFormat.setOutputPath(conf, new Path(args[1]));
			JobClient.runJob(conf);
		} else {
			// v2
			Job job = new Job(getConf(), "minimal mapreduce v2");
			org.apache.hadoop.mapreduce.lib.input.FileInputFormat.addInputPath(
					job, new Path(args[0]));
			org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
					.setOutputPath(job, new Path(args[1]));
			job.waitForCompletion(true);
		}
  
		return 0;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new MinimalMapReduceV2(), args);
		System.exit(exitCode);
	}
}
