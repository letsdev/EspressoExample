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

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ListFragmentAdapter extends RecyclerView.Adapter implements ListFragmentViewHolderListener {

    private List<ListItem> mListItemList = new ArrayList<>();
    private ListFragmentAdapterListener mListener;

    public ListFragmentAdapter(ListFragmentAdapterListener listener) {

        mListener = listener;
    }

    public void addItemToList(ListItem itemToAdd) {

        mListItemList.add(itemToAdd);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_list_item, parent, false);
        viewHolder = new ListFragmentViewHolder(view, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ListFragmentViewHolder listFragmentViewHolder = (ListFragmentViewHolder) holder;

        ListItem listItem = mListItemList.get(position);
        listItem.setTitle("Item " + position);
        listFragmentViewHolder.applyListItem(listItem);
    }

    @Override
    public int getItemCount() {

        return mListItemList.size();
    }

    @Override
    public void onCheckChanged(int changedPosition, boolean newCheckStatus) {

        mListItemList.get(changedPosition).setCheckBoxIsChecked(newCheckStatus);
        mListener.onListUpdated(getNumberOfCheckedItems(), getItemCount());
    }

    private int getNumberOfCheckedItems() {

        int result = 0;
        for (ListItem listItem : mListItemList) {

            if (listItem.isCheckBoxIsChecked()) {
                result++;
            }
        }
        return result;
    }
}
