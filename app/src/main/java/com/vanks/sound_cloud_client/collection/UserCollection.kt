package com.vanks.sound_cloud_client.collection

import com.vanks.sound_cloud_client.viewmodel.User
import java.util.*
import kotlin.collections.ArrayList

class UserCollection {
    val users: ArrayList<User> = ArrayList()

    override fun toString(): String {
        return "UserCollection(users=$users)"
    }
}