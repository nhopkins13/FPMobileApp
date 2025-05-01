package com.csci448.fpmobileapp.data

import androidx.annotation.StringRes
import com.csci448.fpmobileapp.R

enum class SelectedScreen(val stringRes: Int?) {
    NONE(R.string.app_name),
    STARTUP(null),
    LOGIN(R.string.label_login),
    SIGNUP(R.string.label_signup),
    HOME(R.string.label_home),
    SETTINGS(R.string.label_settings),
    SHOP(R.string.label_shop),
    SOCIAL(R.string.label_social),
    WARDROBE(R.string.label_wardrobe),
    TASKS(R.string.label_tasks),
    NEW_TASK(R.string.label_new_task),
    ARCHIVE(R.string.label_archive)
}
