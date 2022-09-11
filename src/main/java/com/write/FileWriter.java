package com.write;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * A file output stream that is the target to write the string and do the additional operations.
 * 
 */

public class FileWriter extends Writer {

	private FileOutputStream mFileStream;

	private String mFileName;

	/**
	 * Create a new file writer with the provided file name.
	 * 
	 * @param fileName the physical file name
	 * @throws IOException exception thrown if I/O error occurs.
	 */
	public FileWriter(String fileName) throws IOException {
		mFileName = fileName;
	}

	/**
	 * Write the provided array of characters to the stream.
	 *
	 * @param charBuffer Array of characters
	 * @throws IOException exception thrown if I/O error occurs.
	 */
	protected void write(char charBuffer[]) throws IOException {
		if (charBuffer.length == 0) {
			return;
		}
		if(mFileStream != null) {
			mFileStream.close();
		}
		mFileStream = new FileOutputStream(mFileName);
		mFileStream.write(String.valueOf(charBuffer).getBytes());
		mFileStream.flush();
	}

	/**
	 * Writes a string to the file stream.
	 *
	 * @param string String to be written
	 * @throws IOException If an I/O error occurs
	 */
	public Writer write(String string) throws IOException {
		return super.write(string);
	}

	/**
	 * Return the file's current content as a string.
	 */
	public String getString() throws IOException {
		String fileContent = null;
		if (mFileName == null) {
			throw new NullPointerException("File name is null");
		}

		File file = new File(mFileName);
		try (FileReader fr = new FileReader(file)) {
			char[] chars = new char[(int) file.length()];
			fr.read(chars);
			fileContent = String.valueOf(chars);
		} catch (IOException e) {
			throw e;
		}
		return fileContent;
	}

	/**
	 * Closes the file output  stream and releases the resources associated with it. If
	 * the stream is already closed then invoking this method has no effect.
	 *
	 * @throws IOException if an I/O error occurs
	 */
	public void close() throws IOException {
		mIsClosed = true;
		mFileStream.close();
	}

}
