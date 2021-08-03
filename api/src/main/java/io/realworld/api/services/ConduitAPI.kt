    package io.realworld.api.services

    import io.realworld.api.models.requests.LoginRequest
    import io.realworld.api.models.responses.ArticlesResponse
    import retrofit2.Response
    import io.realworld.api.models.requests.SignupRequest
    import io.realworld.api.models.responses.UserResponse
    import retrofit2.http.*

    interface ConduitAPI {

        @POST("users")
        suspend fun signupUser(
            @Body userCreds: SignupRequest
        ): Response<UserResponse>

        @POST("users/login")
        suspend fun loginUser(
            @Body userCreds: LoginRequest
        ): Response<UserResponse>


        @GET("articles")
        suspend fun getArticles(
            @Query("author") author: String? = null,
            @Query("favourited") favourited: String?= null,
            @Query("tag") tags: String? =null
        ): Response<ArticlesResponse>

        @GET("articles/{slug}")
        suspend fun getArticleBySlug(
            @Path("slug") slug: String
        ): Response<ArticlesResponse>

        @GET("tags")
        suspend fun  getTags(): Response<ArticlesResponse>


    }