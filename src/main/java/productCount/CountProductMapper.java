package productCount;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CountProductMapper extends Mapper<Object, Text, Text, Text> {
	public Text name=new Text();
	public Text any=new Text();
	public void map(Object key,Text value,Context context)throws InterruptedException,IOException{
		name.set(value.toString().split("\t")[7]);
		any.set(value.toString().split("\t")[0]);
		context.write(name, any);
	}
}
