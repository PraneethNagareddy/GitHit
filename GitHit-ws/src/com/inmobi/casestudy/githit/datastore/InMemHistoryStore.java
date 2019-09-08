package com.inmobi.casestudy.githit.datastore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inmobi.casestudy.githit.domain.HistoryRecord;
import com.inmobi.casestudy.githit.services.AuthenticationService;

public class InMemHistoryStore implements HistoryStore{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);
	Map<Integer, HistoryRecord> history = new HashMap<Integer, HistoryRecord>();
	public InMemHistoryStore(){

	}

	@Override
	public List<HistoryRecord> getFullHistory() {
		if(null == history) {
			LOGGER.error("Unable to retrieve products from the cache");
			return new ArrayList<HistoryRecord>();
		}else {
			return history.values().stream()
					.collect(Collectors.toList());
		}
	}

	@Override
	public List<HistoryRecord> getFullHistoryForUser(String userName) {
		List<HistoryRecord> fullHistory =  getFullHistory();
		return fullHistory.stream().
			filter(history -> history.getSearchedBy().equals(userName))
			.collect(Collectors.toList());
	}

	@Override
	public HistoryRecord getHistoryRecord(int id) {
		return history.get(id);
	}

	@Override
	public boolean deleteHistoryRecord(int id) {
		history.remove(id);
		return false;
	}

	@Override
	public void addHistoryRecord(HistoryRecord historyRecord) {
		history.put(historyRecord.getId(), historyRecord);
		
	}
	
	@Override
	public synchronized Integer getNextKey() {
		return history.size()+1;
	}

}
