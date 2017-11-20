package default_package;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.util.ReflectionUtils;



public class MapFileReadDemo {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		String uri="hdfs://master:9000/0908";
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(URI.create(uri),conf);
		Path path=new Path(uri);
		MapFile.Reader reader=null;
		reader=new MapFile.Reader(fs, uri, conf);
		WritableComparable key =(WritableComparable)ReflectionUtils.newInstance(reader.getKeyClass(), conf);
		Writable value =(Writable)ReflectionUtils.newInstance(reader.getValueClass(), conf);
		while (reader.next(key,value)) {
			System.out.printf("%s\t%s\n",key,value);
		}
		IOUtils.closeStream(reader);
	}

}
