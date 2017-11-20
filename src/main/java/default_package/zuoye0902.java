package default_package;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;



public class zuoye0902 {
	static{
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("/home/zkpk/Desktop/sogou-data/500w/sogou.500w.utf8");
		BufferedReader bReader = new BufferedReader(new FileReader(file));
		String s = null;
		int count = 0;
		int length = 0;
		while ((s = bReader.readLine()) != null) {
			String[] spt = s.split("\t");
			String keyword = spt[2];
			length+=string_length(keyword);
			count++;
		}
		int avg=length/count;
		System.out.println(avg);
		
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(URI.create("hdfs://master:9000/0902/zuoye0902.txt"),conf);
		FSDataOutputStream out=null;
		out=fs.append(new Path("hdfs://master:9000/0902/zuoye0902.txt"));
		out.write(avg);
		

	}
	private static int string_length(String keyword) {
		// TODO Auto-generated method stub
		int valueLength=0;
		String english="[a-z|A-Z]";
		for(int i=0;i<keyword.length();i++){
			String temp=keyword.substring(i,i+1);
			if (temp.matches(english)) {
               valueLength+=1;			
			}else {
				valueLength+=2;
			}
		}
		return valueLength;
		
		
	}

}
