package default_package;

public class example {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line="大白菜  0.70    2014/1/1        广西南宁市五里亭蔬菜批发市场    广西    南宁";
		String[] aaaStrings=line.split("\t");
		System.out.println(aaaStrings.length);
	}

}
