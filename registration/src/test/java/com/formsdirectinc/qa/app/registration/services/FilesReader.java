package com.formsdirectinc.qa.app.registration.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FilesReader {
	
	private static final String DEFAULT_OUTPUT_PATH = "src/test/resources/registration/output/%s.txt";
	
	public static String readFile(String fileName) throws IOException

	{

		File outputFile = new File(String.format(DEFAULT_OUTPUT_PATH, fileName));
		String outputFilePath = outputFile.getAbsolutePath();
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader(outputFilePath));
		try {
			StringBuilder sb = new StringBuilder();
			line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		}

		finally {
			br.close();
		}

	}
}
