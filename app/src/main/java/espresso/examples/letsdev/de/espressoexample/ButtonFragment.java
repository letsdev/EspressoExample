/*
 * Project: EspressoExample
 *
 * User: rkoesters
 * Date: 07.06.2018
 *
 * This code is copyright (c) 2018 let's dev GmbH & Co. KG
 * URL: https://www.letsdev.de
 * e-Mail: contact@letsdev.de
 */
package espresso.examples.letsdev.de.espressoexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ButtonFragment extends Fragment {

    private Button mCounterButton = null;
    private Button mFragmentChangeButton = null;
    private TextView mCounterTextView = null;
    private int mButtonClickCount = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_button, container, false);
        mCounterButton = view.findViewById(R.id.fragment_button_counter_button);
        mFragmentChangeButton = view.findViewById(R.id.fragment_button_fragment_change_button);
        mCounterTextView = view.findViewById(R.id.fragment_button_counter_text_view);
        setupButtons();

        return view;
    }

    private void setupButtons() {

        mCounterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mButtonClickCount++;
                mCounterTextView.setText("Number of Button Clicks: " + mButtonClickCount);
            }
        });

        mFragmentChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(getTag())
                        .replace(R.id.fragment_container, new ListFragment()).commit();
            }
        });
    }
}
