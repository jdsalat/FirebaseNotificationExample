package com.thejavedsalat.notificationexample

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

/**
 * Created by Javed.Salat on 10/19/2017.
 */
class MyFirebaseInstanceIdService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        super.onTokenRefresh()

        val refreshedToken = FirebaseInstanceId.getInstance().token

        Log.d("", "Refreshed Token:: " + refreshedToken)
    }
}