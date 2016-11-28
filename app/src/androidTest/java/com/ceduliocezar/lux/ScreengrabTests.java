package com.ceduliocezar.lux;

/**
 * Created by cedulio on 28/10/16.
 */

//@RunWith(JUnit4.class)
//public class ScreengrabTests {
//    @ClassRule
//    public static final LocaleTestRule localeTestRule = new LocaleTestRule();
//
//    @Rule
//    public ActivityTestRule<HomeActivity> homeActivityActivityTestRule
//            = new ActivityTestRule<>(HomeActivity.class);
//
//    @Before
//    public void setUp(){
//        Screengrab.setDefaultScreenshotStrategy(new UiAutomatorScreenshotStrategy());
//    }
//
//    @Test
//    public void clickOnMovie_opensMovieDetailUi() throws Exception {
//        Screengrab.screenshot("beforeSelectmovie");
//
//        onData(is(instanceOf(Movie.class))).atPosition(0).perform(click());
//
//        onView(withId(R.id.movie_overview)).check(matches(isDisplayed()));
//
//        Screengrab.screenshot("afterSelectmovie");
//    }
//
//    @Test
//    public void scrollMovieGrid_loadNextPage() throws Exception {
//        Screengrab.screenshot("beforeLoadNextMovePage");
//
//        onData(instanceOf(Movie.class))
//                .inAdapterView(allOf(withId(R.id.movie_grid), isDisplayed()))
//                .atPosition(19)
//                .check(matches(isDisplayed()));
//
//        onView(withId(R.id.movie_grid)).check(matches(hasNumberOfItems(40)));
//
//        Screengrab.screenshot("afterLoadNextMovePage");
//
//    }
//}
