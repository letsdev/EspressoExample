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

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import org.hamcrest.Matcher;

/**
 * View matcher necessary for clicking items inside a recycler view row.
 */
public class ChildClickViewAction {

    public static ViewAction clickChildViewWithId(final int resourceId) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(resourceId);
                v.performClick();
            }
        };
    }
}
