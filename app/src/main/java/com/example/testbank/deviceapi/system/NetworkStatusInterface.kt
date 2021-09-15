package com.example.testbank.deviceapi.system

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.databinding.ObservableBoolean
import com.example.testbank.base.extension.systemService
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import javax.inject.Singleton

interface NetworkStatusInterface {
    val isLostNetwork: ObservableBoolean

    fun registerNetworkStatus()
    fun unregisterNetworkStatus()
}

@Singleton
class MyNetworkStatus @Inject constructor(
    @ApplicationContext private val context: Context,
) : NetworkStatusInterface, ConnectivityManager.NetworkCallback() {
    override val isLostNetwork = ObservableBoolean(true)
    private var count = AtomicInteger(0)

    private val networkRequest: NetworkRequest by lazy {
        NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
    }

    private val manager: ConnectivityManager? by lazy {
        context.systemService<ConnectivityManager>()
    }

    override fun registerNetworkStatus() {
        Timber.d("[NETWORK] REGISTER")
        manager?.registerNetworkCallback(networkRequest, this)
    }

    override fun unregisterNetworkStatus() {
        Timber.d("[NETWORK] UNREGISTER")
        manager?.unregisterNetworkCallback(this)
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)

        val available = count.incrementAndGet()
        Timber.i("[NETWORK] AVAILABLE $available")

        if (available > 0) {
            isLostNetwork.set(false)
        }
    }

    override fun onLost(network: Network) {
        super.onLost(network)

        val decrement = count.decrementAndGet()
        Timber.i("[NETWORK] LOST $decrement")

        if (decrement == 0) {
            isLostNetwork.set(true)
        }
    }
}