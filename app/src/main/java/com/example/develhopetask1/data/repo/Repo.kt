package com.example.develhopetask1.data.repo

import com.example.develhopetask1.data.datasource.ILocalDataSource
import com.example.develhopetask1.data.datasource.LocalDataSource
import com.example.develhopetask1.data.model.toDomainModel
import com.example.develhopetask1.domain.model.MobileDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repo(
    private val localDataSource: ILocalDataSource = LocalDataSource.getLocalDataSource(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IRepo {
    companion object {
        @Volatile
        private var repo: IRepo? = null

        fun getRepoInstance() = run {
            repo ?: synchronized(this) {
                val temp = Repo()
                repo = temp
                temp
            }
        }
    }

    private var mobilesDomainModel = mutableListOf<MobileDomainModel>()

    override suspend fun getMobileData(): Flow<MobileDomainModel> {
        val mobilesDataModel = localDataSource.getData()
        for (mobileDataModel in mobilesDataModel) {
            mobilesDomainModel.add(mobileDataModel.toDomainModel())
        }
        return flow {
            for (mobileDomainModel in mobilesDomainModel) {
                emit(mobileDomainModel)
                delay(1000)
            }
        }.catch {
            // handle emitting error
        }.flowOn(dispatcher)
    }

}