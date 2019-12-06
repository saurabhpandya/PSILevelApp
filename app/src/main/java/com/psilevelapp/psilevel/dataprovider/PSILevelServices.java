package com.psilevelapp.psilevel.dataprovider;

import com.psilevelapp.model.PSILevelsModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.psilevelapp.constants.Constants.END_POINT;

public interface PSILevelServices {

    @GET(END_POINT)
    Observable<PSILevelsModel> getPSILevels(@Query("date_time") String dateTime,
                                            @Query("date") String date);

}
