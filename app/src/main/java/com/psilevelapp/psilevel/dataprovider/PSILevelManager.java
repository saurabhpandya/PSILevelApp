package com.psilevelapp.psilevel.dataprovider;

import com.psilevelapp.model.PSILevelsModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class PSILevelManager {

    private CompositeDisposable compositeDisposable;
    private Disposable psiLevelDisposable;
    private PSILevelDataProvider psiLevelDataProvider;

    public PSILevelManager(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
        psiLevelDataProvider = PSILevelNetworkDataProvider.getInstance();
    }

    public void getPSILevels(String dateTime, String date,
                             Consumer<PSILevelsModel> success,
                             Consumer<Throwable> error) {
        psiLevelDisposable = psiLevelDataProvider.getPSILevels(dateTime, date, success, error);
        if (psiLevelDisposable != null) {
            compositeDisposable.add(psiLevelDisposable);
        }
    }

    public void clear() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }

    public void dispose() {
        if (compositeDisposable != null
                && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }

}
