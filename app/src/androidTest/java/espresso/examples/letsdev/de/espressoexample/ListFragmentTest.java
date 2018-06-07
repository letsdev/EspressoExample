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

import android.support.test.espresso.contrib.RecyclerViewActions;
import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static espresso.examples.letsdev.de.espressoexample.ChildClickViewAction.clickChildViewWithId;

public class ListFragmentTest extends BaseTest {

    @Before
    public void setup() {

        onView(withId(R.id.fragment_button_fragment_change_button)).perform(click());
    }

    @Test
    public void test_uiWasSetUpCorrectly_allItemsAreVisibleAndHaveCorrectValues() {

        onView(withId(R.id.list_fragment_floating_action_button)).check(matches(isDisplayed()));
        onView(withId(R.id.list_fragment_title_text_view)).check(matches(withText("0/0 items selected")));
    }

    @Test
    public void test_clickFAB_itemIsAddedToListTextViewUpdated() {

        onView(withId(R.id.list_fragment_floating_action_button)).perform(click());
        onView(withId(R.id.list_fragment_recycler_view)).perform(RecyclerViewActions.scrollToPosition(0));
        onView(withRecyclerView(R.id.list_fragment_recycler_view).atPosition(0)).check(
                matches(hasDescendant(withText("Item 0"))));
        onView(withId(R.id.list_fragment_title_text_view)).check(matches(withText("0/1 items selected")));
    }

    @Test
    public void test_addElementSelectElementCheckBox_shouldRefreshTextViewCorrectly() {

        onView(withId(R.id.list_fragment_floating_action_button)).perform(click());
        onView(withId(R.id.list_fragment_recycler_view)).perform(RecyclerViewActions.scrollToPosition(0));
        onView(withId(R.id.list_fragment_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.row_list_item_checkbox)));
        onView(withId(R.id.list_fragment_title_text_view)).check(matches(withText("1/1 items selected")));
    }

    @Test
    public void test_addElementSelectElementCheckBoxAddOtherItem_shouldRefreshTextViewCorrectly() {

        onView(withId(R.id.list_fragment_floating_action_button)).perform(click());
        onView(withId(R.id.list_fragment_recycler_view)).perform(RecyclerViewActions.scrollToPosition(0));
        onView(withId(R.id.list_fragment_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.row_list_item_checkbox)));
        onView(withId(R.id.list_fragment_floating_action_button)).perform(click());
        onView(withId(R.id.list_fragment_title_text_view)).check(matches(withText("1/2 items selected")));
    }

    @Test
    public void test_addElementSelectElementCheckBoxDeselectCheckBoxAddOtherItem_shouldRefreshTextViewCorrectly() {

        onView(withId(R.id.list_fragment_floating_action_button)).perform(click());
        onView(withId(R.id.list_fragment_floating_action_button)).perform(click());
        onView(withId(R.id.list_fragment_floating_action_button)).perform(click());
        onView(withId(R.id.list_fragment_recycler_view)).perform(RecyclerViewActions.scrollToPosition(0));
        onView(withId(R.id.list_fragment_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.row_list_item_checkbox)));

        onView(withId(R.id.list_fragment_title_text_view)).check(matches(withText("1/3 items selected")));
        onView(withId(R.id.list_fragment_floating_action_button)).perform(click());
        onView(withId(R.id.list_fragment_title_text_view)).check(matches(withText("1/4 items selected")));
        onView(withId(R.id.list_fragment_recycler_view)).perform(RecyclerViewActions.scrollToPosition(1));
        onView(withId(R.id.list_fragment_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.row_list_item_checkbox)));
        onView(withId(R.id.list_fragment_title_text_view)).check(matches(withText("2/4 items selected")));

        onView(withId(R.id.list_fragment_floating_action_button)).perform(click());
        onView(withId(R.id.list_fragment_title_text_view)).check(matches(withText("2/5 items selected")));
        onView(withId(R.id.list_fragment_recycler_view)).perform(RecyclerViewActions.scrollToPosition(1));
        onView(withId(R.id.list_fragment_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.row_list_item_checkbox)));
        onView(withId(R.id.list_fragment_title_text_view)).check(matches(withText("1/5 items selected")));
    }

    @Test
    public void test_pressBackButton_shouldReturnToButtonFragment(){

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
        onView(withId(R.id.fragment_button_counter_text_view)).check(matches(isDisplayed()));
    }
}
