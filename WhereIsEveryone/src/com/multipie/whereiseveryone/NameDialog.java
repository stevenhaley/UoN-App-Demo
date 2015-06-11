package com.multipie.whereiseveryone;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.google.common.base.Preconditions;

public class NameDialog extends DialogFragment {
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Preconditions.checkArgument(activity instanceof MainActivity);
	}


	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("New Person");
		final EditText editText = new EditText(getActivity());
		editText.setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PERSON_NAME);
		builder.setView(editText);

		builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				MainActivity activity = (MainActivity) getActivity();
				activity.onPersonCreated(editText.getText().toString());
			}
		});

		builder.setNegativeButton("Cancel", null);

		return builder.create();
	}
}
