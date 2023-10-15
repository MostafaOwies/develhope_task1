package com.example.develhopetask1.data.repo

import com.example.develhopetask1.domain.model.MobileDomainModel
import kotlinx.coroutines.flow.Flow

interface IRepo {
    suspend fun getMobileData() : Flow<MobileDomainModel>

}