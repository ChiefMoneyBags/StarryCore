package me.chiefbeef.core.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serializer {
	
	private byte[] bytes;
	
	public Serializer(Serializable data) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutput os = new ObjectOutputStream(out);
		os.writeObject(data);
		bytes = out.toByteArray();
	}
	
	public Serializer(byte[] bytes) {
		this.bytes = bytes;
	}
	
	public byte[] asBytes() {
		return bytes;
	}
	
	public Serializable asObject() throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		ObjectInputStream is = new ObjectInputStream(in);
		return (Serializable) is.readObject();
	}

}
