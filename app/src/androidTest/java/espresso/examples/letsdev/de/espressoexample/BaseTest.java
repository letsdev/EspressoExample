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

import org.junit.Rule;

public class BaseTest {

    @Rule
    public MainActivityTestRule<MainActivity> mActivityRule = new MainActivityTestRule<>(MainActivity.class);


    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {

        return new RecyclerViewMatcher(recyclerViewId);
    }
}
