package default_package;
import org.apache.hadoop.hive.ql.exec.UDF;
public class UDFdemo1 extends UDF{
public int evaluate(int i,int j) {
	return (j-1)*10+i;
}
}

