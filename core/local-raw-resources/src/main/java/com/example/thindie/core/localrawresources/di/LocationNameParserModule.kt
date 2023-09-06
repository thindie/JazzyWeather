package com.example.thindie.core.localrawresources.di

import com.example.thindie.core.localrawresources.LocationNameParser
import com.example.thindie.core.localrawresources.locationnameparser.LocationNameParserImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface LocationNameParserModule{
    @Binds
    fun bindLocationNameParser(impl: LocationNameParserImpl): LocationNameParser
}