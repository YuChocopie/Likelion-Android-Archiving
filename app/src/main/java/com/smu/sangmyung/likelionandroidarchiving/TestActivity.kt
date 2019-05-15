package com.smu.sangmyung.likelionandroidarchiving

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_test.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)



        funGit()
    }
    @SuppressLint("CheckResult")
    fun funGit(){
        //retrofit 변수선언
        val retrofit = Retrofit.Builder()
            //받은 응답을 옵저버블 형태로 변환해준다.
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                // 서버에서 온 json 형식의 data를 파싱해서 받아옴
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com/")
            .build()

        val api = retrofit.create(TestInterface::class.java)
        api.getGithub()
            //subscribeOn : 옵저버블의 스케쥴러를 바꾸는 것.
            //스케쥴러는 RxJAVA 코드를 어느 쓰레드에서 실행해야 할 지 지정해줌
            // subscribeOn과 observeOn 함수를 지정해주면?? -> 데이터 흐름이 발생하는
            //  스레드와 io(출력) 쓰레드를 분리 할 수 있다.
            .subscribeOn(Schedulers.io())
            //이후 실행할 코드를 mainThread에서 실행 할 수 있다.
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result ->
                tvEmail.text = "이메일 부분${result.emails_url}"
                tvEmoticon.text = "emotion ${result.emojis_url}"
                tvEvent.text=result.events_url
            },{error ->
                error.printStackTrace()
                Log.e("에러났다..","error")

            },{
                //작업이 정상적으로 처리되지 않았을 때
                Log.e("에러났다..","complete")
            })

    }
}
