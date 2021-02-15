package no.hvl.dat153.oblig2;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import no.hvl.dat153.oblig2.main.activities.MainActivity;
import no.hvl.dat153.oblig2.main.activities.QuizActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class QuizActivityTest {

    @Rule
    public ActivityScenarioRule<QuizActivity> mActivityRule = new ActivityScenarioRule<QuizActivity>(
            QuizActivity.class);



    @Test
    public void startCorrectTest() throws InterruptedException {
        Thread.sleep(3000); //waiting so that the page loads
        String n = QuizActivity.pList.get(0).getName();
        onView(withId(R.id.textViewScore)).check(matches(withSubstring("Score: " + 0 + "/" + QuizActivity.pList.size())));
        onView(withId(R.id.guessText)).perform(typeText(n));
        onView(withId(R.id.guessButton)).perform(click());
        onView(withId(R.id.textViewScore)).check(matches(withSubstring("Score: " + 1 + "/" + QuizActivity.pList.size())));



    }

    @Test
    public void startWrongTest() throws InterruptedException {
        Thread.sleep(3000); //waiting so that the page loads
        String n = QuizActivity.pList.get(0).getName();
        onView(withId(R.id.textViewScore)).check(matches(withSubstring("Score: " + 0 + "/" + QuizActivity.pList.size())));
        onView(withId(R.id.guessText)).perform(typeText("WRONG NAME"));
        onView(withId(R.id.guessButton)).perform(click());
        onView(withId(R.id.textViewScore)).check(matches(withSubstring("Score: " + 0 + "/" + QuizActivity.pList.size())));

    }



}