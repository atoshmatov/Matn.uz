package uz.uicgroup.presentation

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.uicgroup.R
import uz.uicgroup.data.local.SharedPref
import uz.uicgroup.presentation.screens.edit.dialog.NetWorkDialog
import uz.uicgroup.utils.ConnectivityReceiver
import uz.uicgroup.utils.Open
import uz.uicgroup.utils.Theme
import uz.uicgroup.utils.internetConnection.ConnectivityObserver
import uz.uicgroup.utils.internetConnection.NetworkConnectivityObserver

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main),
    ConnectivityReceiver.ConnectivityReceiverListener {
    private var shared: SharedPref? = null
    lateinit var connectivityObserver: ConnectivityObserver

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isConnected) {
            Open.openScreen.value = Unit
        } else
            Open.notInternet.value = Unit
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
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        lifecycleScope.launch {
            connectivityObserver.observer().collect {
                when (it) {
                    ConnectivityObserver.Status.UnAvailable -> {
                        showDialog()
                    }
                    ConnectivityObserver.Status.Lost -> {
                        showDialog()
                    }
                    ConnectivityObserver.Status.Losing -> {
                        showDialog()
                    }
                    else -> {

                    }
                }
            }
        }
        shared = SharedPref(this)
        if (shared!!.theme)
            Theme.goInDarkMode()
        else
            Theme.goInLightMode()
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    private fun showDialog() {
        val dialog = NetWorkDialog()
        dialog.show(supportFragmentManager, "")
    }
}