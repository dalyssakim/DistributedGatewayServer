package com.mydistributedsystem.interfaces;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Job implements Serializable{
	
	public abstract <T extends Object> Object  doJob(ArrayList<T> data);
}
