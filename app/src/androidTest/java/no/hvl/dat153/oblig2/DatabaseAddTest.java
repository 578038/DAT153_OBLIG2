package no.hvl.dat153.oblig2;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import no.hvl.dat153.oblig2.R;

import no.hvl.dat153.oblig2.main.activities.AddActivity;
import no.hvl.dat153.oblig2.main.activities.DatabaseActivity;

import static android.app.Instrumentation.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DatabaseAddTest {

    @Rule
    public IntentsTestRule<AddActivity> aActivityTestRule = new IntentsTestRule<>(AddActivity.class);


    @Test
    public void startAddTest() throws InterruptedException {
        Thread.sleep(1000); //waiting so that the page loads


        int n = 3; //antall element i database ved start. Ligger 3 objekt inni ved start av app




        //Legge til bilde
        onView(withId(R.id.newPerson)).perform(typeText("NyPerson"), ViewActions.closeSoftKeyboard());


        //given
        Matcher<Intent> expectedIntent = allOf(
                hasAction(Intent.ACTION_PICK),
                hasData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        );

        ActivityResult activityResult = galeryActivity();
        intending(expectedIntent).respondWith(activityResult);

        //execute and verify
        onView(withId(R.id.browseButton)).perform(click());
        intended(expectedIntent);


        onView(withId(R.id.addButton)).perform(click());

        assertEquals(AddActivity.dbSize, n+1);


    }


    private ActivityResult galeryActivity() { //
        Resources resources = InstrumentationRegistry.getInstrumentation()
                .getTargetContext().getResources();

        Uri iUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(R.drawable.ginger) + "/" +
                resources.getResourceTypeName(R.drawable.ginger) + "/" +
                resources.getResourceEntryName(R.drawable.ginger)
        );

        //Uri iUri = Uri.parse("android.resource://no.hvl.dat153.oblig2/drawable/ginger.png");

        Intent resultIntent = new Intent();
        resultIntent.setData(iUri);
        return new ActivityResult(Activity.RESULT_OK, resultIntent);
    }


}