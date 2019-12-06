package com.psilevelapp.psilevel.dataprovider;

import com.psilevelapp.PSILevelApp;
import com.psilevelapp.model.PSILevelsModel;
import com.psilevelapp.network.RestClient;
import com.psilevelapp.utils.Utility;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PSILevelNetworkDataProvider implements PSILevelDataProvider {

    private static PSILevelDataProvider mInstance;
    private PSILevelServices mServices;

    private PSILevelNetworkDataProvider() {
        mServices = RestClient.getClient().create(PSILevelServices.class);
    }

    public static synchronized PSILevelDataProvider getInstance() {
        if (mInstance == null) {
            mInstance = new PSILevelNetworkDataProvider();
        }
        return mInstance;
    }

    @Override
    public Disposable getPSILevels(String dateTime, String date,
                                   final Consumer<PSILevelsModel> success,
                                   Consumer<Throwable> error) {

        if (Utility.canConnect(PSILevelApp.getContext())) {
            return getPSILevel(dateTime, date).subscribe(new Consumer<PSILevelsModel>() {
                @Override
                public void accept(PSILevelsModel psiLevelsModel) throws Exception {
                    success.accept(psiLevelsModel);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    throwable.printStackTrace();
                }
            });
        }
        return Observable.just(0).subscribe();
    }

    private Observable<PSILevelsModel> getPSILevel(String dateTime, String date) {
        return mServices.getPSILevels(dateTime, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
