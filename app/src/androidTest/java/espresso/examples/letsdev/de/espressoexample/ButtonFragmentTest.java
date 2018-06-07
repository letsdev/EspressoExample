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

import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;

public class ButtonFragmentTest extends BaseTest {

    @Test
    public void test_uiHasSetupCorrectly_AllViewsShouldBeVisibleAndHaveCorrectValues() {

        onView(withId(R.id.fragment_button_counter_text_view)).check(matches(withText("Number of Button Clicks: 0")));
        onView(withId(R.id.fragment_button_counter_button)).check(matches(withText("Count")));
        onView(withId(R.id.fragment_button_fragment_change_button)).check(matches(withText("Change Fragment")));
    }

    @Test
    public void test_clickCounterButtonOnce_CounterTextViewCountsUp() {

        onView(withId(R.id.fragment_button_counter_text_view)).check(matches(withText("Number of Button Clicks: 0")));
        onView(withId(R.id.fragment_button_counter_button)).perform(click());
        onView(withId(R.id.fragment_button_counter_text_view)).check(matches(withText("Number of Button Clicks: 1")));
    }

    @Test
    public void test_clickCounterButtonTenTimes_CounterTextViewCountsUp() {

        onView(withId(R.id.fragment_button_counter_text_view)).check(matches(withText("Number of Button Clicks: 0")));
        int numberOfClicks = 10;
        for (int i = 0; i < numberOfClicks; i++) {
            onView(withId(R.id.fragment_button_counter_button)).perform(click());
        }
        onView(withId(R.id.fragment_button_counter_text_view)).check(matches(withText("Number of Button Clicks: 10")));
    }

    @Test
    public void test_clickChangeFragmentButton_FragmentChangesToListFragment() {

        onView(withId(R.id.fragment_button_fragment_change_button)).perform(click());
        onView(withId(R.id.list_fragment_floating_action_button)).check(matches(isDisplayed()));
    }
}
