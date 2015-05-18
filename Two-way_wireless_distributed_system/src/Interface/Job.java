package Interface;

import java.io.Serializable;
import java.util.ArrayList;

public interface Job extends Serializable{
	
	public Object doJob(ArrayList data);
}
