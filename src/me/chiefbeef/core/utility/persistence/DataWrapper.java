package me.chiefbeef.core.utility.persistence;


public class DataWrapper<T extends Data> {

	private T data; 
	
	public DataWrapper(T data) {
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	
	public boolean hasData() {
		return data != null;
	}

}
