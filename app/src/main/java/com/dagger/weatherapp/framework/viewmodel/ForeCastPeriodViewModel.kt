package com.dagger.weatherapp.framework.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dagger.weatherapp.framework.model.entity.ApiMainEntity
import com.dagger.weatherapp.framework.model.entity.ForeCastPeriodItemEntity
import com.dagger.weatherapp.framework.model.entity.ForeCastPeriodItemResponse
import com.dagger.weatherapp.framework.model.entity.PropertiesEntity
import com.dagger.weatherapp.framework.model.remotedata.ForeCastPeriodService
import com.dagger.weatherapp.framework.util.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ForeCastPeriodViewModel  (app: Application): AndroidViewModel(app) {

    val foreCastPeriodeList = MutableLiveData<List<ForeCastPeriodItemEntity>>()
    private val disposable = CompositeDisposable()
    private val foreCastPeriodService = ForeCastPeriodService()

    private var prefHelper = SharedPreferencesHelper(getApplication())
    private var refreshTime = 5 * 60 * 1000 * 1000 * 1000L

    init {

        foreCastPeriodeList.value = arrayListOf(
            ForeCastPeriodItemEntity(
                4, "Friday Night",
                "2020-08-28T18:00:00-04:00", "2020-08-29T06:00:00-04:00",
                false, 76, "F",
                null, "8 mph", "SW",
                "https://api.weather.gov/icons/land/night/tsra,30?size=medium",
                "Chance Showers And Thunderstorms",
                "A chance of showers and thunderstorms. Mostly cloudy, with a low around 76. Southwest wind around 8 mph. Chance of precipitation is 30%. New rainfall amounts less than a tenth of an inch possible."
            )
        )

        fetchFromRemote()

    }


    private fun fetchFromRemote() {

        disposable.add(
            foreCastPeriodService.getApiMainCall("25.761681,-80.191788")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<ApiMainEntity>(){
                    override fun onSuccess(apiMainEntity: ApiMainEntity) {
                        Toast.makeText(getApplication(),"Succes 1!! ${splitOurForeCastPath(apiMainEntity.properties.forecast)}", Toast.LENGTH_SHORT).show()
                        /*here we launch our forecast api call with the url
                        who are get with our main apiCall */
                        fetchRemoteForeCast(splitOurForeCastPath(apiMainEntity.properties.forecast))
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(getApplication(),"Error !!${e.printStackTrace()}", Toast.LENGTH_SHORT).show()
                    }

                })
        )
    }

    private fun splitOurForeCastPath(path : String) : String {
        var newpath : String? = ""
        val tempPath = path.split("/")
        for(i in 3 until tempPath.size) {
            if(newpath.equals("")){
                newpath = tempPath[i]
            }else {
                newpath = newpath+"/"+tempPath[i]
            }

        }
        return newpath!!
    }


    private fun fetchRemoteForeCast(url : String) {
        disposable.add(
            foreCastPeriodService.getForeCastPeriod(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<ForeCastPeriodItemResponse>(){
                    override fun onSuccess(foreCastPeriodItemResponse: ForeCastPeriodItemResponse) {
                        Toast.makeText(getApplication(),"Succes !!", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(getApplication(),"Error !!${e.printStackTrace()}", Toast.LENGTH_SHORT).show()
                    }

                })
        )
    }


}