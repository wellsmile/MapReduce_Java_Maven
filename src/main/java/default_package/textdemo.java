package default_package;
import org.apache.hadoop.io.Text;
import org.hamcrest.core.Is;


public class textdemo {
	public static void main(String[] args) {
		Text t=new Text("hadoop");
		assertThat (t.getLength(),is(6));
		assertThat (t.getBytes().length,is(6));
		assertThat (t.charAt(2),is(int) 'd');
		assertThat ("OUT OF BOUNDS",t.charAt(100),is(-1));
	}

}
