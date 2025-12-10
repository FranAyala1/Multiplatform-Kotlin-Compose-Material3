package org.crow.data


import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.crow.model.ApiResponse
import org.crow.model.Game.AuthResponse
import org.crow.model.Game.GameDto
import org.crow.model.Page
import org.crow.model.RegisterRequest
import org.crow.model.ReviewCrowGames
import org.crow.model.UserDetailsDto
import org.crow.model.UserDto

expect val url:String

class DataRepository() {

    private val client = HttpClient(CIO){
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    suspend fun getGamesSet(): ApiResponse<List<GameDto>> {
        return withContext(Dispatchers.Default) {
            client.get(url + "recommendations").body<ApiResponse<List<GameDto>>>()
        }

    }

    suspend fun getGames(page: Int, size: Int): ApiResponse<Page<GameDto>> {
        return withContext(Dispatchers.Default) {
            client.get(url + "games/games"){
                url {
                    parameters.append("page", page.toString())
                    parameters.append("size", size.toString())
                }
            }.body<ApiResponse<Page<GameDto>>>()
        }
    }

    suspend fun getGamesFromSearchAndFilter(page: Int, size: Int,search:String): ApiResponse<Page<GameDto>> {
        return withContext(Dispatchers.Default) {
            client.get(url + "games/findGames"){
                url {
                    parameters.append("page", page.toString())
                    parameters.append("size", size.toString())
                    parameters.append("search", search)
                }
            }.body<ApiResponse<Page<GameDto>>>()
        }
    }

    suspend fun getReviewsFromSearchAndFilter(search:String): ApiResponse<Set<ReviewCrowGames>> {
        return withContext(Dispatchers.Default) {
            client.get(url + "games/findReviews"){
                url {
                    parameters.append("search", search)
                }
            }.body<ApiResponse<Set<ReviewCrowGames>>>()
        }
    }

    suspend fun getGame(id:Long): ApiResponse<GameDto> {
        return withContext(Dispatchers.Default) {
            client.get(url + "games/game"){
                url {
                    parameters.append("id", id.toString())
                }
            }.body<ApiResponse<GameDto>>()
        }

    }

    suspend fun postRating(rating: Int,gameId:Long,token: String ){
        withContext(Dispatchers.Default) {
            client.post(url + "rating"){
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                }
                url {
                    parameters.append("ratingNumber",rating.toString())
                    parameters.append("idGame",gameId.toString())
                }
            }
        }
    }

    suspend fun postReview(contenido: String,gameId:Long,token: String ){
        withContext(Dispatchers.Default) {
            client.post(url + "reviews"){
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                }
                url {
                    parameters.append("contenido",contenido)
                    parameters.append("idGame",gameId.toString())
                }
            }
        }
    }

    suspend fun getReviewsGame(gameId:Long):ApiResponse<Set<ReviewCrowGames>>{
        return withContext(Dispatchers.Default) {
            client.get(url+"games/reviewsGame"){
                url {
                    parameters.append("idGame",gameId.toString())
                }
            }.body<ApiResponse<Set<ReviewCrowGames>>>()
        }
    }

    suspend fun getReviewsUser(token:String):ApiResponse<Set<ReviewCrowGames>>{
        return withContext(Dispatchers.Default) {
            client.get(url+"reviews/reviewsUser"){
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                }
            }.body<ApiResponse<Set<ReviewCrowGames>>>()
        }
    }

    suspend fun getUser(token:String):ApiResponse<UserDetailsDto>{
       return withContext(Dispatchers.Default) {
            client.get(url+"user/details"){
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                }
            }.body<ApiResponse<UserDetailsDto>>()
        }
    }

    suspend fun login(user:UserDto):ApiResponse<AuthResponse>{
        return withContext(Dispatchers.Default) {
            client.post(url+"auth/login"){
                contentType(ContentType.Application.Json)
                setBody(user)
            }.body<ApiResponse<AuthResponse>>()
        }
    }
    suspend fun register(user:RegisterRequest):ApiResponse<AuthResponse>{
        return withContext(Dispatchers.Default) {
            client.post(url+"auth/register"){
                contentType(ContentType.Application.Json)
                setBody(user)
            }.body<ApiResponse<AuthResponse>>()
        }
    }
}