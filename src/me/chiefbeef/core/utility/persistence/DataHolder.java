package me.chiefbeef.core.utility.persistence;

public interface DataHolder {
	
	/**
	 * Don't forget to implement the static method
	 * public static Class<? extends Data> getDataClass()
	 */
	
	/**
	 * 
	 * @return
	 */
	public abstract DataPack getDataPack();
}
