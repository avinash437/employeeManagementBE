package com.amdocs.Avinash.resources.rest.interfaces;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amdocs.Avinash.resources.models.UserDetails;

public interface UserLoginRest {

	@RequestMapping(value = "/login", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<UserDetails> checkLogin(
			@RequestHeader(value = "Content-Type", required = true) String contentType,
			@Valid @RequestBody UserDetails userDetails) throws Exception;

	@RequestMapping(value = "/login/add", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Void> addUser(@RequestHeader(value = "Content-Type", required = true) String contentType,
			@Valid @RequestBody UserDetails userDetails);

	@RequestMapping(value = "/login/update", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Void> updateUser(@RequestHeader(value = "Content-Type", required = true) String contentType,
			@Valid @RequestBody UserDetails userDetails);
}
