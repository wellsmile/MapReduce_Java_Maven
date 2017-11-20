package sogou500wRank;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RankReducer extends Reducer<Text,DoubleWritable, Text,Text>{
	public Text val=new Text();
	public void reduce(Text key,Iterable<DoubleWritable> values,Context context)throws InterruptedException,IOException{
		double lll=0.0;
		for(DoubleWritable value:values){
			lll=lll+value.get();
			}
		double result=lll/5000000;
		String s1=""+result*100.0;
		String s2=s1.substring(0,5)+"%";
		val.set(s2);
		context.write(key, val);
	}
}
