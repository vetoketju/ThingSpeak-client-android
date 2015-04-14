package com.villevalta.thingspeakclient.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.villevalta.thingspeakclient.R;
import com.villevalta.thingspeakclient.activities.ChannelActivity;

/**
 * Created by villevalta on 14.4.2015.
 */
public class OpenChannelDialog extends DialogFragment {

	EditText mInputChannelId;
	EditText mInputChannelReadKey;

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = getActivity().getLayoutInflater();

		View v = inflater.inflate(R.layout.dialog_openchannel, null);

		mInputChannelId = (EditText) v.findViewById(R.id.input_channelId);
		mInputChannelReadKey = (EditText) v.findViewById(R.id.input_channelReadKey);


		builder.setTitle("Open Channel");
		builder.setView(v);

		builder.setPositiveButton("Open", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {

				int channelId = 0;
				try {
					channelId = Integer.parseInt(mInputChannelId.getText().toString());
				} catch (NumberFormatException e) {
					// ignore
				}

				if (channelId > 0) {
					Intent i = new Intent(getActivity().getBaseContext(), ChannelActivity.class);
					i.putExtra("id", channelId);
					if (mInputChannelReadKey.length() > 0) {
						i.putExtra("key", mInputChannelReadKey.getText().toString());
					}
					getActivity().startActivity(i);
				} else {
					Toast.makeText(getActivity(),"Invalid Channel ID",Toast.LENGTH_SHORT).show();
				}
			}
		});

		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});


		return builder.create();
	}


}
