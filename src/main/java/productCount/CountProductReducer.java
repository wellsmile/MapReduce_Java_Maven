package productCount;

import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CountProductReducer extends Reducer<Text, Text,Text,IntWritable> {
	public IntWritable val=new IntWritable();
	public void reduce(Text key,Iterable<Text> values,Context context)throws InterruptedException,IOException{
		HashSet<String> markets=new HashSet<String>();
		for (Text value:values) {
			markets.add(value.toString());
		}
		val.set(markets.size());
		context.write(key, val);
	
	
		
	}
}
