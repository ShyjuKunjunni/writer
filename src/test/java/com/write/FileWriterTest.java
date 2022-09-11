package com.write;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.FileSystems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileWriterTest {
	
	private Writer mTestWriter = null;

	@BeforeEach
	public void beforeFunction() throws IOException {
		String path = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
		mTestWriter = new FileWriter(path + "\\src\\test\\resources\\TestFile.txt");
	}

	@DisplayName("Test write()")
	@Test
	void testWrite() throws IOException {
		assertEquals("Test String Check", mTestWriter.write("Test String Check").getString());
	}

	@DisplayName("Test toLowercase()")
	@Test
	void testToLowercase() throws IOException {
		assertEquals("test string", mTestWriter.write("Test String").toLowercase().getString());
	}

	@DisplayName("Test toUppercase()")
	@Test
	void testToUppercase() throws IOException {
		assertEquals("TEST STRING", mTestWriter.write("Test String").toUppercase().getString());
	}

	@DisplayName("Test replaceWord()")
	@Test
	void testReplaceWord() throws IOException {
		assertEquals("Test String S*****", mTestWriter.write("Test String Stupid").replaceWord("Stupid").getString());
	}

	@DisplayName("Test removeDuplicate()")
	@Test
	void testRemoveDuplicate() throws IOException {
		assertEquals("Test String is saved", mTestWriter.write("Test String is is saved").removeDuplicate().getString());
	}

}