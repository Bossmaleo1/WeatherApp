package com.dagger.weatherapp.framework.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dagger.core.data.ForeCastPeriodItem
import com.dagger.weatherapp.framework.di.ApplicationModule
import com.dagger.weatherapp.framework.di.DaggerViewModelComponent
import com.dagger.weatherapp.framework.model.UseCases
import com.dagger.weatherapp.framework.model.entity.ApiMainEntity
import com.dagger.weatherapp.framework.model.entity.City
import com.dagger.weatherapp.framework.model.entity.ForeCastPeriodItemEntity
import com.dagger.weatherapp.framework.model.entity.ForeCastPeriodItemResponse
import com.dagger.weatherapp.framework.model.remotedata.ForeCastPeriodService
import com.dagger.weatherapp.framework.util.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForeCastPeriodViewModel(app: Application, city: City): AndroidViewModel(app) {

    val foreCastPeriodeList = MutableLiveData<List<ForeCastPeriodItem>>()
    private val disposable = CompositeDisposable()
    private val foreCastPeriodService = ForeCastPeriodService()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private var prefHelper = SharedPreferencesHelper(getApplication())
    private var refreshTime = 5 * 60 * 1000 * 1000 * 1000L

    @Inject
    lateinit var useCases : UseCases


    val saved =  MutableLiveData<Boolean>()

    val loading = MutableLiveData<Boolean>()

    init {

        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)

        //getForeCastPeriod()
        val path:String = ""+city.lat+","+city.long
        //fetchFromRemote(path)
        refresh(path)

    }


    private fun checkCacheDuration() {
        val cachePreference = prefHelper.getCacheDuration()

        try {
            val cachePreferenceInt = cachePreference?.toInt() ?: 5 * 60
            refreshTime = cachePreferenceInt.times(60*1000*1000*100L)
        }catch (e: NumberFormatException){
            e.printStackTrace()
        }
    }

    fun refreshBypassCache(path:String) {
        fetchFromRemote(path)
    }


    fun refresh(path: String) {
        checkCacheDuration()
        val updateTime = prefHelper.getUpdateTime()
       /* if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            fetchFromDatabase()
            Toast.makeText(getApplication(),"locally !!",Toast.LENGTH_LONG).show()
        } else {*/
            fetchFromRemote(path)
        //Toast.makeText
    }


    private fun fetchFromRemote(path: String) {

        loading.value = true

        disposable.add(
            foreCastPeriodService.getApiMainCall(path)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<ApiMainEntity>(){
                    override fun onSuccess(apiMainEntity: ApiMainEntity) {
                        /*here we launch our forecast api call with the url
                        who are get with our main apiCall */
                       //fetchRemoteForeCast(splitOurForeCastPath(apiMainEntity.properties.forecast))
                       Log.i("retrofit","/"+splitOurForeCastPath(apiMainEntity.properties.forecast))

                        fetchRemoteForeCast(splitOurForeCastPath(apiMainEntity.properties.forecast))
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        //if we have a network error we display with our cache
                        fetchFromDatabase()
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
        loading.value = true
        disposable.add(
            foreCastPeriodService.getForeCastPeriod(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<ForeCastPeriodItemResponse>(){
                    override fun onSuccess(foreCastPeriodItemResponse: ForeCastPeriodItemResponse) {
                        loading.value = false
                        storageForeCastPeriodItem(foreCastPeriodItemResponse.properties.periods)
                        fetchFromDatabase()
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        Log.i("retrofit","Error !!${e.printStackTrace()}")
                        loading.value = false
                    }

                })
        )
    }


    //this method save our ForeCastPeriod in our locale database
    fun saveForeCastPeriodItem(foreCastPeriodItem : ForeCastPeriodItem) {

        coroutineScope.launch {
            useCases.addForeCastPeriodItem(foreCastPeriodItem)
            saved.postValue(true)
        }
    }

    //this method remove our ForeCastPeriod  our locale database
    fun removeForeCastPeriodItem(foreCastPeriodItem : ForeCastPeriodItem) {
        coroutineScope.launch {
            useCases.removeForeCastPeriodItem(foreCastPeriodItem)
        }
    }

    private fun storageForeCastPeriodItem(foreCastPeriodItemEntities:List<ForeCastPeriodItemEntity>) {

        for(element in foreCastPeriodItemEntities){
            removeForeCastPeriodItem(element.toForeCastPeriodItem())
        }

        for(element in foreCastPeriodItemEntities){
            saveForeCastPeriodItem(element.toForeCastPeriodItem())
        }

        prefHelper.saveUpdateTime(System.nanoTime())
    }

    private fun foreCastPeriodRetrieved(foreCastPeriodItemEntities:List<ForeCastPeriodItem>){
        //foreCastPeriodeList.value = foreCastPeriodItemEntities
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

   private fun fetchFromDatabase() {
       loading.value = true
        coroutineScope.launch {
            val foreCastPeriodList = useCases.getAllForeCastPeriodItem()
            foreCastPeriodeList.postValue(foreCastPeriodList)
        }
    }


}