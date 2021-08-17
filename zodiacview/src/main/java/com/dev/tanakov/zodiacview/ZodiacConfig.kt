package com.dev.tanakov.zodiacview

import androidx.annotation.ColorRes

internal const val DEFAULT_STAR_COUNT = 30
internal const val DEFAULT_STAR_SIZE_MIN = 10
internal const val DEFAULT_STAR_SIZE_MAX = 20
internal const val DEFAULT_RELATION_SIZE = 5
internal const val DEFAULT_SPEED = 0.7f
internal const val DEFAULT_DISTANCE = 200
internal const val DEFAULT_INTERACTION_ENABLED = false

data class ZodiacConfig(
    val starCount: Int = DEFAULT_STAR_COUNT,
    val starSizeMin: Int = DEFAULT_STAR_SIZE_MIN,
    val starSizeMax: Int = DEFAULT_STAR_SIZE_MAX,
    val relationSize: Int = DEFAULT_RELATION_SIZE,
    val speed: Float = DEFAULT_SPEED,
    val distance: Int = DEFAULT_DISTANCE,
    @ColorRes val colorBackground: Int = R.color.zv_default_color_background,
    @ColorRes val colorStar: Int = R.color.zv_default_color_star,
    @ColorRes val colorRelation: Int = R.color.zv_default_color_relation,
    val interactionEnabled: Boolean = DEFAULT_INTERACTION_ENABLED,
)
