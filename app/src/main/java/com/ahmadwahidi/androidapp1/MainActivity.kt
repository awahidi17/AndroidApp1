package com.ahmadwahidi.androidapp1

import android.app.Activity
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Button
import android.widget.TextView

/**
 * Main screen for Daily Motivation.
 * Created by Ahmad Wahidi for MWD3B Android Development Assignment 3.
 */
class MainActivity : Activity() {

    // Each motivation connects the visible message, emoji, accessibility label, and background.
    private data class Motivation(
        val messageResource: Int,
        val emojiResource: Int,
        val emojiDescriptionResource: Int,
        val backgroundResource: Int
    )

    private lateinit var pageBackground: View
    private lateinit var motivationCard: View
    private lateinit var emojiText: TextView
    private lateinit var messageText: TextView
    private lateinit var tapCountText: TextView
    private lateinit var inspireButton: Button

    private val motivations = listOf(
        Motivation(R.string.message_1, R.string.emoji_sun, R.string.emoji_sun_description, R.drawable.background_sunrise),
        Motivation(R.string.message_2, R.string.emoji_rocket, R.string.emoji_rocket_description, R.drawable.background_ocean),
        Motivation(R.string.message_3, R.string.emoji_seedling, R.string.emoji_seedling_description, R.drawable.background_garden),
        Motivation(R.string.message_4, R.string.emoji_star, R.string.emoji_star_description, R.drawable.background_twilight),
        Motivation(R.string.message_5, R.string.emoji_mountain, R.string.emoji_mountain_description, R.drawable.background_sky),
        Motivation(R.string.message_6, R.string.emoji_light, R.string.emoji_light_description, R.drawable.background_warmth),
        Motivation(R.string.message_7, R.string.emoji_target, R.string.emoji_target_description, R.drawable.background_focus),
        Motivation(R.string.message_8, R.string.emoji_sparkles, R.string.emoji_sparkles_description, R.drawable.background_calm)
    )

    private val motivationSelector by lazy { MotivationSelector(motivations.size) }
    private var currentMotivationIndex = NO_SELECTION
    private var tapCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectViews()

        // Restore the displayed message and counter after a screen rotation or recreation.
        currentMotivationIndex = savedInstanceState?.getInt(KEY_CURRENT_INDEX, NO_SELECTION)
            ?: NO_SELECTION
        tapCount = savedInstanceState?.getInt(KEY_TAP_COUNT, 0) ?: 0

        if (currentMotivationIndex in motivations.indices) {
            displayMotivation(currentMotivationIndex, shouldAnimate = false)
        }
        updateTapCount()

        // This is the app's only button. Every tap presents a fresh motivation.
        inspireButton.setOnClickListener { button ->
            button.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            showNextMotivation()
        }
    }

    /** Finds the views once so the click handler remains clear and readable. */
    private fun connectViews() {
        pageBackground = findViewById(R.id.page_background)
        motivationCard = findViewById(R.id.motivation_card)
        emojiText = findViewById(R.id.emoji_text)
        messageText = findViewById(R.id.message_text)
        tapCountText = findViewById(R.id.tap_count_text)
        inspireButton = findViewById(R.id.inspire_button)
    }

    /** Selects a non-repeating message, updates the counter, and refreshes the screen. */
    private fun showNextMotivation() {
        currentMotivationIndex = motivationSelector.nextIndex(currentMotivationIndex)
        tapCount += 1
        displayMotivation(currentMotivationIndex, shouldAnimate = true)
        updateTapCount()
    }

    /** Applies one complete motivation theme to the screen. */
    private fun displayMotivation(index: Int, shouldAnimate: Boolean) {
        val motivation = motivations[index]

        pageBackground.setBackgroundResource(motivation.backgroundResource)
        emojiText.setText(motivation.emojiResource)
        emojiText.contentDescription = getString(motivation.emojiDescriptionResource)
        messageText.setText(motivation.messageResource)

        if (shouldAnimate) {
            animateMotivationCard()
        }
    }

    /** Uses a short fade-and-scale animation to make each tap feel responsive. */
    private fun animateMotivationCard() {
        motivationCard.animate().cancel()
        motivationCard.alpha = 0f
        motivationCard.scaleX = 0.94f
        motivationCard.scaleY = 0.94f
        motivationCard.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(ANIMATION_DURATION_MILLIS)
            .start()
    }

    /** Displays the correct singular or plural counter text. */
    private fun updateTapCount() {
        tapCountText.text = when (tapCount) {
            0 -> getString(R.string.tap_count_zero)
            1 -> getString(R.string.tap_count_single, tapCount)
            else -> getString(R.string.tap_count_multiple, tapCount)
        }
    }

    /** Saves user progress so the current state survives Activity recreation. */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_CURRENT_INDEX, currentMotivationIndex)
        outState.putInt(KEY_TAP_COUNT, tapCount)
        super.onSaveInstanceState(outState)
    }

    private companion object {
        const val KEY_CURRENT_INDEX = "current_motivation_index"
        const val KEY_TAP_COUNT = "tap_count"
        const val NO_SELECTION = -1
        const val ANIMATION_DURATION_MILLIS = 260L
    }
}
