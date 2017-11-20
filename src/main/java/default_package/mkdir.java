package default_package;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


public class mkdir {
	public static void main(String[] args) throws IOException{
		String dst="hdfs://master:9000/0902/README.txt";
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(URI.create(dst),conf);
		fs.create(new Path("hdfs://master:9000//0902/README.txt"));
		
	}
}
