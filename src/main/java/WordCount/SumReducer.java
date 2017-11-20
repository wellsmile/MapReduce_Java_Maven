package WordCount;

import java.io.IOException;

import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SumReducer extends Reducer<Text, Text,Text,Text> {
	public Text val=new Text();
	public void reduce(Text key,Iterable<Text> values,Context context)throws InterruptedException,IOException{
		int sum1=0;
		int sum2=0;
		int sum3=0;
		int i=0;
		int m=0;
		for (Text value:values) {
			m=m+1;
		}
		Text[] values1=new Text[m];
		for (Text value2:values) {
			values1[i]=value2;
			i++;
		}
		
		
		String[] arr=new String[values1.length*3];
		for (int j = 0; j < values1.length; j++) {
			String line=values1[j].toString();
			arr[j*3]=line.split("\t")[0];
			arr[j*3+1]=line.split("\t")[1];
			arr[j*3+2]=line.split("\t")[2];
		}
		for (int j = 0; j < values1.length; j++) {
			
			sum1=sum1+Integer.parseInt(arr[j*3].toString());
			sum2=sum1+Integer.parseInt(arr[j*3+1].toString());
			sum3=sum1+Integer.parseInt(arr[j*3+2].toString());
		}
		String result="\t"+sum1+"\t"+sum2+"\t"+sum3;
		val.set(result);
		context.write(key, val);
	
	
		
	}
}
