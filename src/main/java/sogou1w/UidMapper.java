package sogou1w;

import java.io.IOException;

import javax.ws.rs.core.NewCookie;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class UidMapper extends Mapper<Object,Text,Text,IntWritable> {
//	private Text uidText=new Text();
	IntWritable i=new IntWritable(1);
	public void map(Object key,Text value,Context context)throws IOException,InterruptedException {
//		String line=value.toString();
//		String[] arr=line.split("\t");
//		if (null!=arr&&arr.length==6) {
//			String uid=arr[1];
//			if (null!=uid&&!"".equals(uid.trim())) {
//				uidText.set(uid);
//			uidText.set(line);
				context.write(value, i);
			}
//		}
//	}
}
