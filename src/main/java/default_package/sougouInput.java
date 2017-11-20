package default_package;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class sougouInput {
	public static void main(String[] args) throws IOException {
		String local="/home/zkpk/sogou-data/500w/sogou.500w.utf8";
		String dst="hdfs://master:9000/sogou.500w.utf8";
		InputStream in=new BufferedInputStream(new FileInputStream(local));
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(URI.create(dst),conf);
		FSDataOutputStream out=null;
		try{
			out=fs.create(new Path(dst));
			IOUtils.copyBytes(in,out,4096,false);
		}finally{
			in.close();
			IOUtils.closeStream(out);
		}
	}

}

