package me.chiefbeef.core.customitem;

/**
 * ICustomItem is an interface that enables me to create multiple chains of inheritance
 * using interfaces as classes may only extend one parent class in java. It implements
 * the build method which has the same method signiture as {@link CustomItem#build()}
 * which basically turns the method into a constructor for the "interfaces" extending this
 * interface.
 * @author Kevin
 *
 */
public interface ICustomItem {

	CustomItem build();
	
}
