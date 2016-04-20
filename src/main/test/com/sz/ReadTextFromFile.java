package com.sz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ReadTextFromFile {

	public static void main(String[] args) {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(new File("1.txt").toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(lines.toString());
	}

}
