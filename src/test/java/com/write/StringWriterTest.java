package com.write;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringWriterTest {
	
	private Writer mTestWriter = null;

	@BeforeEach
	public void beforeFunction() throws IOException {
		mTestWriter = new StringWriter();
	}

	@DisplayName("Test write()")
	@Test
	void testWrite() throws IOException {
		assertEquals("Test String", mTestWriter.write("Test String").getString());
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