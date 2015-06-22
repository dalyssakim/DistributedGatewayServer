package com.mydistributedsystem.nodes;

public abstract class Node {

		private int gid;
		private int id;
		private int status;
		private int priority;
		private String jobName;
		private boolean isJobChanged = false;
		private int recent_ts ; //  
		private Object recentVal = null;
		
		
		public int getRecent_ts() {
			return recent_ts;
		}
		public void setRecent_ts(int recent_ts) {
			this.recent_ts = recent_ts;
		}
		public Object getRecentVal() {
			return recentVal;
		}
		public void setRecentVal(Object data) {
			this.recentVal = data;
		}
		public boolean isJobChanged() {
			return isJobChanged;
		}
		public void setJobChanged(boolean isJobChanged) {
			this.isJobChanged = isJobChanged;
		}
		public int getPriority() {
			return priority;
		}
		public void setPriority(int priority) {
			this.priority = priority;
		}
		public int getGid() {
			return gid;
		}
		public void setGid(int gid) {
			this.gid = gid;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getJobName() {
			return jobName;
		}
		public void setJobName(String jobName) {
			this.jobName = jobName;
		}
		public int getRecentTs(){
			return this.recent_ts;
		}
		public void setRecentTs(int ts){
			this.recent_ts = ts ;
		}

		
		
}
