package com.ligadata.datasets;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fatafat.dao.impl.DatasetsDao;
import com.fatafat.dao.objs.DatasetObj;
import com.fatafat.dao.objs.GenericObj;
import com.ligadata.datasets.utils.ReadCSV;
import com.ligadata.parameters.GlobalParams;

public final class GlobalWordSets {

	// private static HashMap<String, HashMap<String, Integer>> subjectsDataSets
	// = new HashMap<String, HashMap<String, Integer>>();
	// private static HashMap<String, HashMap<String, Integer>>
	// industriesDataSets = new HashMap<String, HashMap<String, Integer>>();

	private static HashMap<String, HashMap<String, Integer>> subjectsDataSets = null;
	private static HashMap<String, HashMap<String, Integer>> industriesDataSets = null;

	private GlobalWordSets() {
	}

	static {
//		debug("static initializer started >> getSubjectsFiles");
		// for (String string : GlobalParams.getSubjectsFiles()) {
		// subjectsDataSets.put(string,
		// ReadCSV.loadTextFileToHashMap(GlobalParams.getSubjectsDir() +
		// string));
		// }
//		debug("static initializer started >>");
//		DataSetsWrapper wrap;
//		DatasetsDao dao = new DatasetsDao();
//		Connection con = dao.getConnection();
//		wrap = getDataSetsInMap(dao.selectAll(con));
//		subjectsDataSets = (HashMap<String, HashMap<String, Integer>>) wrap.getSubjects();
//		industriesDataSets = (HashMap<String, HashMap<String, Integer>>) wrap.getIndustries();
//		debug("static initializer finished >>");
//		debug("static initializer finished >> getSubjectsFiles");
//		debug("static initializer started >> getIndustriesFiles");
//		for (String string : GlobalParams.getIndustriesFiles()) {
//			industriesDataSets.put(
//					string,
//					ReadCSV.loadTextFileToHashMap(GlobalParams
//							.getIndustriesDir() + string));
//		}
//		debug("static initializer finished >> getIndustriesFiles");
	}

	private static void debug(String msg) {
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss SSS");
		System.out.println(msg + format.format(new Date()));
	}

	public static HashMap<String, HashMap<String, Integer>> getSubjectsDataSets() {
		// return subjectsDataSets;
		debug("getSubjectsDataSets started....");
		for (String string : GlobalParams.getSubjectsFiles())
			subjectsDataSets.put(
					string,
					ReadCSV.loadTextFileToHashMap(GlobalParams.getSubjectsDir()
							+ string));
		debug("getSubjectsDataSets finished....");
		return subjectsDataSets;
	}

	public static HashMap<String, HashMap<String, Integer>> getIndustriesDataSets() {
		// return industriesDataSets;
		debug("getIndustriesDataSets started....");
		for (String string : GlobalParams.getIndustriesFiles())
			industriesDataSets.put(
					string,
					ReadCSV.loadTextFileToHashMap(GlobalParams
							.getIndustriesDir() + string));
		debug("getIndustriesDataSets finished....");
		return industriesDataSets;
	}

	private static DataSetsWrapper getDataSetsInMap(List<GenericObj> datasets) {

		DataSetsWrapper wrapper = new DataSetsWrapper();
		Map subs = new HashMap<String, HashMap<String, Integer>>();
		Map inds = new HashMap<String, HashMap<String, Integer>>();
		DatasetObj obj = null;

		for (GenericObj genericObj : datasets) {
			if (genericObj instanceof DatasetObj) {
				obj = (DatasetObj) genericObj;
				if (obj.getType().toLowerCase().equals("subject"))
					subs.put(obj.getName(), obj.getDataset());
				else if (obj.getType().toLowerCase().equals("industry"))
					inds.put(obj.getName(), obj.getDataset());
			}
		}
		wrapper.setIndustries(inds);
		wrapper.setSubjects(subs);

		return wrapper;
	}
	// private static HashMap<String, String[]> subjectsDataSets = new
	// HashMap<String, String[]>();
	// private static HashMap<String, String[]> industriesDataSets = new
	// HashMap<String, String[]>();
	//
	// private GlobalWordSets() {
	// }
	//
	// static {
	// for (String string : GlobalParams.getSubjectsFiles()) {
	// subjectsDataSets.put(string,
	// ReadCSV.loadCSVFile(GlobalParams.getSubjectsDir() + string));
	// }
	// for (String string : GlobalParams.getIndustriesFiles()) {
	// industriesDataSets.put(string,
	// ReadCSV.loadCSVFile(GlobalParams.getIndustriesDir() + string));
	// }
	// }
	//
	// public static HashMap<String, String[]> getSubjectsDataSets() {
	// return subjectsDataSets;
	// }
	//
	// public static HashMap<String, String[]> getIndustriesDataSets() {
	// return industriesDataSets;
	// }

}
