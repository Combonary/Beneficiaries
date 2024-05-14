package com.example.beneficiaries.di

import android.content.Context
import com.example.beneficiaries.data.repositories.BeneficiariesRepositoryImpl
import com.example.beneficiaries.domain.repository.BeneficiariesRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BeneficiariesLocalModule {
    @Provides
    @Singleton
    fun injectBeneficiariesRepository(@ApplicationContext context: Context,gson: Gson): BeneficiariesRepository {
        return BeneficiariesRepositoryImpl(gson, context)
    }

    @Provides
    @Singleton
    fun injectGson(): Gson {
        return GsonBuilder().create()
    }
}