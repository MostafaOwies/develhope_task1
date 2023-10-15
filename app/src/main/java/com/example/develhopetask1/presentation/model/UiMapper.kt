package com.example.develhopetask1.presentation.model

import com.example.develhopetask1.R
import com.example.develhopetask1.domain.model.MobileDomainModel
import com.example.develhopetask1.presentation.model.BrandCategory
import com.example.develhopetask1.presentation.model.MobileUiModel

fun MobileDomainModel.toUiModel(): MobileUiModel {
    return MobileUiModel(name = this.name, brand = mapFirstLetterToBrandName(this.brand))
}

fun mapFirstLetterToBrandName(firstLetter: String): Int? {
    return when (firstLetter) {
        BrandCategory.IPHONE.firstLetter -> R.string.iphone
        BrandCategory.SAMSUNG.firstLetter -> R.string.samsung
        else -> null
    }
}