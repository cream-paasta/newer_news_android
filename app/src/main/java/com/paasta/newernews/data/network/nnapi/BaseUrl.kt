package com.paasta.newernews.data.network.nnapi

class BaseUrl {
    companion object {
        const val NN_API_BASE_URL = "https://newer-news-server.paas-ta.org/api/"

        const val NN_API_POST_SIGN_UP = "signup"
        const val NN_API_POST_LOGIN = "login"

        const val NN_API_GET_NEWS = "issues"
        const val NN_API_PUT_LIKE = "action"
        const val NN_API_GET_USER_NEWS = "user_issue_lists"

        const val NN_API_GET_HOT_LOCATIONS = "gus"

        const val NN_API_CHAT = "posts"

        const val WEATHER_API_KEY = "395824e172d347d6132ede4a1a140566"
        const val WEATHER_API_BASE_URL = "http://api.openweathermap.org/data/2.5/"
        const val WEATHER_API_GET_WEATHER = "weather"
    }
}