package com.todayweather;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import com.todayweather.utils.Constants;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by kamran on 3/2/2018.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends InstrumentationTestCase {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);
    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        server = new MockWebServer();
        server.start();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        Constants.BASE_URL = server.url("/").toString();
    }

    @Test
    public void testResponseContainerIsShown() throws Exception {
        String fileName = "server_200_ok_response.json";
        Constants.CITY = "Tehran";

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().getContext(), fileName)));

        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);

        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.dataContainer)).check(matches(isDisplayed()));

    }

    @Test
    public void testRetryButtonShowsWhenError() throws Exception {
        String fileName = "server_404_not_found_response.json";
        Constants.CITY = "fake";

        server.enqueue(new MockResponse()
                .setResponseCode(404)
                .setBody(RestServiceTestHelper.getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().getContext(), fileName)));

        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);

        onView(withId(R.id.retryButton)).check(matches(isDisplayed()));
        onView(withText(R.string.FailedErrorMsg)).inRoot(withDecorView(
                not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

}