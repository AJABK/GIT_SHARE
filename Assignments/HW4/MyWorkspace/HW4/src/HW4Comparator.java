import java.util.Comparator;
import java.util.Map;

/**
 * Comparator for HW4
 * 
 * @author Ajay Kauthale
 *
 */
public class HW4Comparator implements Comparator<Map.Entry<String, Long>> {
	@Override
	public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
		return o2.getValue().compareTo(o1.getValue());
	}
}