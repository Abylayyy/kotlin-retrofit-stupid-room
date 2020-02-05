package kdo.one.stupidkotlinroom.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import kdo.one.stupidkotlinroom.MyRoomApplication
import kdo.one.stupidkotlinroom.data.model.CountryModel
import kdo.one.stupidkotlinroom.data.service.RestApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainRepository {
    val BASE_URL = "https://restcountries.eu/rest/v2/"
    val TAG = MainRepository::class.java.simpleName

    fun getCountries(): LiveData<List<CountryModel>> {
        return MyRoomApplication.database!!.countryDao().getAllCountries()
    }

    fun apiCallAndPut() {
        val gson = Gson()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()

        val restApi = retrofit.create<RestApi>(RestApi::class.java)

        restApi.getAllCountries().enqueue(object : Callback<List<CountryModel>> {
            override fun onResponse(
                call: Call<List<CountryModel>>,
                response: Response<List<CountryModel>>
            ) {
                when (response.code()) {
                    200 -> {
                        Thread(Runnable {
                            MyRoomApplication.database!!.countryDao().deleteAllCountries()
                            MyRoomApplication.database!!.countryDao().insertAllCountries(response.body()!!)
                        }).start()
                    }
                }
            }

            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
                Log.e(TAG, "Something went wrong")
            }

        })
    }
}