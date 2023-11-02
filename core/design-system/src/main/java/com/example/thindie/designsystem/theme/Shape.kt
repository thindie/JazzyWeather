package com.example.thindie.designsystem.theme

import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val jazzyShapes = Shapes(
    extraSmall = AbsoluteCutCornerShape(5.dp),
    small = AbsoluteRoundedCornerShape(10.dp),
    medium = RoundedCornerShape(20.dp),
    large = RoundedCornerShape(40.dp),
    extraLarge = AbsoluteCutCornerShape(20.dp)
)
