package com.villevalta.thingspeakclient.api;

import com.villevalta.thingspeakclient.api.model.Channel;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.*;

/**
 * Created by villevalta on 24.3.2015.
 */
public class ApiClient {

	// singleton instance of this client
	private static ThingSpeakApiInterface clientInstance;

	private static final String ENDPOINT_CHANNELS = "channels";
	private static final String ENDPOINT_USERS = "users";
	private static final String ENDPOINT_CHARTS = "charts";
	private static final String MARKUP = ".json";


	public static ThingSpeakApiInterface getInstance() {
		if (clientInstance == null) {
			RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint("https://api.thingspeak.com").setLogLevel(RestAdapter.LogLevel.FULL)
					//.setConverter()
					.build();

			clientInstance = restAdapter.create(ThingSpeakApiInterface.class);
		}

		return clientInstance;
	}

	public interface ThingSpeakApiInterface {

		/*
		    page (integer) Page number to retrieve (optional)
			tag (string) Name of tag to search for (optional) -> etsi by tag
			username (string) Person's username that you want to search Channels for (optional) -> must be exact

			You can also search for Channels within a certain distance of a location by including the following location parameters:
			latitude (decimal) - Latitude of location in degrees. (optional)
			longitude (decimal) - Longitude of location in degrees. (optional)
			distance (decimal) - Distance in kilometers from location. (optional)
		*/

		@GET("/" + ENDPOINT_CHANNELS + "/" + "public" + MARKUP)
		void getPublicChannels(@Query("page") int page, Callback<PaginatedResponce<Channel>> callback);

		@GET("/" + ENDPOINT_CHANNELS + "/" + "public" + MARKUP)
		void getPublicChannelsByUsername(@Query("page") int page, @Query("username") String username, Callback<List<Channel>> callback);

		@GET("/" + ENDPOINT_CHANNELS + "/" + "public" + MARKUP)
		void getPublicChannelsByTag(@Query("page") int page, @Query("tag") String tag, Callback<List<Channel>> callback);

		@GET("/" + ENDPOINT_CHANNELS + "/" + "{id}" + MARKUP)
		void getPublicChannel(@Path("id") int id, Callback<Channel> callback);

		@GET("/" + ENDPOINT_CHANNELS + "/" + "{id}" + MARKUP)
		void getPrivateChannel(@Path("id") int id, Callback<Channel> callback);

	}
}
