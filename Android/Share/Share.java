/**
 * 
 * Phonegap share plugin for Android
 * Kevin Schaul 2011
 *
 */

package com.schaul.plugins.share;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;

public class Share extends Plugin {

	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) {
		try {
			JSONObject jo = args.getJSONObject(0);
			doSendIntent(jo.getString("title"), jo.getString("subject"), jo.getString("text")); 
			callbackContext.success();
		} catch (JSONException e) {
			callbackContext.error(e.getMessage());
		}
		return true;
	}
	
	private void doSendIntent(String title, String subject, String text) {
		Intent sendIntent = new Intent(android.content.Intent.ACTION_SEND);
		sendIntent.setType("text/plain");
		sendIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
		sendIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
		this.cordova.startActivityForResult(this, Intent.createChooser(sendIntent, title), 0);
	}

}
