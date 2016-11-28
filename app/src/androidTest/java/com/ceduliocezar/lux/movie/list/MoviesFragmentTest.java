package com.ceduliocezar.lux.movie.list;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.GridView;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.movie.Movie;
import com.ceduliocezar.lux.home.HomeActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by cedulio on 27/11/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MoviesFragmentTest {

    @Rule
    public ActivityTestRule<HomeActivity> homeActivityActivityTestRule
            = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void clickOnMovie_opensMovieDetailUi() throws Exception {

        onData(is(instanceOf(Movie.class))).atPosition(0).perform(click());

        onView(withId(R.id.movie_overview)).check(matches(isDisplayed()));
    }

    @Test
    public void scrollMovieGrid_loadNextPage() throws Exception {

        onData(instanceOf(Movie.class))
                .inAdapterView(allOf(withId(R.id.movie_grid), isDisplayed()))
                .atPosition(19)
                .check(matches(isDisplayed()));

        onView(withId(R.id.movie_grid)).check(matches(hasNumberOfItems(40)));

    }

    @Test
    public void loadMovies_show_genres() throws Exception {

        onData(is(instanceOf(Movie.class))).atPosition(0).onChildView(withId(R.id.movie_genre))
                .check(matches(not(withText(""))));

    }

    public static Matcher hasNumberOfItems(final int expectedSize) {

        Matcher matcher = new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                return expectedSize == ((GridView) item).getCount();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(" expected size: " + expectedSize);
            }
        };

        return matcher;
    }

}
