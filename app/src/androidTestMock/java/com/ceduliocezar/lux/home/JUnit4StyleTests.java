package com.ceduliocezar.lux.home;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.GridView;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.Movie;
import com.ceduliocezar.lux.movies.FakeMoviesServiceApiImpl;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import tools.fastlane.screengrab.Screengrab;
import tools.fastlane.screengrab.UiAutomatorScreenshotStrategy;
import tools.fastlane.screengrab.locale.LocaleTestRule;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Created by cedulio on 28/10/16.
 */

@RunWith(JUnit4.class)
public class JUnit4StyleTests {
    @ClassRule
    public static final LocaleTestRule localeTestRule = new LocaleTestRule();

    @Rule
    public ActivityTestRule<HomeActivity> homeActivityActivityTestRule
            = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setUp(){
        Screengrab.setDefaultScreenshotStrategy(new UiAutomatorScreenshotStrategy());
    }

    @Test
    public void clickOnMovie_opensMovieDetailUi() throws Exception {
        Screengrab.screenshot("beforeSelectmovie");

        onView(withText(FakeMoviesServiceApiImpl.FAKE_TITLE + 1)).perform(click());

        onView(withId(R.id.movie_overview)).check(matches(isDisplayed()));

        Screengrab.screenshot("afterSelectmovie");
    }

    @Test
    public void scrollMovieGrid_loadNextPage() throws Exception {
        Screengrab.screenshot("beforeLoadNextMovePage");

        onData(instanceOf(Movie.class))
                .inAdapterView(allOf(withId(R.id.movie_grid), isDisplayed()))
                .atPosition(19)
                .check(matches(isDisplayed()));

        onView(withId(R.id.movie_grid)).check(matches(hasNumberOfItems(40)));

        Screengrab.screenshot("afterLoadNextMovePage");

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
