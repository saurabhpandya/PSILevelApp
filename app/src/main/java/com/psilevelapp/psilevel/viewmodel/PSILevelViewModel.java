package com.psilevelapp.psilevel.viewmodel;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.psilevelapp.BaseVIewModel;
import com.psilevelapp.model.PSILevelsModel;
import com.psilevelapp.model.Readings;
import com.psilevelapp.model.RegionMetaData;
import com.psilevelapp.psilevel.dataprovider.PSILevelManager;
import com.psilevelapp.utils.Utility;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class PSILevelViewModel extends BaseVIewModel {

    private final String TAG = PSILevelViewModel.class.getSimpleName();

    private CompositeDisposable compositeDisposable;
    private PSILevelManager psiLevelManager;

    public PSILevelViewModel() {
        compositeDisposable = new CompositeDisposable();
        psiLevelManager = new PSILevelManager(compositeDisposable);
    }

    /**
     * It will get data from API and pass data to Activity
     *
     * @param success
     * @param error
     */
    public void getPSILevels(final Consumer<PSILevelsModel> success,
                             final Consumer<Throwable> error) {

        String dateTime = Utility.returnDate("YYYY-MM-dd'T'HH:mm:ss'Z'");
        String date = Utility.returnDate("YYYY-MM-dd");

        Log.d(TAG, "dateTime::" + dateTime);
        Log.d(TAG, "date::" + date);

        psiLevelManager.getPSILevels(dateTime, date, new Consumer<PSILevelsModel>() {
            @Override
            public void accept(PSILevelsModel psiLevelsModel) throws Exception {
                success.accept(psiLevelsModel);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                error.accept(throwable);
            }
        });
    }

    /**
     * It will return HashMap for parsed regions
     */
    public HashMap<String, LatLng> getRegion(ArrayList<RegionMetaData> arrylstRegionMetaData) {
        HashMap<String, LatLng> regionData = new HashMap<>();
        if (arrylstRegionMetaData != null && arrylstRegionMetaData.size() > 0) {
            for (RegionMetaData regionMetaData : arrylstRegionMetaData) {
                LatLng latLng = new LatLng(regionMetaData.getLabelLocation().getLatitude(),
                        regionMetaData.getLabelLocation().getLatitude());
                regionData.put(regionMetaData.getName(), latLng);
            }
        }
        return regionData;
    }

    /**
     * It will return HashMap for parsed region readings
     */
    public HashMap<String, HashMap<String, Integer>> getRegionsReading(Readings regionsReading) {
        HashMap<String, HashMap<String, Integer>> regionReading = new HashMap<>();
        HashMap<String, Integer> westRegionReading = new HashMap<>();
        HashMap<String, Integer> eastRegionReading = new HashMap<>();
        HashMap<String, Integer> northRegionReading = new HashMap<>();
        HashMap<String, Integer> southRegionReading = new HashMap<>();
        HashMap<String, Integer> centralRegionReading = new HashMap<>();

        if (regionsReading != null) {

            setO3SubIndex(regionsReading.getO3SubIndex(),
                    westRegionReading, eastRegionReading, northRegionReading,
                    southRegionReading, centralRegionReading);

            setPm10TwentyFourHourly(regionsReading.getPm10TwentyFourHourly(),
                    westRegionReading, eastRegionReading, northRegionReading,
                    southRegionReading, centralRegionReading);

            setPm10SubIndex(regionsReading.getPm10SubIndex(),
                    westRegionReading, eastRegionReading, northRegionReading,
                    southRegionReading, centralRegionReading);

            setCoSubIndex(regionsReading.getCoSubIndex(),
                    westRegionReading, eastRegionReading, northRegionReading,
                    southRegionReading, centralRegionReading);

            setPm25TwentyFourHourly(regionsReading.getPm25TwentyFourHourly(),
                    westRegionReading, eastRegionReading, northRegionReading,
                    southRegionReading, centralRegionReading);

            setSo2SubIndex(regionsReading.getSo2SubIndex(),
                    westRegionReading, eastRegionReading, northRegionReading,
                    southRegionReading, centralRegionReading);

            setCoEightHourMax(regionsReading.getCoEightHourMax(),
                    westRegionReading, eastRegionReading, northRegionReading,
                    southRegionReading, centralRegionReading);

            setNo2OneHourMax(regionsReading.getNo2OneHourMax(),
                    westRegionReading, eastRegionReading, northRegionReading,
                    southRegionReading, centralRegionReading);

            setSo2TwentyFourHourly(regionsReading.getSo2TwentyFourHourly(),
                    westRegionReading, eastRegionReading, northRegionReading,
                    southRegionReading, centralRegionReading);

            setPm25SubIndex(regionsReading.getPm25SubIndex(),
                    westRegionReading, eastRegionReading, northRegionReading,
                    southRegionReading, centralRegionReading);

            setPsiTwentyFourHourly(regionsReading.getPsiTwentyFourHourly(),
                    westRegionReading, eastRegionReading, northRegionReading,
                    southRegionReading, centralRegionReading);

            setO3EightHourMax(regionsReading.getO3EightHourMax(),
                    westRegionReading, eastRegionReading, northRegionReading,
                    southRegionReading, centralRegionReading);

        }

        regionReading.put("West", westRegionReading);
        regionReading.put("East", eastRegionReading);
        regionReading.put("North", northRegionReading);
        regionReading.put("South", southRegionReading);
        regionReading.put("Central", centralRegionReading);

        return regionReading;
    }

    private void setO3SubIndex(Readings.ReadingCount o3SubIndex,
                               HashMap<String, Integer> westRegionReading,
                               HashMap<String, Integer> eastRegionReading,
                               HashMap<String, Integer> southRegionReading,
                               HashMap<String, Integer> northRegionReading,
                               HashMap<String, Integer> centralRegionReading) {

        String name = "O3 Sub Index";

        westRegionReading.put(name, o3SubIndex.getWest());
        eastRegionReading.put(name, o3SubIndex.getEast());
        southRegionReading.put(name, o3SubIndex.getSouth());
        northRegionReading.put(name, o3SubIndex.getNorth());
        centralRegionReading.put(name, o3SubIndex.getCentral());
    }

    private void setPm10TwentyFourHourly(Readings.ReadingCount pm10TwentyFourHourly,
                                         HashMap<String, Integer> westRegionReading,
                                         HashMap<String, Integer> eastRegionReading,
                                         HashMap<String, Integer> southRegionReading,
                                         HashMap<String, Integer> northRegionReading,
                                         HashMap<String, Integer> centralRegionReading) {

        String name = "Pm 10 Twenty Four Hourly";

        westRegionReading.put(name, pm10TwentyFourHourly.getWest());
        eastRegionReading.put(name, pm10TwentyFourHourly.getEast());
        southRegionReading.put(name, pm10TwentyFourHourly.getSouth());
        northRegionReading.put(name, pm10TwentyFourHourly.getNorth());
        centralRegionReading.put(name, pm10TwentyFourHourly.getCentral());
    }

    private void setPm10SubIndex(Readings.ReadingCount Pm10SubIndex,
                                 HashMap<String, Integer> westRegionReading,
                                 HashMap<String, Integer> eastRegionReading,
                                 HashMap<String, Integer> southRegionReading,
                                 HashMap<String, Integer> northRegionReading,
                                 HashMap<String, Integer> centralRegionReading) {

        String name = "Pm 10 Sub Index";

        westRegionReading.put(name, Pm10SubIndex.getWest());
        eastRegionReading.put(name, Pm10SubIndex.getEast());
        southRegionReading.put(name, Pm10SubIndex.getSouth());
        northRegionReading.put(name, Pm10SubIndex.getNorth());
        centralRegionReading.put(name, Pm10SubIndex.getCentral());
    }

    private void setCoSubIndex(Readings.ReadingCount coSubIndex,
                               HashMap<String, Integer> westRegionReading,
                               HashMap<String, Integer> eastRegionReading,
                               HashMap<String, Integer> southRegionReading,
                               HashMap<String, Integer> northRegionReading,
                               HashMap<String, Integer> centralRegionReading) {

        String name = "CO Sub Index";

        westRegionReading.put(name, coSubIndex.getWest());
        eastRegionReading.put(name, coSubIndex.getEast());
        southRegionReading.put(name, coSubIndex.getSouth());
        northRegionReading.put(name, coSubIndex.getNorth());
        centralRegionReading.put(name, coSubIndex.getCentral());
    }

    private void setPm25TwentyFourHourly(Readings.ReadingCount coSubIndex,
                                         HashMap<String, Integer> westRegionReading,
                                         HashMap<String, Integer> eastRegionReading,
                                         HashMap<String, Integer> southRegionReading,
                                         HashMap<String, Integer> northRegionReading,
                                         HashMap<String, Integer> centralRegionReading) {

        String name = "Pm 25 Twenty Four Hourly";

        westRegionReading.put(name, coSubIndex.getWest());
        eastRegionReading.put(name, coSubIndex.getEast());
        southRegionReading.put(name, coSubIndex.getSouth());
        northRegionReading.put(name, coSubIndex.getNorth());
        centralRegionReading.put(name, coSubIndex.getCentral());
    }

    private void setSo2SubIndex(Readings.ReadingCount so2SubIndex,
                                HashMap<String, Integer> westRegionReading,
                                HashMap<String, Integer> eastRegionReading,
                                HashMap<String, Integer> southRegionReading,
                                HashMap<String, Integer> northRegionReading,
                                HashMap<String, Integer> centralRegionReading) {

        String name = "so2 Sub Index";

        westRegionReading.put(name, so2SubIndex.getWest());
        eastRegionReading.put(name, so2SubIndex.getEast());
        southRegionReading.put(name, so2SubIndex.getSouth());
        northRegionReading.put(name, so2SubIndex.getNorth());
        centralRegionReading.put(name, so2SubIndex.getCentral());
    }

    private void setCoEightHourMax(Readings.ReadingCount coEightHourMax,
                                   HashMap<String, Integer> westRegionReading,
                                   HashMap<String, Integer> eastRegionReading,
                                   HashMap<String, Integer> southRegionReading,
                                   HashMap<String, Integer> northRegionReading,
                                   HashMap<String, Integer> centralRegionReading) {

        String name = "CO Eight Hour Max";

        westRegionReading.put(name, coEightHourMax.getWest());
        eastRegionReading.put(name, coEightHourMax.getEast());
        southRegionReading.put(name, coEightHourMax.getSouth());
        northRegionReading.put(name, coEightHourMax.getNorth());
        centralRegionReading.put(name, coEightHourMax.getCentral());
    }

    private void setNo2OneHourMax(Readings.ReadingCount no2OneHourMax,
                                  HashMap<String, Integer> westRegionReading,
                                  HashMap<String, Integer> eastRegionReading,
                                  HashMap<String, Integer> southRegionReading,
                                  HashMap<String, Integer> northRegionReading,
                                  HashMap<String, Integer> centralRegionReading) {

        String name = "NO2 One Hour Max";

        westRegionReading.put(name, no2OneHourMax.getWest());
        eastRegionReading.put(name, no2OneHourMax.getEast());
        southRegionReading.put(name, no2OneHourMax.getSouth());
        northRegionReading.put(name, no2OneHourMax.getNorth());
        centralRegionReading.put(name, no2OneHourMax.getCentral());
    }

    private void setSo2TwentyFourHourly(Readings.ReadingCount so2TwentyFourHourly,
                                        HashMap<String, Integer> westRegionReading,
                                        HashMap<String, Integer> eastRegionReading,
                                        HashMap<String, Integer> southRegionReading,
                                        HashMap<String, Integer> northRegionReading,
                                        HashMap<String, Integer> centralRegionReading) {

        String name = "SO2 Twenty Four Hourly";

        westRegionReading.put(name, so2TwentyFourHourly.getWest());
        eastRegionReading.put(name, so2TwentyFourHourly.getEast());
        southRegionReading.put(name, so2TwentyFourHourly.getSouth());
        northRegionReading.put(name, so2TwentyFourHourly.getNorth());
        centralRegionReading.put(name, so2TwentyFourHourly.getCentral());
    }

    private void setPm25SubIndex(Readings.ReadingCount pm25SubIndex,
                                 HashMap<String, Integer> westRegionReading,
                                 HashMap<String, Integer> eastRegionReading,
                                 HashMap<String, Integer> southRegionReading,
                                 HashMap<String, Integer> northRegionReading,
                                 HashMap<String, Integer> centralRegionReading) {

        String name = "Pm 25 Sub Index";

        westRegionReading.put(name, pm25SubIndex.getWest());
        eastRegionReading.put(name, pm25SubIndex.getEast());
        southRegionReading.put(name, pm25SubIndex.getSouth());
        northRegionReading.put(name, pm25SubIndex.getNorth());
        centralRegionReading.put(name, pm25SubIndex.getCentral());
    }

    private void setPsiTwentyFourHourly(Readings.ReadingCount psiTwentyFourHourly,
                                        HashMap<String, Integer> westRegionReading,
                                        HashMap<String, Integer> eastRegionReading,
                                        HashMap<String, Integer> southRegionReading,
                                        HashMap<String, Integer> northRegionReading,
                                        HashMap<String, Integer> centralRegionReading) {

        String name = "PSO Twenty Four Hourly";

        westRegionReading.put(name, psiTwentyFourHourly.getWest());
        eastRegionReading.put(name, psiTwentyFourHourly.getEast());
        southRegionReading.put(name, psiTwentyFourHourly.getSouth());
        northRegionReading.put(name, psiTwentyFourHourly.getNorth());
        centralRegionReading.put(name, psiTwentyFourHourly.getCentral());
    }

    private void setO3EightHourMax(Readings.ReadingCount O3EightHourMax,
                                   HashMap<String, Integer> westRegionReading,
                                   HashMap<String, Integer> eastRegionReading,
                                   HashMap<String, Integer> southRegionReading,
                                   HashMap<String, Integer> northRegionReading,
                                   HashMap<String, Integer> centralRegionReading) {

        String name = "O3 Eight Hour Max";

        westRegionReading.put(name, O3EightHourMax.getWest());
        eastRegionReading.put(name, O3EightHourMax.getEast());
        southRegionReading.put(name, O3EightHourMax.getSouth());
        northRegionReading.put(name, O3EightHourMax.getNorth());
        centralRegionReading.put(name, O3EightHourMax.getCentral());
    }

    @Override
    public void subScribe() {

    }

    @Override
    public void clear() {
        psiLevelManager.clear();
    }

    @Override
    public void dispose() {
        psiLevelManager.dispose();
    }
}
