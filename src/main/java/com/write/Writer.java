package com.write;

import java.io.IOException;

/**
 * This abstract class will have methods to write the string to the target and other operations 
 * to be performed on the content to be written. 
 * 
 * It is assumed that the strings to be written on to the target is not appended to the already written string,
 * instead it just write the string to the target and do the operations.
 * 
 */
public abstract class Writer implements Closeable {

	/**
	 * Temporary buffer used to hold writes of strings and single characters
	 */
	private char[] mWriteBuffer;

	/**
	 * count to mark the total characters in the temporary buffer
	 */
	private int mCount;

	protected boolean mIsClosed = false;

	/**
	 * Creates a new character-stream writer whose critical sections will
	 * synchronize on the writer itself.
	 */
	protected Writer() {
	}

	/**
	 * Writes a portion of an array of characters.
	 *
	 * @param buffer Array of characters
	 * @throws IOException If an I/O error occurs
	 */
	abstract protected void write(char buffer[]) throws IOException;

	/**
	 * Writes a string.
	 *
	 * @param string String to be written
	 * @throws IOException If an I/O error occurs
	 */
	public Writer write(String string) throws IOException {
		if (!mIsClosed) {
			mCount = string.length();
			mWriteBuffer = new char[mCount];
			string.getChars(0, mCount, mWriteBuffer, 0);
			write(mWriteBuffer);
		}
		return this;
	}

	/**
	 * Converts the string being written to lower case.
	 * 
	 * @throws IOException If an I/O error occurs
	 */
	public Writer toLowercase() throws IOException {
		if (!mIsClosed) {
			for (int index = 0; index < mCount; index++) {
				if (Character.isLetter(mWriteBuffer[index]) && Character.isUpperCase(mWriteBuffer[index])) {
					mWriteBuffer[index] = Character.toLowerCase(mWriteBuffer[index]);
				}
			}
			write(mWriteBuffer);
		}
		return this;
	}

	/**
	 * Converts the string being written to upper case.
	 * 
	 * @throws IOException If an I/O error occurs
	 */
	public Writer toUppercase() throws IOException {
		if (!mIsClosed) {
			for (int index = 0; index < mCount; index++) {
				if (Character.isLetter(mWriteBuffer[index]) && Character.isLowerCase(mWriteBuffer[index])) {
					mWriteBuffer[index] = Character.toUpperCase(mWriteBuffer[index]);
				}
			}
			write(mWriteBuffer);
		}
		return this;
	}

	/**
	 * Replace the word with the string having the first latter and the remaining
	 * letters as *.
	 * 
	 * @throws IOException If an I/O error occurs
	 */
	public Writer replaceWord(String word) throws IOException {
		if (!mIsClosed) {
			int length = word.length();
			char charBuffer[] = new char[length];
			word.getChars(0, length, charBuffer, 0);

			for (int index = 1; index < length; index++) {
				charBuffer[index] = '*';
			}
			mWriteBuffer = String.valueOf(mWriteBuffer).replace(word, String.valueOf(charBuffer)).toCharArray();
			write(mWriteBuffer);
		}
		return this;
	}

	/**
	 * Remove consecutive duplicate words.
	 * 
	 * @throws IOException If an I/O error occurs
	 */
	public Writer removeDuplicate() throws IOException {
		if (!mIsClosed) {
			String result = String.valueOf(mWriteBuffer).replaceAll("(?i)\\b([a-z]+)\\b(?:\\s+\\1\\b)+", "$1");
			mWriteBuffer = result.toCharArray();
			write(mWriteBuffer);
		}
		return this;
	}

	/**
	 * Closes this stream and releases any system resources associated with it. If
	 * the stream is already closed then invoking this method has no effect.
	 *
	 * @throws IOException if an I/O error occurs
	 */
	abstract public void close() throws IOException;

	/**
	 * Gets the content written to the writer.
	 * 
	 * @return string.
	 * @throws IOException
	 */
	abstract public String getString() throws IOException;

}