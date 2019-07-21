package com.amdocs.Avinash.resources.rest.interfaces;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amdocs.Avinash.resources.models.UserPreferences;

public interface UserPreferencesRest {

	@RequestMapping(value = "/preference", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<UserPreferences>> getPreference();

	@RequestMapping(value = "/preference", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Void> postPreference(@RequestHeader(value = "Content-Type", required = true) String contentType,
			@Valid @RequestBody UserPreferences userPreferences);
}
