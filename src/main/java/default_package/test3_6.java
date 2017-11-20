package default_package;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;


public class test3_6 {

	public static void main(String args[]) throws IOException{
		
		String[] uri={"hdfs://master:9000/0902","hdfs://master:9000/0903"};
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(URI.create(uri[0]),conf);
		Path[] paths=new Path[uri.length];
		for (int i = 0; i < paths.length; i++) {
			paths[i]=new Path(uri[i]);
		}
		FileStatus[] status=fs.listStatus(paths);
		Path[] listedPaths=FileUtil.stat2Paths(status);
		System.out.println("\n--------ls a group of dir--------\n");
		for (Path p:listedPaths) {
			System.out.println(p);
		}
		System.out.println("\n--------end with txt && !end with 2.txt--------\n");
		FileStatus[] status1=fs.globStatus(new Path("hdfs://master:9000/0902/*.txt"),new Regex(".*2.txt"));
		Path[] listedPaths1=FileUtil.stat2Paths(status1);
		for (Path q:listedPaths1) {
			System.out.println(q);
		}
		System.out.println("\n--------delete a dir--------\n");
		String dst="hdfs://master:9000/0902/README.txt";
		FileSystem fs2=FileSystem.get(URI.create(dst),conf);
		fs.delete(new Path("hdfs://master:9000/0902/README.txt"));
		
		FileStatus[] status2=fs.listStatus(new Path("hdfs://master:9000/0902/"));
		Path[] listedPaths2=FileUtil.stat2Paths(status2);
		for (Path pp:listedPaths2) {
			System.out.println(pp);
		}
		
	}
	
}
class Regex implements PathFilter{
	private final String regex;
	public Regex(String regex){
		this.regex=regex;
	}
	public boolean accept(Path path) {
		// TODO Auto-generated method stub
		return !path.toString().matches(regex);
	}
	
}