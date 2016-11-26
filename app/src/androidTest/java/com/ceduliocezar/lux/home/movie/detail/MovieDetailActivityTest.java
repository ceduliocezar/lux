package com.ceduliocezar.lux.home.movie.detail;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.home.RecyclerViewMatcher;
import com.ceduliocezar.lux.movie.detail.MovieDetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by TECBMCCS on 25/11/16.
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MovieDetailActivityTest {


    @Rule
    public ActivityTestRule<MovieDetailActivity> activityTestRule = new
            ActivityTestRule<MovieDetailActivity>(MovieDetailActivity.class) {
                @Override
                protected Intent getActivityIntent() {

                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();

                    Intent result = new Intent(targetContext, MovieDetailActivity.class);
                    result.putExtra(MovieDetailActivity.MOVIE_ID_PARAM, 0);

                    return result;
                }
            };

    @Test
    public void loadMovie_showOverview() {

        onView(withId(R.id.movie_overview))
                .check(matches(not(withText(""))));

    }

    @Test
    public void loadMovie_videos() {

        onView(withRecyclerView(R.id.videos_recycler).atPosition(0))
                .check(matches(hasDescendant(not(withText("")))));


    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }
}
