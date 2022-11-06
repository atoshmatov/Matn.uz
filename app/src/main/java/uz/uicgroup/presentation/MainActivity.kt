package uz.uicgroup.presentation

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.R
import uz.uicgroup.utils.ConnectivityReceiver
import uz.uicgroup.utils.Open

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main),
    ConnectivityReceiver.ConnectivityReceiverListener {
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
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

}