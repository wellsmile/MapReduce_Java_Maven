package default_package;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class sogouWritable implements WritableComparable<sogouWritable> {
	Text time;
	Text uid;
	Text keyword;
	IntWritable rank;
	IntWritable page;
	Text url;
	public sogouWritable(){}
	public sogouWritable(Text time, Text uid, Text keyword, IntWritable rank,
			IntWritable page, Text url) {
		this.time = time;
		this.uid = uid;
		this.keyword = keyword;
		this.rank = rank;
		this.page = page;
		this.url = url;
	}
	@Override
	public String toString() {
		return "sogouWritable [time=" + time + ", uid=" + uid + ", keyword="
				+ keyword + ", rank=" + rank + ", page=" + page + ", url="
				+ url + "]";
	}
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		time.readFields(in);
		uid.readFields(in);
		keyword.readFields(in);
		rank.readFields(in);
		page.readFields(in);
		url.readFields(in);
	}

	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		time.write(out);
		uid.write(out);
		keyword.write(out);
		rank.write(out);
		page.write(out);
		url.write(out);
	}
	public Text getTime() {
		return time;
	}

	public void setTime(Text time) {
		this.time = time;
	}

	public Text getUid() {
		return uid;
	}

	public void setUid(Text uid) {
		this.uid = uid;
	}

	public Text getKeyword() {
		return keyword;
	}

	public void setKeyword(Text keyword) {
		this.keyword = keyword;
	}

	public IntWritable getRank() {
		return rank;
	}

	public void setRank(IntWritable rank) {
		this.rank = rank;
	}

	public IntWritable getPage() {
		return page;
	}

	public void setPage(IntWritable page) {
		this.page = page;
	}

	public Text getUrl() {
		return url;
	}

	public void setUrl(Text url) {
		this.url = url;
	}



	public int compareTo(sogouWritable o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
