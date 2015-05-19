package Message;
import java.io.Serializable;


public class JDMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int id;
	public int type;
	public Object data;

	public void setMessage(int id, int type, Object data){
		this.id = id;
		this.type = type;
		this.data = data;
	}
	
}
