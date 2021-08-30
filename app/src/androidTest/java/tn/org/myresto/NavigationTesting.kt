package tn.org.myresto

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import tn.org.myresto.ui.permission.PermissionFragment

@RunWith(AndroidJUnit4::class)
class NavigationTesting {

    @Test
    fun fromPermissionToMap() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        val titleScenario =
            launchFragmentInContainer<PermissionFragment>(themeResId = R.style.Theme_MyResto)

        titleScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.main_nav)

            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(ViewMatchers.withId(R.id.enable_gps)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id, equalTo(R.id.restaurantMapFragment))
    }

}