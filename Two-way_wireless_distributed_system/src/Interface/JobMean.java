package Interface;

import java.util.ArrayList;

public class JobMean implements Job{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Object doJob(ArrayList data) {
		// TODO Auto-generated method stub
		double sum = 0;
		if(data == null || data.size() == 0){
			
		}else {
			for(int i = 0; i < data.size(); i++){
				sum += Double.parseDouble((String)data.get(i));
			}
			
			return (Object) String.valueOf(sum/data.size());
		}
		return null;
	}

}
