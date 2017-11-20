package default_package;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;


public class test3_4 {
	public static void main(String[] args) throws IOException {
		String localSrc="/home/zkpk/Desktop/newfile";
		String dst="hdfs://master:9000/0902/newfile2";
		InputStream in=new BufferedInputStream(new FileInputStream(localSrc));
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(URI.create(dst),conf);
		OutputStream out=fs.create(new Path(dst),new Progressable(){
			public void progress(){
				System.out.print(".");
			}
		});
		IOUtils.copyBytes(in, out,4096, true);
	}

}
