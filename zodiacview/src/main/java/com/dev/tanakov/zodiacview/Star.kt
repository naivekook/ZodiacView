/**
 * Copyright 16/08/21 Vladimir Tanakov
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dev.tanakov.zodiacview

internal data class Star(
    var x: Float = 0f,
    var y: Float = 0f,
    var dirX: Float = 0f,
    var dirY: Float = 0f,
    var size: Float = 0f,
    var connectedStars: MutableList<Star> = mutableListOf()
)
