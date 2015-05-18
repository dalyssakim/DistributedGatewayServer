
public class NodeStatus {

	public static NodeStatus status;
	
	private NodeStatus(){
		status = new NodeStatus();
	}
	
	public static int disconnected = 0;	
	public static int connected = 1;
	public static int ready = 2;
	public static int run = 3;
	public static int pending = 4;

}
