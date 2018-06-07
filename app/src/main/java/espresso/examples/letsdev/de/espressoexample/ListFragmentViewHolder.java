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

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class ListFragmentViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {

    private TextView mTextView;
    private CheckBox mCheckBox;
    private ListItem mListItem;
    private ListFragmentViewHolderListener mListener;

    public ListFragmentViewHolder(View itemView, ListFragmentViewHolderListener listener) {

        super(itemView);

        mListener = listener;
        mTextView = itemView.findViewById(R.id.row_list_item_text_view);
        mCheckBox = itemView.findViewById(R.id.row_list_item_checkbox);
        mCheckBox.setOnCheckedChangeListener(this);
    }

    public void applyListItem(ListItem listItem) {

        mListItem = listItem;
        mTextView.setText(mListItem.getTitle());
        mCheckBox.setChecked(mListItem.isCheckBoxIsChecked());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        mListener.onCheckChanged(getAdapterPosition(), isChecked);
    }
}
