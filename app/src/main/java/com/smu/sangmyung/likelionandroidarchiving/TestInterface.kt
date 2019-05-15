package com.smu.sangmyung.likelionandroidarchiving

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TestInterface {
    //Observable 사용
    // 장점 : Callback을 신경쓰지 않아도 된다 && 비즈니스로직과 프로그래밍을 분리
    @GET("/")
    fun getGithub(): Observable<Github>

}