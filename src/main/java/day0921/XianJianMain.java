package day0921;

		import java.io.IOException;

		import org.apache.hadoop.conf.Configuration;
		import org.apache.hadoop.fs.Path;
		import org.apache.hadoop.io.Text;
		import org.apache.hadoop.mapreduce.Job;
		import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class XianJianMain {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws IOException, ClassNotFoundException, InterruptedException {
				if (args!=null&&args.length==3) {
					Job job =new Job(new Configuration(),"XianJianMain");
					job.setJarByClass(XianJianMain.class);
//					job.setMapperClass(XianJianMapper.class);
					job.setReducerClass(XianJianReducer.class);
					
					job.setOutputKeyClass(Text.class);
					job.setOutputValueClass( Text.class);
					MultipleInputs.addInputPath(job, new Path(args[0]), FileInputFormat.class,WholeFileMapper.class);
					MultipleInputs.addInputPath(job, new Path(args[1]), FileInputFormat.class,XianJianMapper.class);
					FileOutputFormat.setOutputPath(job, new Path(args[2]));
					
					System.exit(job.waitForCompletion(true)?0:1);
				}else {
					System.out.println("<usage>:XianJianMain <input> <output>...");
				}
				
		
	}

}
