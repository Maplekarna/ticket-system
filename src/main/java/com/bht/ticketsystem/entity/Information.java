package com.bht.ticketsystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public class Information {

    @NotNull(message = "showing_id cannot be null")
    @JsonProperty("showing_id")
    Integer showingId;

    @Min(1)
    @JsonProperty("count")
    Integer count;

    @JsonProperty("version")
    Integer version;

    @JsonProperty("scheduleId")
    Integer scheduleId;




    public Integer getCount() {
        return count;
    }

    public Integer getShowingId() {
        return showingId;
    }

    public Integer getVersion() {return version;}

    public Integer getScheduleId() {
        return scheduleId;
    }


}
