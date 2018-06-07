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

public class ListItem {

    private String mTitle;
    private boolean mCheckBoxIsChecked;

    public ListItem() {

    }

    public String getTitle() {

        return mTitle;
    }

    public void setTitle(String title) {

        mTitle = title;
    }

    public boolean isCheckBoxIsChecked() {

        return mCheckBoxIsChecked;
    }

    public void setCheckBoxIsChecked(boolean checkBoxIsChecked) {

        mCheckBoxIsChecked = checkBoxIsChecked;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof ListItem)) {
            return false;
        }

        ListItem listItem = (ListItem) o;

        if (mCheckBoxIsChecked != listItem.mCheckBoxIsChecked) {
            return false;
        }
        return mTitle != null ? mTitle.equals(listItem.mTitle) : listItem.mTitle == null;
    }

    @Override
    public int hashCode() {

        int result = mTitle != null ? mTitle.hashCode() : 0;
        result = 31 * result + (mCheckBoxIsChecked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {

        return (new StringBuilder("ListItem{").append("mTitle='")
                .append(mTitle)
                .append('\'')
                .append(", mCheckBoxIsChecked=")
                .append(mCheckBoxIsChecked)
                .append('}')).toString();
    }
}
