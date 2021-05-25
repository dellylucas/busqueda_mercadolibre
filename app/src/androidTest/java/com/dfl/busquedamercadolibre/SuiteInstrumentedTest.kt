package com.dfl.busquedamercadolibre

import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dfl.busquedamercadolibre.view.ui.MainActivity
import com.dfl.busquedamercadolibre.view.ui.SearchFragment
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class SuiteInstrumentedTest {


    @Test
    fun useAppContext() {
        val scenario: ActivityScenario<MainActivity> =
            ActivityScenario.launch(MainActivity::class.java, null)
        scenario.onActivity {
            it.setLoading(true)
        }
    }

    @Test
    fun testEventFragment() {
        val scenario = launchFragment<SearchFragment>()
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.recreate()
    }
}