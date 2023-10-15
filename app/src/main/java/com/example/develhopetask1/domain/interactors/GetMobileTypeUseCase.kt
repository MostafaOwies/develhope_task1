package com.example.develhopetask1.domain.interactors

import com.example.develhopetask1.domain.model.MobileDomainModel
import com.example.develhopetask1.data.repo.IRepo
import com.example.develhopetask1.data.repo.Repo
import kotlinx.coroutines.flow.Flow

class GetMobileTypeUseCase(private val repo : IRepo = Repo.getRepoInstance()) {

    suspend operator fun invoke() : Flow<MobileDomainModel> {
        return repo.getMobileData()
    }
}