package com.vaniala.movies.data.interceptor

import javax.inject.Inject

class TokenProviderImpl @Inject constructor() : TokenProvider {
    override fun getToken(): String = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiOTcwMDZiMDQ0MGNhMDZiO" +
        "WUwNjc0M2NlNDFiMDQyNiIsIm5iZiI6MTQyMTc3NzAwMC4zNTM5OTk5LCJzdWIiOiI1" +
        "NGJlOTg2OGMzYTM2ODFkYTAwMDQ2NmMiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6M" +
        "X0.Zgunnx4gxjuMRwvDadw5J3to6iKIYqM9fuWFl0hRnx4"
}
