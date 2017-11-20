package Markets;

		import java.io.IOException;

		import org.apache.hadoop.conf.Configuration;
		import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
		import org.apache.hadoop.io.Text;
		import org.apache.hadoop.mapreduce.Job;
		import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class provinceMain {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws IOException, ClassNotFoundException, InterruptedException {
				if (args!=null&&args.length==3) {
					Job job =new Job(new Configuration(),"XianJianMain");
					job.setJarByClass(provinceMain.class);
//					job.setMapperClass(XianJianMapper.class);
					job.setReducerClass(provinceReducer.class);
					job.setMapOutputKeyClass(Text.class);
					job.setMapOutputValueClass(Text.class);
					
					job.setOutputKeyClass(Text.class);
					job.setOutputValueClass( NullWritable.class);
					MultipleInputs.addInputPath(job, new Path(args[0]), FileInputFormat.class,WholeProvince.class);
					MultipleInputs.addInputPath(job, new Path(args[1]), FileInputFormat.class,provinceMapper.class);
					FileOutputFormat.setOutputPath(job, new Path(args[2]));
					
					System.exit(job.waitForCompletion(true)?0:1);
				}else {
					System.out.println("<usage>:XianJianMain <input> <output>...");
				}
				
		
	}

}
