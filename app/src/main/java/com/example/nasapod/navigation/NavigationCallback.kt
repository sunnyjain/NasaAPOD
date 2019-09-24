package com.example.nasapod.navigation

import android.os.Bundle

interface NavigationCallback {
    fun onSelect(id: Int, bundle: Bundle)
}