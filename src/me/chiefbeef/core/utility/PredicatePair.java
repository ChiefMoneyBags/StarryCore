package me.chiefbeef.core.utility;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicatePair<T> {

	private T obj;
	private List<Predicate<T>> functions;
	
	public PredicatePair(T obj, Predicate<T> function) {
		this.functions = Arrays.asList(function);
	}
	
	public PredicatePair(T obj, List<Predicate<T>> functions) {
		this.functions = functions;
	}
	
	public boolean test() {
		for (Predicate<T> function: functions) {
			if (!function.test(obj)) {
				return false;
			}
		}
		return true;
	}

}
