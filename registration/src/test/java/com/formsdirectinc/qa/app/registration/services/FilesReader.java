package com.formsdirectinc.qa.app.registration.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FilesReader {
	
	private static final String DEFAULT_OUTPUT_PATH = "src/test/resources/registration/output/%s.txt";
	
	public static String readFile(String fileName)

	{

		File outputFile = new File(String.format(DEFAULT_OUTPUT_PATH, fileName));
		String outputFilePath = outputFile.getAbsolutePath();
		String line = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(outputFilePath));
			StringBuilder sb = new StringBuilder();
			line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			br.close();
			return sb.toString();
		}catch(IOException e){
			e.printStackTrace();
		}

		return null;

	}
}
