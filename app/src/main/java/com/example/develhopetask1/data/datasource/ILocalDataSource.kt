package com.example.develhopetask1.data.datasource

import com.example.develhopetask1.data.model.MobileResponseDataModel

interface ILocalDataSource {
    suspend fun getData() : List<MobileResponseDataModel>
}