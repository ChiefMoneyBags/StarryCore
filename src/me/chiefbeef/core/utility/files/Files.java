package me.chiefbeef.core.utility.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Files {

	public static void createConfig(InputStream stream, File file) throws IOException {
		byte[] buffer = new byte[stream.available()];
		stream.read(buffer);
		OutputStream outStream = new FileOutputStream(file);
		outStream.write(buffer);
		outStream.close();
	}

}
