package uz.uicgroup.presentation

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.r0adkll.slidr.Slidr
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.R
import uz.uicgroup.data.local.SharedPref
import uz.uicgroup.utils.ConnectivityReceiver
import uz.uicgroup.utils.Open
import uz.uicgroup.utils.Theme

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main),
    ConnectivityReceiver.ConnectivityReceiverListener {
    private var shared: SharedPref? = null
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        Open.notInternet = isConnected
    }

    private fun registerConnectivityReceiver() {
        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerConnectivityReceiver()
        shared = SharedPref(this)
        if (shared!!.theme)
            Theme.goInLightMode()
        else
            Theme.goInDarkMode()
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

}