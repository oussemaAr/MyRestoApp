package tn.org.data.local

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import tn.org.data.local.dao.RestaurantDao
import tn.org.data.local.entity.CategoryEntity
import tn.org.data.local.entity.LocationEntity
import tn.org.data.local.entity.RestaurantEntity

@RunWith(AndroidJUnit4::class)
class RestaurantDatabaseTest {
    private lateinit var db: RestaurantDatabase
    private lateinit var dao: RestaurantDao

    private val restaurantsList = arrayListOf(
        RestaurantEntity(
            "1", "name one",
            LocationEntity(
                "address one",
                12.123,
                12.123
            ),
            category = CategoryEntity(
                "category one",
                "icon one"
            )
        ),
        RestaurantEntity(
            "2", "name two",
            LocationEntity(
                "address two",
                12.123,
                12.123
            ),
            category = CategoryEntity(
                "category two",
                "icon two"
            )
        )
    )

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = RestaurantDatabase.getInstance(context = context)
        dao = db.restaurantDao()
    }

    @Test
    fun addRestaurant() = runBlocking {
        dao.insertRestaurant(restaurantsList.first())
        val list = dao.getAllRestaurants().first()
        assertThat(list.size, equalTo(1))
    }

    @Test
    fun addRestaurantList() = runBlocking {
        dao.insertRestaurantsList(restaurantsList)
        val list = dao.getAllRestaurants().first().toList()
        assertThat(list.size, equalTo(2))
    }

    @After
    fun closeDB() {
        db.close()
    }
}
