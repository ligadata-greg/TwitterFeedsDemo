package com.files.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	
	public static List<String> listFilesForFolder(final File folder) {
		List<String> filePath = new ArrayList();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				filePath.add(fileEntry.getPath());
			}
		}
		return filePath;
	}
	
}
