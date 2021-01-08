package me.chiefbeef.core.utility;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerPair<T> {

	private T obj;
	private List<Consumer<T>> functions;
	
	public ConsumerPair(T obj, Consumer<T> function) {
		this.functions = Arrays.asList(function);
	}
	
	public ConsumerPair(T obj, List<Consumer<T>> functions) {
		this.functions = functions;
	}
	
	public void accept() {
		for (Consumer<T> function: functions) {
			function.accept(obj);
		}
	}

}
