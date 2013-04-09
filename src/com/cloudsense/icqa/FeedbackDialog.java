package com.cloudsense.icqa;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * This is the class the makes up the Dialog box that pops
 * up when clicking the bubbles in the EditText box in the 
 * DetailedFeedbackFragment.
 */

public class FeedbackDialog extends DialogFragment {

	public FeedbackDialogListener mDialogListener;

	// For saving states
	public static final String ITEMS = "items";
	public static final String CHOSEN_ADJECTIVE = "chosen_adjective";

	/**
	 * The <code>FeedbackDialogListener</code> listens for events on the pop-up
	 * dialog. These events are,  the click on the Remove button and the click
	 * on the list items in the dialog.
	 */
	public interface FeedbackDialogListener {
		public void onDialogNegativeButtonClick(DialogInterface dialog,
				String item);

		public void onDialogListItemClick(String[] items, String item, int index);
	}

	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle("Finer feedback");

		savedInstanceState = getArguments();

		final String[] items;
		String[][] finerChoiceAdjectives = { { "Brighter", "Very Bright" },
				{ "Just fine", "Great", "I'm High" },
				{ "Colder", "Very cold", "Unbearable" },
				{ "Darker", "Very Dark", "Ghostly dark" },
				{ "Just fresh", "Very fresh" }, { "Smelly", "Irresistable" },
				{ "Cozy", "Really cozy", "Love my space." },
				{ "Bad mood", "Very annoying" }, { "Very warm", "Hot" },
				{ "Humid", "Very wet", "Dry", "Very Dry" },
				{ "Quite", "Noisy", "Very Noisy" } };

		final String item = savedInstanceState.getString(CHOSEN_ADJECTIVE);
		String[] keys = DetailedFeedbackFragment.mButtonAdjectives;

		if (item.equals(keys[0]))
			items = finerChoiceAdjectives[0];
		else if (item.equals(keys[1]))
			items = finerChoiceAdjectives[1];
		else if (item.equals(keys[2]))
			items = finerChoiceAdjectives[2];
		else if (item.equals(keys[3]))
			items = finerChoiceAdjectives[3];
		else if (item.equals(keys[4]))
			items = finerChoiceAdjectives[4];
		else if (item.equals(keys[5]))
			items = finerChoiceAdjectives[5];
		else if (item.equals(keys[6]))
			items = finerChoiceAdjectives[6];
		else if (item.equals(keys[7]))
			items = finerChoiceAdjectives[7];
		else if (item.equals(keys[8]))
			items = finerChoiceAdjectives[8];
		else if (item.equals(keys[9]))
			items = finerChoiceAdjectives[9];
		else if (item.equals(keys[10]))
			items = finerChoiceAdjectives[10];
		else
			items = null;
		builder.setItems(items, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				mDialogListener.onDialogListItemClick(items, item, which);

			}
		});

		builder.setNegativeButton("Remove",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						mDialogListener.onDialogNegativeButtonClick(dialog,
								item);
						dialog.dismiss();

					}
				});

		return builder.create();
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mDialogListener = (FeedbackDialogListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.getClass().toString()
					+ " should implement FeedbackDialogListener");
		}
	}

}
