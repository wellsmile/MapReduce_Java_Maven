package Markets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class provinceReducer extends Reducer<Text, Text,Text, NullWritable> {
	//在china_province中排除上一步统计出已有的
	Text keywordstText =new Text();
	public void reduce(Text key,Iterable<Text> values,Context context)throws InterruptedException,IOException{
	List<String> list=new ArrayList<>();
	String uid=null; 
		for (Text value:values) {
			if (value.toString().startsWith(WholeProvince.LABEL)) {
				uid=value.toString().substring(2);
			}
			if (value.toString().startsWith(provinceMapper.LABEL)) {
				String markets=value.toString().substring(2).split("\t")[0];
				list.add(markets);
			}
		}
		if (uid==null&&list.size()==0) {
				context.write(new Text(uid), NullWritable.get());
		}
	
	
		
	}
}
