package default_package;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;


public class URLCat {
	static{
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	}
	public static void main(String[] args) throws MalformedURLException, IOException {
		InputStream in=null;
		try{
		String path="hdfs://master:9000/0902/README.txt"; 
		in=new URL(path).openStream();
		IOUtils.copyBytes(in,System.out,4096,false);
		}finally{
			IOUtils.closeStream(in);
		}
		
	}

}
