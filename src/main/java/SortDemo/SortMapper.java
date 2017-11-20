package SortDemo;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SortMapper extends Mapper<Object,Text,IntWritable,IntWritable>{
//	private Text uidText=new Text();
	IntWritable i1=new IntWritable();
	public void map(Object key,Text value,Context context)throws IOException,InterruptedException {
//		String line=value.toString();
//		String[] arr=line.split("\t");
//		if (null!=arr&&arr.length==6) {
//			String uid=arr[1];
//			if (null!=uid&&!"".equals(uid.trim())) {
//				uidText.set(uid);
//			uidText.set(line);
//		
			int val=Integer.parseInt(value.toString());
			i1.set(val);
			context.write(i1,i1);
				
			}
//		}
//	}
}
