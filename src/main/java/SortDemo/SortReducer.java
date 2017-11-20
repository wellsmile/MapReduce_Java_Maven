package SortDemo;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class SortReducer extends Reducer<IntWritable,IntWritable, IntWritable,IntWritable> {
	public IntWritable val=new IntWritable();
	static int i=0;
	public void reduce(IntWritable key,Iterable<IntWritable> values,Context context)throws InterruptedException,IOException{
		
//		for(IntWritable value:values){
			
			i=i+1;
			
		val.set(i);
		context.write(val, key);
	}
}
