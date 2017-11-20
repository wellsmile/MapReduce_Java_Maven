package sogou1w;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class UidReducer extends Reducer<Text,IntWritable, Text,NullWritable> {
//	public IntWritable val=new IntWritable();
	public void reduce(Text key,Iterable<IntWritable> values,Context context)throws InterruptedException,IOException{
//		int lll=0;
//		for(IntWritable value:values){
//			
//			lll=lll+value.get();
//			}
//		val.set(lll);
		context.write(key, NullWritable.get());
	}
}
