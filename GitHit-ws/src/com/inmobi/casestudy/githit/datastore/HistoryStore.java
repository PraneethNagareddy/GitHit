package com.inmobi.casestudy.githit.datastore;

import java.util.List;

import com.inmobi.casestudy.githit.domain.HistoryRecord;

public interface HistoryStore {
	List<HistoryRecord> getFullHistory();
	List<HistoryRecord> getFullHistoryForUser(String userName);
	HistoryRecord getHistoryRecord(int id);
	boolean deleteHistoryRecord(int id);
	void addHistoryRecord(HistoryRecord historyRecord);
	Integer getNextKey();
}
