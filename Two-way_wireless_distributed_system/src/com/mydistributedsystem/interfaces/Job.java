package com.mydistributedsystem.interfaces;

import java.io.Serializable;
import java.util.ArrayList;

public interface Job extends Serializable{
	
	public <T extends Object> Object  doJob(ArrayList<T> data);
}
