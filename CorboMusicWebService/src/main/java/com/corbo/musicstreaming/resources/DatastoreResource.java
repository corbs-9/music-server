package com.corbo.musicstreaming.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corbo.musicstreaming.model.DatastoreManager;

@RestController
@RequestMapping(value = "/datastore")
public class DatastoreResource {
	
	@Autowired
	private DatastoreManager datastoreManager;
	
	@RequestMapping(value = "/sync")
	public ResponseEntity<Void> syncDatastore() {
		datastoreManager.resyncDatastore();
		return ResponseEntity.noContent().build();
	}

}
