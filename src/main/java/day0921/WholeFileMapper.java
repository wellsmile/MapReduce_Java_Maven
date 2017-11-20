package day0921;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WholeFileMapper extends Mapper<Object, Text, Text, Text>{
	public Text name=new Text();
	public Text newValue=new Text();
	public static final String LABEL="W_";
	public void map(Object key,Text value,Context context)throws InterruptedException,IOException{
	
		String line=value.toString();
		String[] arr=line.split("\t");
		if (arr !=null&&arr.length==6) {
			String uidString=arr[1];
//		if (arr[2].indexOf("仙剑奇侠传")>=0) {
			name.set(arr[1]);
			newValue.set(LABEL+line);
		context.write(name, newValue);
//		}
			}
		
	}
}
