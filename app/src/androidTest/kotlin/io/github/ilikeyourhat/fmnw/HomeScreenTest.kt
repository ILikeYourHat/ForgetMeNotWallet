package io.github.ilikeyourhat.fmnw

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.ilikeyourhat.fmnw.ui.screen.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(HomeActivity::class.java)

    @Test
    fun checkEmptyHomeScreen() {
        with(composeTestRule) {
            onNodeWithText("Forget-Me-Not Wallet")
                .assertExists()
            onNodeWithText("Add a new item by clicking the + button")
                .assertExists()
        }
    }

    @Test
    fun checkNavigationToNewNoteScreen() {
        with(composeTestRule) {
            onNodeWithContentDescription("Add new item")
                .performClick()
            onNodeWithText("Note")
                .performClick()
            onNodeWithText("Add new note")
                .assertExists()
        }
    }

    @Test
    fun checkNavigationToNewGroupScreen() {
        with(composeTestRule) {
            onNodeWithContentDescription("Add new item")
                .performClick()
            onNodeWithText("Group")
                .performClick()
            onNodeWithText("Add new group")
                .assertExists()
        }
    }
}
