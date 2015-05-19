package Interface;

import java.util.ArrayList;

public class JobMean implements Job{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public <T extends Object> Object doJob(ArrayList<T> data) {
		// TODO Auto-generated method stub
		double sum = 0;
		if(data == null || data.size() == 0){
			
		}else {
			for(int i = 0; i < data.size(); i++){
				sum += (double)data.get(i);
			}
			
			return sum/data.size();
			//return (Object) String.valueOf(sum/data.size());
		}
		return 0;
	}

}
