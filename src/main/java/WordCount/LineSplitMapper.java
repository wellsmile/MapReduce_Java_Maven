package WordCount;

import java.io.IOException;

import javax.ws.rs.core.NewCookie;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LineSplitMapper extends Mapper<Object, Text, Text, Text> {
//	public static final IntWritable val=new IntWritable(1);
	public Text name=new Text();
	public Text any=new Text();
	public void map(Object key,Text value,Context context)throws InterruptedException,IOException{
	
		String line=value.toString();
		String[] arr=line.split("\t");
		String pin=arr[1]+"\t"+arr[2]+"\t"+arr[3];
//		for(String wd:arr){
//			word.set(wd);
//			context.write(word,val);
//		}
		name.set(arr[0]);
		any.set(pin);
		context.write(name, any);
	}
}
