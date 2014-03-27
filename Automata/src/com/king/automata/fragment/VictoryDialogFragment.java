package com.king.automata.fragment;

import java.util.Locale;

import android.app.Activity;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.king.automata.GameActivity;
import com.king.automata.R;
import com.king.automata.data.LevelStatusCommands;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link VictoryDialogFragment.OnFragmentInteractionListener} interface to
 * handle interaction events. Use the {@link VictoryDialogFragment#newInstance}
 * factory method to create an instance of this fragment.
 * 
 */
public class VictoryDialogFragment extends DialogFragment {
	private static final String ARG_REALM = "REALM";
	private static final String ARG_LEVEL = "LEVEL";
	private static final String ARG_MOVES = "MOVES";
	private static final String ARG_MOVES_LEFT = "MOVES_LEFT";
	private static final String ARG_CELLS = "CELLS";
	private static final String ARG_EXPECTED_CELLS = "EXPECTED_CELLS";
	private static final String ARG_PROGRESS = "PROGRESS";
	
	private int realm;
	private int level;
	private int progress;
	private int movesLeft, moves, cells, expectedCells;
	private GameActivity gameActivity;
	

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment VictoryDialogFragment.
	 */
	public static VictoryDialogFragment newInstance(GameActivity gameActivity, 
			int realm, int level, int moves, int movesLeft, int cells, int expectedCells, int progress) {
		VictoryDialogFragment fragment = new VictoryDialogFragment();
		fragment.setGameActivity(gameActivity);
		Bundle args = new Bundle();
		args.putInt(ARG_REALM, realm);
		args.putInt(ARG_LEVEL, level);
		args.putInt(ARG_PROGRESS, progress);
		args.putInt(ARG_MOVES, moves);
		args.putInt(ARG_MOVES_LEFT, movesLeft);
		args.putInt(ARG_CELLS, cells);
		args.putInt(ARG_EXPECTED_CELLS, expectedCells);
		fragment.setArguments(args);
		return fragment;
	}

	public VictoryDialogFragment() {
		// Required empty public constructor
	}

	public void setGameActivity(GameActivity gameActivity) {
		this.gameActivity = gameActivity;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			realm = getArguments().getInt(ARG_REALM);
			level = getArguments().getInt(ARG_LEVEL);
			progress = getArguments().getInt(ARG_PROGRESS);
			moves = getArguments().getInt(ARG_MOVES);
			movesLeft = getArguments().getInt(ARG_MOVES_LEFT);
			cells = getArguments().getInt(ARG_CELLS);
			expectedCells = getArguments().getInt(ARG_EXPECTED_CELLS);
		}
		LevelStatusCommands.insertOrUpdate(gameActivity, realm, level, progress);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getDialog().setTitle("Level " + (realm + 1) + " - " + (level + 1));
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_victory_dialog, container,
				false);
		Button button = (Button)view.findViewById(R.id.victory_ok_button);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dismiss();
                gameActivity.processVictory();
            }
        });
        TextView topText = (TextView)view.findViewById(R.id.victory_text_one);
        String text_one = String.format(Locale.US, "You finished with %d out of %d moves remaining", 
        		moves, movesLeft);
        topText.setText(text_one);
        TextView bottomText = (TextView)view.findViewById(R.id.victory_text_two);
        String text_two = String.format(Locale.US, "You finished with %d out of %d total cells on the board", 
        		cells, expectedCells);
        bottomText.setText(text_two);
        RatingBar ratingBar = (RatingBar)view.findViewById(R.id.victory_rating_bar);
        ratingBar.setProgress(progress);
        return view;
	}
	
	public void onButtonPressed(Uri uri) {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}
}
