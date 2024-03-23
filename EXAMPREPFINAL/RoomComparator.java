
import java.util.Comparator;
public class RoomComparator implements Comparator<Room>{
	public int compare(Room r1, Room r2) {
		Double p1 = r1.getPrice();
		double p2 = r2.getPrice();
		return p1.compareTo(p2);
	}
}
