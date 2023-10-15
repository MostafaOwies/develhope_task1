package com.example.develhopetask1.data.datasource

import com.example.develhopetask1.data.model.MobileResponseDataModel

class LocalDataSource : ILocalDataSource {

    companion object {
        @Volatile
        private var localDataSource: ILocalDataSource? = null

        fun getLocalDataSource() = run {
            localDataSource ?: synchronized(this) {
                val temp = LocalDataSource()
                localDataSource = temp
                temp
            }
        }
    }



    override suspend fun getData(): List<MobileResponseDataModel> {
        return listOf(
            MobileResponseDataModel(name = "S23+", price = 12000, brand = "Samsung"),
            MobileResponseDataModel(name = "iphone 14 pro max", price = 20000, brand = "IPhone"),
        )
    }
}