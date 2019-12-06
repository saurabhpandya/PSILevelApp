package com.psilevelapp.psilevel.dataprovider;

import com.psilevelapp.model.PSILevelsModel;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public interface PSILevelDataProvider {

    Disposable getPSILevels(String dateTime, String date,
                            Consumer<PSILevelsModel> success,
                            Consumer<Throwable> error);

}
