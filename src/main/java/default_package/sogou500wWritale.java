package default_package;
import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.jasper.tagplugins.jstl.core.Out;


public class sogou500wWritale {


		public static void main(String[] args) throws Exception{
			File file = new File("/home/zkpk/Desktop/sogou-data/500w/sogou.500w.utf8");
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			String s = null;
			int count = 0;
			int length = 0;
			sogouWritable sogou1=new sogouWritable();
			while ((s = bReader.readLine()) != null) {
				String[] spt = s.split("\t");
				sogou1.setTime(new Text(spt[0]));
				sogou1.setUid(new Text(spt[1]));
				sogou1.setKeyword(new Text(spt[2]));
				sogou1.setRank(new IntWritable(Integer.parseInt(spt[3])));
				sogou1.setPage(new IntWritable(Integer.parseInt(spt[4])));
				sogou1.setUrl(new Text(spt[5]));
				System.out.println(sogou1.toString());
			}
	}

}
