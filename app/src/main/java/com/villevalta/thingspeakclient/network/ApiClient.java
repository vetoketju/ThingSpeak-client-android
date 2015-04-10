package com.villevalta.thingspeakclient.network;

import com.villevalta.thingspeakclient.BuildConfig;
import com.villevalta.thingspeakclient.model.Channel;

import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.*;

/**
 * Created by villevalta on 24.3.2015.
 */
public class ApiClient {

	// singleton instance of this client
	private static ThingSpeakApiInterface clientInstance;

	private static final String DEFAULT_APILOCATION = "https://api.thingspeak.com";
	private static final String ENDPOINT_CHANNELS = "channels";
	private static final String ENDPOINT_USERS = "users";
	private static final String ENDPOINT_FEEDS = "feeds";
	private static final String MARKUP = ".json";


	public static ThingSpeakApiInterface getInstance() {
		if (clientInstance == null) {
			RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(DEFAULT_APILOCATION)
					.setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.BASIC : RestAdapter.LogLevel.NONE)
					//.setConverter()
					.build();

			clientInstance = restAdapter.create(ThingSpeakApiInterface.class);
		}

		return clientInstance;
	}

	public interface ThingSpeakApiInterface {

		/*
		    page (integer) Page number to retrieve (optional)
			tag (string) Name of tag to search for (optional)
			username (string) Person's username that you want to search Channels for (optional) -> must be exact

			You can also search for Channels within a certain distance of a location by including the following location parameters:
			latitude (decimal) - Latitude of location in degrees. (optional)
			longitude (decimal) - Longitude of location in degrees. (optional)
			distance (decimal) - Distance in kilometers from location. (optional)
		*/

		// Channels

		@GET("/" + ENDPOINT_CHANNELS + "/" + "public" + MARKUP)
		void getPublicChannels(@Query("page") int page, Callback<PaginatedChannelResponce> callback);

		@GET("/" + ENDPOINT_CHANNELS + "/" + "public" + MARKUP)
		void getPublicChannelsByUsername(@Query("page") int page, @Query("username") String username, Callback<PaginatedChannelResponce> callback);

		@GET("/" + ENDPOINT_CHANNELS + "/" + "public" + MARKUP)
		void getPublicChannelsByTag(@Query("page") int page, @Query("tag") String tag, Callback<PaginatedChannelResponce> callback);

		@GET("/" + ENDPOINT_CHANNELS + "/" + "{id}" + MARKUP)
		void getPublicChannel(@Path("id") int id, Callback<Channel> callback);

		@GET("/" + ENDPOINT_CHANNELS + "/" + "{id}" + MARKUP)
		void getPrivateChannel(@Path("id") int id, Callback<Channel> callback);

		// Channel Feeds

		@GET("/" + ENDPOINT_CHANNELS + "/" +"{id}" +"/" + ENDPOINT_FEEDS + MARKUP)
		void getPublicChannelFeed(@Path("id") int id, @QueryMap Map<String, String> parameters, Callback<Channel> callback);

		@GET("/" + ENDPOINT_CHANNELS + "/" +"{id}" +"/" + ENDPOINT_FEEDS + MARKUP)
		void getPrivateChannelFeed(@Path("id") int id, @QueryMap Map<String, String> parameters, Callback<Channel> callback);

		// User


	}
}
