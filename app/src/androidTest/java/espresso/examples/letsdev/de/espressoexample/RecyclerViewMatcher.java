package espresso.examples.letsdev.de.espressoexample;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Fork of the RecyclerViewMatcher from https://github.com/dannyroa/espresso-samples
 *
 * @author Patrick Bacon
 * https://gist.github.com/baconpat/8405a88d04bd1942eb5e430d33e4faa2
 * <p>
 * This class implements a custom ViewMatcher.
 * This makes comparing elements of a recyclerView possible.
 */
public class RecyclerViewMatcher {

    private final int mRecyclerViewId;

    private final String CLASS_TAG = "RecyclerViewMatcher";

    public RecyclerViewMatcher(int recyclerViewId) {

        Log.d(CLASS_TAG, "RecyclerViewMatcher was created");
        this.mRecyclerViewId = recyclerViewId;
    }

    public Matcher<View> atPosition(final int position) {

        Log.d(CLASS_TAG, "atPosition() was called with position " + position);
        return atPositionOnView(position, -1);
    }

    public Matcher<View> atPositionOnView(final int position, final int targetViewId) {

        Log.d(CLASS_TAG,
                "atPositionOnView() was called with position " + position + " and targetViewId " + targetViewId);
        return new TypeSafeMatcher<View>() {
            Resources resources = null;
            View childView;

            public void describeTo(Description description) {

                Log.d(CLASS_TAG, "describeTo() was called with description " + description.toString());
                String idDescription = Integer.toString(mRecyclerViewId);
                if (this.resources != null) {
                    try {
                        idDescription = this.resources.getResourceName(mRecyclerViewId);
                    } catch (Resources.NotFoundException notFoundException) {
                        Log.e(CLASS_TAG, "resource name was not found");
                        idDescription = String.format("%s (resource name not found)", mRecyclerViewId);
                    }
                }

                description.appendText("RecyclerView with id: " + idDescription + " at position: " + position);
            }

            public boolean matchesSafely(View view) {

                Log.d(CLASS_TAG, "matchesSafely() was called");
                this.resources = view.getResources();

                if (childView == null) {
                    Log.d(CLASS_TAG, "Child view is null. Searching it.");
                    RecyclerView recyclerView = (RecyclerView) view.getRootView().findViewById(mRecyclerViewId);
                    if (recyclerView != null && recyclerView.getId() == mRecyclerViewId) {
                        Log.d(CLASS_TAG, "RecyclerView found. Getting view holder for item at position " + position);
                        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                        if (viewHolder != null) {
                            Log.d(CLASS_TAG, "View holder found. Saving it as child view.");
                            childView = viewHolder.itemView;
                        } else {
                            Log.d(CLASS_TAG, "View holder not found. Unable to save as a child view.");
                        }
                    } else {
                        Log.d(CLASS_TAG, "RecyclerView not found. Returning false");
                        return false;
                    }
                } else {
                    Log.d(CLASS_TAG, "Child view is not null. Skipping search.");
                }

                if (targetViewId == -1) {
                    Log.d(CLASS_TAG, "TargetViewId is -1. Checking if the current view is the child view.");
                    boolean result = view == childView;
                    Log.d(CLASS_TAG,
                            "Compared child view " + childView + " with current view " + view + ". Result: " + result);
                    return result;
                } else {
                    Log.d(CLASS_TAG, "TargetViewId: " + targetViewId + ". Try to find the child view by id.");
                    View targetView = childView.findViewById(targetViewId);
                    Log.d(CLASS_TAG,
                            "Comparing view " + view + " with targetView " + targetView + " and returning the result");
                    return view == targetView;
                }
            }
        };
    }
}

