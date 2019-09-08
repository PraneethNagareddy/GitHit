package com.inmobi.casestudy.githit.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.inmobi.casestudy.githit.caching.CacheManager;
import com.inmobi.casestudy.githit.datastore.HistoryStore;
import com.inmobi.casestudy.githit.datastore.StoreFactory;
import com.inmobi.casestudy.githit.domain.HistoryRecord;
import com.inmobi.casestudy.githit.domain.User;

public class GitHitService {

	private static String SEARCH_HISTORY = "SEARCH_HISTORY";
	private static String GIT_USERS_API_URL = "https://api.github.com/users/";
	private HistoryStore historyStore; 
	public GitHitService(StoreFactory storeFactory) {
		historyStore = storeFactory.getHistoryStore();
	}
	
	public JSONObject getGitUserDetails(String searchedByUserEmailId, String handle) throws Exception {
		
		String url = GIT_USERS_API_URL+handle;

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		
		if(response.getStatusLine().getStatusCode() != 200) {
			throw new Exception();
		}

		BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));
		JSONObject gitUserDetails = (JSONObject) new JSONParser().parse(rd);
		HistoryRecord hsitoryRecord = addSearchToHistory(searchedByUserEmailId, handle);
		updateSearchCache(searchedByUserEmailId, hsitoryRecord);
		return gitUserDetails;
	}

	private void updateSearchCache(String emailId, HistoryRecord hsitoryRecord) {
		
		List currentHistory = ((ArrayList) CacheManager.getCache(SEARCH_HISTORY).getEntry(emailId));
		if(currentHistory == null)
			CacheManager.getCache(SEARCH_HISTORY).putEntry(emailId, new ArrayList<HistoryRecord>(Arrays.asList(hsitoryRecord)));
		else {
			currentHistory.add(hsitoryRecord);
		}
	}
	
	public void deleteHistory(Integer historyRecordId) {
		if(historyRecordId == null) return;
		HistoryRecord historyRecord = historyStore.getHistoryRecord(historyRecordId);
		historyStore.deleteHistoryRecord(historyRecordId);
		((ArrayList) CacheManager.getCache(SEARCH_HISTORY).getEntry(historyRecord.getSearchedBy())).remove(historyRecord);
	}
	
	public List<HistoryRecord> getHistory(String emailID){
		if(emailID == null || emailID.isEmpty())
			return new ArrayList<HistoryRecord>();
		return (List<HistoryRecord>) CacheManager.getCache(SEARCH_HISTORY).getEntry(emailID);
	}
	
	private HistoryRecord addSearchToHistory(String searchedByUserEmailId, String handle) {
		Integer key = historyStore.getNextKey();
		HistoryRecord record = new HistoryRecord();
		record.setId(key);
		record.setSearchedBy(searchedByUserEmailId);
		record.setGitHandle(handle);
		historyStore.addHistoryRecord(record);
		return record;
	}
	
}
