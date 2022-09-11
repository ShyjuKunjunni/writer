package com.write;

import java.io.IOException;

/**
 * A character stream that collects its input in a string buffer, which can
 * then be used to construct a string.
 */

public class StringWriter extends Writer {

	private StringBuffer mBuffer;

	/**
	 * Create a new string writer.
	 */
	public StringWriter() {
		mBuffer = new StringBuffer();
	}

	/**
	 * Write the array of characters.
	 *
	 * @param charBuffer Array of characters
	 */
	protected void write(char charBuffer[]) throws IOException {
		if (charBuffer.length == 0) {
			return;
		}
		mBuffer.setLength(0);
		mBuffer.append(charBuffer);
	}

	/**
	 * Writes a string to the string buffer.
	 *
	 * @param string String to be written
	 * @throws IOException If an I/O error occurs
	 */
	public Writer write(String string) throws IOException {
		return super.write(string);
	}

	/**
	 * Return the buffer's current value as a string.
	 */
	public String getString() throws IOException {
		return mBuffer.toString();
	}

	/**
	 * Closing the write has no effect. The methods in this class can
	 * be called after the stream has been closed without generating an IOException.
	 */
	public void close() throws IOException {
		mIsClosed = true;
	}

}
