package com.multipie.whereiseveryone;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

import com.google.gson.Gson;

public class Server {
	private interface ServerInterface {
		@POST("/person_server")
		void updatePerson(@Body Person person, Callback<Person> callback);


		@GET("/person_server/{id}")
		void getPerson(@Path("id") Long personId, Callback<Person> callback);


		@GET("/person_server/all")
		void getAllPeople(Callback<List<Person>> callback);
	}

	private static ServerInterface service;


	public static void updatePerson(Person person, Callback<Person> callback) {
		ServerInterface service = getService();
		service.updatePerson(person, callback);
	}


	public static void getPerson(Long personId, Callback<Person> callback) {
		getService().getPerson(personId, callback);
	}


	public static void getAllPeople(Callback<List<Person>> callback) {
		getService().getAllPeople(callback);
	}


	private static synchronized ServerInterface getService() {
		if (service == null) {
			RestAdapter adapter = new RestAdapter.Builder().setEndpoint("https://multipie-dev2.appspot.com/")
					.setConverter(new GsonConverter(new Gson())).build();
			service = adapter.create(ServerInterface.class);
		}

		return service;
	}
}
