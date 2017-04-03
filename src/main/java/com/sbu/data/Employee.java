package com.sbu.data;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * Created by nicholasgenco on 4/3/17.
 * StartDate DATE,
 HourlyRate INTEGER,
 */
public class Employee extends Person {

    @NotBlank(message = "start date cannot be blank")
    Date startDate;

    @NotBlank(message = "hourly rate cannot be blank")
    int hourlyRate;


    public Employee(){

    }

    public Employee(String ssn, String lastname, String firstname, String address, int zipcode, int telephone, Date startDate, int hourlyRate) {
    super(ssn,lastname,firstname,address,zipcode,telephone);
    this.startDate=startDate;
    this.hourlyRate=hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }
}
