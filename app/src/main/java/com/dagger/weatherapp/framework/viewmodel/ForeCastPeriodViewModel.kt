package com.dagger.weatherapp.framework.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dagger.core.data.ForeCastPeriodItem
import com.dagger.weatherapp.framework.di.ApplicationModule
import com.dagger.weatherapp.framework.di.DaggerViewModelComponent
import com.dagger.weatherapp.framework.model.UseCases
import com.dagger.weatherapp.framework.model.entity.ApiMainEntity
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

class ForeCastPeriodViewModel  (app: Application): AndroidViewModel(app) {

    val foreCastPeriodeList = MutableLiveData<List<ForeCastPeriodItem>>()
    private val disposable = CompositeDisposable()
    private val foreCastPeriodService = ForeCastPeriodService()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private var prefHelper = SharedPreferencesHelper(getApplication())
    private var refreshTime = 5 * 60 * 1000 * 1000 * 1000L

    @Inject
    lateinit var useCases : UseCases


    val saved =  MutableLiveData<Boolean>()
    val currentForeCastPeriodItem = MutableLiveData<ForeCastPeriodItem?>()

    init {

        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)

        getForeCastPeriod()
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

                        storageForeCastPeriodItem(foreCastPeriodItemResponse.properties.periods)
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(getApplication(),"Error !!${e.printStackTrace()}", Toast.LENGTH_SHORT).show()
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

    fun getForeCastPeriod() {
        coroutineScope.launch {
            val noteList = useCases.getAllForeCastPeriodItem()
            foreCastPeriodeList.postValue(noteList)
        }
    }


}