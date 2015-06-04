package com.ligadata.twitterfeeds.kafkaproducer;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.files.utils.FileUtil;

public class Main {

	public static void main(String[] args) throws Exception {
		final File folder ;
		
		if (args != null) {
			folder = new File(args[0]);
		}else{
			folder = new File(GlobalParamaters.getFolderPath());			
		}
		
		List<String> list = FileUtil.listFilesForFolder(folder);
		ExecutorService executor = Executors.newFixedThreadPool(5);

		for (String filepath : list) {
			ReadFromFile f = new ReadFromFile(filepath);
			executor.execute(f);
		}

		executor.shutdown();

	}

}
