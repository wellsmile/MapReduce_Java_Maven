package DateSplit;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DateMapper extends Mapper<Object,Text,Text,NullWritable> {
	private Text resultText=new Text();
	public void map(Object key,Text value,Context context)throws IOException,InterruptedException {
		String resultString=value.toString().split(" ")[4];
		resultText.set(resultString);
		context.write(resultText, NullWritable.get());
		
	}

}
