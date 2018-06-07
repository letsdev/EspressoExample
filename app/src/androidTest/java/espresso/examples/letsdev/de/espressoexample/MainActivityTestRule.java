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

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.lifecycle.ActivityLifecycleCallback;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.view.WindowManager;

public class MainActivityTestRule<T extends MainActivity> extends ActivityTestRule<T> implements ActivityLifecycleCallback{

    public MainActivityTestRule(Class<T> activityClass) {

        super(activityClass);
    }

    @Override
    protected void beforeActivityLaunched() {

        super.beforeActivityLaunched();

        ActivityLifecycleMonitorRegistry.getInstance().addLifecycleCallback(this);
    }

    @Override
    public void onActivityLifecycleChanged(Activity activity, Stage stage) {

        if (stage == Stage.PRE_ON_CREATE) {
            activity.getWindow()
                    .addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                            | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                            | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
        }
    }
}
