package com.example.develhopetask1.data.model

import com.example.develhopetask1.domain.model.MobileDomainModel

fun MobileResponseDataModel.toDomainModel(): MobileDomainModel {
    return MobileDomainModel(name = this.name ?: "", brand = this.brand ?: "")
}