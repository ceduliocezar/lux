package com.ceduliocezar.lux.presentation.movie.list;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.GridView;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.movie.Movie;
import com.ceduliocezar.lux.presentation.home.HomeActivity;
import com.ceduliocezar.lux.movies.FakeMoviesServiceApiImpl;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
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

        scrollToItem(19);
        checkNumberOfItems(40);
    }

    @Test
    public void loadMovies_show_genres() throws Exception {

        onData(is(instanceOf(Movie.class))).atPosition(0).onChildView(withId(R.id.movie_genre))
                .check(matches(not(withText(""))));

    }

    @Test
    public void refresh_movies() throws Exception {

        scrollToItem(19);
        checkNumberOfItems(40);
        scrollToItem(39);
        checkNumberOfItems(60);

        scrollToItem(0);
        pullToRefresh();

        checkNumberOfItems(20);
    }

    @Test
    public void reach_end_of_items() throws Exception {
        scrollToItem(19);
        scrollToItem(39);
        scrollToItem(59);
        scrollToItem(79);
        scrollToItem(99);

        checkNumberOfItems(FakeMoviesServiceApiImpl.MAX_NUM_PAGE * FakeMoviesServiceApiImpl.MOVIE_DB_PAGE_SIZE);
    }

    private void checkNumberOfItems(int expctedSize) {
        onView(withId(R.id.movie_grid)).check(matches(hasNumberOfItems(expctedSize)));
    }

    private void pullToRefresh() {
        onView(withId(R.id.swipeContainer))
                .perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(100)));
    }

    private void scrollToItem(int position) {
        onData(instanceOf(Movie.class))
                .inAdapterView(allOf(withId(R.id.movie_grid), isDisplayed()))
                .atPosition(position)
                .check(matches(isDisplayed()));
    }

    public static ViewAction withCustomConstraints(final ViewAction action, final Matcher<View> constraints) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return constraints;
            }

            @Override
            public String getDescription() {
                return action.getDescription();
            }

            @Override
            public void perform(UiController uiController, View view) {
                action.perform(uiController, view);
            }
        };
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
