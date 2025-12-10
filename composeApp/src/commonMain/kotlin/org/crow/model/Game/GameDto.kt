package org.crow.model.Game

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDto(
    @SerialName("id")
    var id: Long = 0,

    var type: String? = null,
    var name: String? = null,

    @SerialName("steam_appid")
    var steamAppid: Int = 0,

    @SerialName("required_age")
    var requiredAge: Int = 0,

    @SerialName("is_free")
    var isFree: Boolean = false,

    @SerialName("controller_support")
    var controllerSupport: String? = null,

    @SerialName("detailed_description")
    var detailedDescription: String? = null,

    @SerialName("about_the_game")
    var aboutTheGame: String? = null,

    @SerialName("short_description")
    var shortDescription: String? = null,

    @SerialName("supported_languages")
    var supportedLanguages: String? = null,

    @SerialName("header_image")
    var headerImage: String? = null,

    var background: String? = null,

    @SerialName("background_raw")
    var backgroundRaw: String? = null,

    var website: String? = null,

    @SerialName("pc_requirements")
    var pcRequirements: String? = null,

    @SerialName("mac_requirements")
    var macRequirements: String? = null,

    @SerialName("linux_requirements")
    var linuxRequirements: String? = null,

    var developers: List<String>? = null,
    var publishers: List<String>? = null,
    var packages: List<Int>? = null,

    var movies: Set<org.crow.model.Game.MovieDto>? = HashSet<org.crow.model.Game.MovieDto>(),

    @SerialName("package_groups")
    var packageGroups: Set<org.crow.model.Game.PackageGroupDto>? = HashSet<org.crow.model.Game.PackageGroupDto>(),

    var platforms: org.crow.model.Game.PlatformsDto? = null,
    var metacritic: org.crow.model.Game.MetacriticDto? = null,
    var categories: Set<org.crow.model.Game.CategoryDto>? = HashSet<org.crow.model.Game.CategoryDto>(),
    var genres: Set<org.crow.model.Game.GenreDto>? = HashSet<org.crow.model.Game.GenreDto>(),
    var screenshots: Set<org.crow.model.Game.ScreenshotDto>? = HashSet<org.crow.model.Game.ScreenshotDto>(),
    var recommendations: org.crow.model.Game.RecommendationsDto? = null,

    @SerialName("reviewsCrow")
    var reviewsCrow: Set<org.crow.model.ReviewCrowDto>? = null,

    @SerialName("covers")
    var coversGame:CoversDto?=null,

    @SerialName("rating_crow")
    var ratingCrow: Set<RatingCrowDto>?=null,

    var achievements: org.crow.model.Game.AchievementsDto? = null,

    @SerialName("release_date")
    var releaseDate: org.crow.model.Game.ReleaseDateDto? = null,

    @SerialName("support_info")
    var supportInfo: org.crow.model.Game.SupportInfoDto? = null,

    var dlc: List<Int>? = null,

    @SerialName("content_descriptors")
    var contentDescriptors: org.crow.model.Game.ContentDescriptorsDto? = null,

    @SerialName("price_overview")
    var priceOverview: org.crow.model.Game.PriceOverviewDto? = null,

    @SerialName("capsule_image")
    var capsuleImage: String? = null,

    var totalRatings: Int? = null,
    var averageRating: Double? = null
)