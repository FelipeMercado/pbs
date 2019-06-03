package com.pbs.PBshop.Servicios

import android.content.ContentResolver
import android.net.Uri
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.pbs.PBshop.BuildConfig
import com.pbs.PBshop.R
import java.lang.Exception

class ServicioNotificaciones: FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        try{
            val title: String? = remoteMessage!!.notification!!.title
            val message: String? = remoteMessage!!.notification!!.body

            showNotification(title,message)

        }catch (e: Exception){
            Log.e("onMessageReceived","trono")
        }
    }

    fun showNotification(title: String?, message: String?) {
        val VIBRATE_PATTERN = longArrayOf(0, 500)

        var builder: NotificationCompat.Builder = NotificationCompat.Builder(this,"MyNotifications")
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_message_deep_orange_500_24dp)
            .setAutoCancel(true)
            .setColor(this.resources.getColor(R.color.colorPrimary))
            .setVibrate(VIBRATE_PATTERN)
            .setContentText(message)

        var managerCompat: NotificationManagerCompat = NotificationManagerCompat.from(this)
        managerCompat!!.notify(999,builder.build())
    }

    override fun onNewToken(token: String?) {
        Log.d("TAG", "Refreshed token: " + token!!)

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        sendRegistrationToServer(token)
    }
}