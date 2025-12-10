package org.crow.data

import org.crow.model.Game.GameDto

interface DataRepositoryInterface {
    suspend fun getMarsPhotos(): List<GameDto>
}
