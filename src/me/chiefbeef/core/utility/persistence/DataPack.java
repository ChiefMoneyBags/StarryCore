package me.chiefbeef.core.utility.persistence;

import java.util.ArrayList;
import java.util.List;


public class DataPack {

	private List<Data> data = new ArrayList<>();
	
	public DataPack add(Data... data) {
		List<Class<? extends Data>> seen = new ArrayList<>();
		for (Data d: data) {
			if (seen.contains(d.getClass())) {
				throw new IllegalArgumentException("DataPacks may only contain ONE of each data type! Duplicate=(" + d.getClass().toString() + ")");
			} else {
				this.data.add(d);
				seen.add(d.getClass());
			}
		}
		return this;
	}

	public Data[] getData() {
		return data.toArray(new Data[data.size()]);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Data> DataWrapper<T> getType(Class<T> type) {
		for (Data d: data) {
			if (type.isAssignableFrom(d.getClass())) {
				return new DataWrapper<T>((T) d);
			}
		}
		return new DataWrapper<T>(null);
	}
	
	public <T extends Data> boolean hasType(Class<T> type) {
		for (Data d: data) {
			if (type.isAssignableFrom(d.getClass())) {
				return true;
			}
		}
		return false;
	}
	
}
