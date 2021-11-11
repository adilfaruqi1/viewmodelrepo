package com.mythio.retrofitsample.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mythio.retrofitsample.network.GithubApi
import com.mythio.retrofitsample.network.GithubUser
import com.mythio.retrofitsample.responsemodel.VehicleMake
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private var _userData = MutableLiveData<VehicleMake>()
    val userData: LiveData<VehicleMake>
        get() = _userData

    init {
        _userData.value = null
    }



    fun getVehicles(){

        val api = GithubApi.retrofitService.getVehicleMakes()

        api.enqueue(object : Callback<VehicleMake> {
            override fun onFailure(call: Call<VehicleMake>, t: Throwable) {
                Log.d("TAG_TAG", "Failed :" + t.message)
            }

            override fun onResponse(call: Call<VehicleMake>, response: Response<VehicleMake>) {
                _userData.value = response.body()
            }
        })


    }



/*    fun getUserData(profile: String) {
        val api = GithubApi.retrofitService.getUserData(profile)

        api.enqueue(object : Callback<GithubUser> {
            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                Log.d("TAG_TAG", "Failed :" + t.message)
            }

            override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                _userData.value = response.body()
            }
        })
    }*/
}