package default_package;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;


public class SequenceFileWriteDemo {

	private static final String[] DATA={
		"what the fuck","holy shit","what can i do for you","are you fuck a pig","come on","what does the foxes say"
	};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String uri="hdfs://master:9000/SequenceWrite";
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(URI.create(uri),conf);
		Path path=new Path(uri);
		IntWritable key=new IntWritable();
		Text value=new Text();
		SequenceFile.Writer writer=null;
		writer=SequenceFile.createWriter(fs, conf, path, key.getClass(), value.getClass());
		for (int i = 0; i < 100; i++) {
			key.set(100-i);
			value.set(DATA[i%DATA.length]);
			System.out.printf("[%s]\t%s\t%s\n",writer.getLength(),key,value);
			writer.append(key, value);
		
		}
		IOUtils.closeStream(writer);
	}

}
