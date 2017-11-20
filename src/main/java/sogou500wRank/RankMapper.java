package sogou500wRank;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class RankMapper extends Mapper<Object,Text,Text,DoubleWritable> {
	private Text rankText=new Text();
	public static DoubleWritable ONE=new DoubleWritable(1.0);
	public void map(Object key,Text value,Context context)throws IOException,InterruptedException {
		String line=value.toString();
		String[] arr=line.split("\t");
		
		if (null!=arr) {
			String rank=arr[3];
			if (null!=rank&&!"".equals(rank.trim())&&Integer.parseInt(rank)<=10) {
				rankText.set(rank);
				context.write(rankText, ONE);
			}
		}
	}
}
