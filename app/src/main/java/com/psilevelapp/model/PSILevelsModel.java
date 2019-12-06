package com.psilevelapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PSILevelsModel {

    @JsonProperty("region_metadata")
    private ArrayList<RegionMetaData> regionMetaData;
    @JsonProperty("items")
    private ArrayList<Items> items;
    @JsonProperty("api_info")
    private APIInfo apiInfo;

    public ArrayList<RegionMetaData> getRegionMetaData() {
        return regionMetaData;
    }

    public void setRegionMetaData(ArrayList<RegionMetaData> regionMetaData) {
        this.regionMetaData = regionMetaData;
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }

    public APIInfo getApiInfo() {
        return apiInfo;
    }

    public void setApiInfo(APIInfo apiInfo) {
        this.apiInfo = apiInfo;
    }

}
