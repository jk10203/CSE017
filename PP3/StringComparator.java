
import java.util.Comparator;
public class StringComparator implements Comparator<String>{
    public int compare(String s1, String s2){
        //comparing two strings before @ sign
        String[] strung = s1.split("@");
        String[] strang = s2.split("@");
        //index 0 has the string we need
        return (strung[0].compareTo(strang[0]));

    }
}