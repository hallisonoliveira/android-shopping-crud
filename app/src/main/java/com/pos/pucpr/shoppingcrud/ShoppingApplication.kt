package com.pos.pucpr.shoppingcrud

import android.app.Application
import com.pos.pucpr.shoppingcrud.di.createDomainModule
import com.pos.pucpr.shoppingcrud.di.createNetworkModule
import com.pos.pucpr.shoppingcrud.di.createRepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ShoppingApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ShoppingApplication)
            modules(
                createNetworkModule,
                createRepositoryModule,
                createDomainModule
            )
        }

    }

}