package com.pp.viveafesta.data

import com.pp.viveafesta.domain.repository.PartyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindPartyRepository(
        partyRepositoryImpl: PartyRepositoryImpl
    ): PartyRepository
}