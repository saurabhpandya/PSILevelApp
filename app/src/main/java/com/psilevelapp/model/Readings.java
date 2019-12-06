package com.psilevelapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Readings {
    @JsonProperty("o3_sub_index")
    private ReadingCount o3SubIndex;
    @JsonProperty("pm10_twenty_four_hourly")
    private ReadingCount pm10TwentyFourHourly;
    @JsonProperty("pm10_sub_index")
    private ReadingCount pm10SubIndex;
    @JsonProperty("co_sub_index")
    private ReadingCount coSubIndex;
    @JsonProperty("pm25_twenty_four_hourly")
    private ReadingCount pm25TwentyFourHourly;
    @JsonProperty("so2_sub_index")
    private ReadingCount so2SubIndex;
    @JsonProperty("co_eight_hour_max")
    private ReadingCount coEightHourMax;
    @JsonProperty("no2_one_hour_max")
    private ReadingCount no2OneHourMax;
    @JsonProperty("so2_twenty_four_hourly")
    private ReadingCount so2TwentyFourHourly;
    @JsonProperty("pm25_sub_index")
    private ReadingCount pm25SubIndex;
    @JsonProperty("psi_twenty_four_hourly")
    private ReadingCount psiTwentyFourHourly;
    @JsonProperty("o3_eight_hour_max")
    private ReadingCount o3EightHourMax;

    public ReadingCount getO3SubIndex() {
        return o3SubIndex;
    }

    public void setO3SubIndex(ReadingCount o3SubIndex) {
        this.o3SubIndex = o3SubIndex;
    }

    public ReadingCount getPm10TwentyFourHourly() {
        return pm10TwentyFourHourly;
    }

    public void setPm10TwentyFourHourly(ReadingCount pm10TwentyFourHourly) {
        this.pm10TwentyFourHourly = pm10TwentyFourHourly;
    }

    public ReadingCount getPm10SubIndex() {
        return pm10SubIndex;
    }

    public void setPm10SubIndex(ReadingCount pm10SubIndex) {
        this.pm10SubIndex = pm10SubIndex;
    }

    public ReadingCount getCoSubIndex() {
        return coSubIndex;
    }

    public void setCoSubIndex(ReadingCount coSubIndex) {
        this.coSubIndex = coSubIndex;
    }

    public ReadingCount getPm25TwentyFourHourly() {
        return pm25TwentyFourHourly;
    }

    public void setPm25TwentyFourHourly(ReadingCount pm25TwentyFourHourly) {
        this.pm25TwentyFourHourly = pm25TwentyFourHourly;
    }

    public ReadingCount getSo2SubIndex() {
        return so2SubIndex;
    }

    public void setSo2SubIndex(ReadingCount so2SubIndex) {
        this.so2SubIndex = so2SubIndex;
    }

    public ReadingCount getCoEightHourMax() {
        return coEightHourMax;
    }

    public void setCoEightHourMax(ReadingCount coEightHourMax) {
        this.coEightHourMax = coEightHourMax;
    }

    public ReadingCount getNo2OneHourMax() {
        return no2OneHourMax;
    }

    public void setNo2OneHourMax(ReadingCount no2OneHourMax) {
        this.no2OneHourMax = no2OneHourMax;
    }

    public ReadingCount getSo2TwentyFourHourly() {
        return so2TwentyFourHourly;
    }

    public void setSo2TwentyFourHourly(ReadingCount so2TwentyFourHourly) {
        this.so2TwentyFourHourly = so2TwentyFourHourly;
    }

    public ReadingCount getPm25SubIndex() {
        return pm25SubIndex;
    }

    public void setPm25SubIndex(ReadingCount pm25SubIndex) {
        this.pm25SubIndex = pm25SubIndex;
    }

    public ReadingCount getPsiTwentyFourHourly() {
        return psiTwentyFourHourly;
    }

    public void setPsiTwentyFourHourly(ReadingCount psiTwentyFourHourly) {
        this.psiTwentyFourHourly = psiTwentyFourHourly;
    }

    public ReadingCount getO3EightHourMax() {
        return o3EightHourMax;
    }

    public void setO3EightHourMax(ReadingCount o3EightHourMax) {
        this.o3EightHourMax = o3EightHourMax;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class ReadingCount {
        @JsonProperty("west")
        private int west;
        @JsonProperty("national")
        private int national;
        @JsonProperty("east")
        private int east;
        @JsonProperty("central")
        private int central;
        @JsonProperty("south")
        private int south;
        @JsonProperty("north")
        private int north;

        public int getWest() {
            return west;
        }

        public void setWest(int west) {
            this.west = west;
        }

        public int getNational() {
            return national;
        }

        public void setNational(int national) {
            this.national = national;
        }

        public int getEast() {
            return east;
        }

        public void setEast(int east) {
            this.east = east;
        }

        public int getCentral() {
            return central;
        }

        public void setCentral(int central) {
            this.central = central;
        }

        public int getSouth() {
            return south;
        }

        public void setSouth(int south) {
            this.south = south;
        }

        public int getNorth() {
            return north;
        }

        public void setNorth(int north) {
            this.north = north;
        }
    }

}
