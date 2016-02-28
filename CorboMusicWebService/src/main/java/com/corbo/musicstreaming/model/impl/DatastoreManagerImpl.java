package com.corbo.musicstreaming.model.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.corbo.musicstreaming.database.Tasks;
import com.corbo.musicstreaming.model.DatastoreManager;

@Controller
public class DatastoreManagerImpl implements DatastoreManager {
	
	@Autowired
	private Tasks tasks;

	public void resyncDatastore() {
		tasks.synchroniseDataStore();
	}
}