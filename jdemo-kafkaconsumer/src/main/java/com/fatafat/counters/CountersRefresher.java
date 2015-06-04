package com.fatafat.counters;

import org.apache.commons.lang.time.StopWatch;

import com.fatafat.params.MatchingParams;

public class CountersRefresher implements Runnable {

	public CountersRefresher(int seconds) {
		super();
		MatchingParams.setTimeWindow(seconds);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		StopWatch sw = new StopWatch();
		sw.start();
		int seconds = 0;
		while (true) {
			seconds = (int) (sw.getTime() / 1000);
			if (seconds == MatchingParams.getTimeWindow()) {
				sw.reset();
				sw.start();
				MatchingCounters.refreshCountersMatrix();
			}
		}
	}
}
