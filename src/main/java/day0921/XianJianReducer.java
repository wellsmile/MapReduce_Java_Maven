package day0921;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class XianJianReducer extends Reducer<Text, Text,Text, Text> {
	Text keywordstText =new Text();
	public void reduce(Text key,Iterable<Text> values,Context context)throws InterruptedException,IOException{
	List<String> list=new ArrayList<String>();
	String uid=null; 
		for (Text value:values) {
			if (value.toString().startsWith(XianJianMapper.LABEL)) {
				uid=value.toString().substring(2);
			}
			if (value.toString().startsWith(WholeFileMapper.LABEL)) {
				String keywords=value.toString().substring(2).split("\t")[2];
				list.add(keywords);
			}
		}
		if (uid!=null&&list.size()>0) {
			for (String value:list){
				context.write(key, new Text(value));
			}
		}
//		context.write(key, NullWritable.get());
	
	
		
	}
}
