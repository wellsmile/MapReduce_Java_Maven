package default_package;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;


public class test3_3 {
	public static void main(String[] args) throws IOException {
		String uri="hdfs://master:9000/0902/README.txt";
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(URI.create(uri),conf);
		FSDataInputStream in=null;
		try{
		in=fs.open(new Path(uri));
		IOUtils.copyBytes(in,System.out,4096,false);
		in.seek(0);
		System.out.println("\nthe second\n");
		IOUtils.copyBytes(in, System.out, 4096,false);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			IOUtils.closeStream(in);
		}
	}

}
