package com.mydistributedsystem.interfaces;
import java.util.ArrayList;

import com.mydistributedsystem.interfaces.Job;


public class JobAccumulate extends Job{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public <T> Object doJob(ArrayList<T> data) {
		// TODO Auto-generated method stub
		
		double sum = 0;
		if(data == null || data.size() == 0){
			
		}else {
			for(int i = 0; i < data.size(); i++){
				sum += (Double)data.get(i);
			}
			
			return sum;
		}
		
		return 0;
	}

}
