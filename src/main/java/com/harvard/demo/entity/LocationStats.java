package com.harvard.demo.entity;

import lombok.Data;

@Data
public class LocationStats {
    private String state;
    private String country;
    private int latestDayCases;
    private int diffFromPrevDay;
}
