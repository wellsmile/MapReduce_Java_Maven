package day0921;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class XianJianMapper extends Mapper<Object, Text, Text, Text> {
//	public static final IntWritable val=new IntWritable(1);
	public Text newValue=new Text();
	public static final String LABEL="U_";
	public void map(Object key,Text value,Context context)throws InterruptedException,IOException{
		
		String line=value.toString();

			newValue.set(LABEL+line);
		context.write(value, newValue);
		}
		
	}
