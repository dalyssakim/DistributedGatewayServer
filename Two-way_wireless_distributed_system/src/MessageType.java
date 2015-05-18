
public class MessageType {
	
	public static MessageType mType;
	
	private MessageType(){
		mType = new MessageType();
	}

	public static int halt = 0;
	public static int pullInterface = 1;
	public static int pushInterface = 2;
	public static int pushResult = 3;
	public static int acceptResult = 4;
	
}
