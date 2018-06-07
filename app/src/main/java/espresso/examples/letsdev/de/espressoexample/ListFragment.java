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
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListFragment extends Fragment implements ListFragmentAdapterListener {

    private RecyclerView mRecyclerView;
    private TextView mTitleTextView;
    private FloatingActionButton mFloatingActionButton;
    private ListFragmentAdapter mAdapter;
    private int mCurrentlySelectedItems;

    public ListFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        setupView(view);

        setupRecyclerView();
        setupFloatingActionButton();

        return view;
    }

    private void setupView(View view) {

        mRecyclerView = view.findViewById(R.id.list_fragment_recycler_view);
        mTitleTextView = view.findViewById(R.id.list_fragment_title_text_view);
        mFloatingActionButton = view.findViewById(R.id.list_fragment_floating_action_button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        getActivity().getSupportFragmentManager().popBackStack();
        return true;
    }

    private void setupRecyclerView() {

        mAdapter = new ListFragmentAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setupFloatingActionButton() {

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAdapter.addItemToList(new ListItem());
                mTitleTextView.setText(mCurrentlySelectedItems + "/" + mAdapter.getItemCount() + " items selected");
            }
        });
    }

    @Override
    public void onListUpdated(int numberOfCheckedItems, int numberOfItems) {

        mCurrentlySelectedItems = numberOfCheckedItems;
        mTitleTextView.setText(numberOfCheckedItems + "/" + numberOfItems + " items selected");
    }
}
