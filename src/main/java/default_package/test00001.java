package default_package;
import org.apache.hadoop.hive.ql.parse.HiveParser_IdentifiersParser.stringLiteralSequence_return;


public class test00001 {
 static String s1="  asdad  sad sad asddsad ";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s3=s1.replaceAll(" ","");
		System.out.println(s3);
		}}
