package com.umutates.izinmodule.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;


public class DateUtils {

    public static int getYearsBetweenCurrentDateAndGivenDate(LocalDate dateOfStart){
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateOfStart, currentDate).getYears();
    }


    public static int findNumberOfWeekendDaysBetweenDates(LocalDate startDate, LocalDate endDate) {
        int numberOfWeekendDays = 0;
        while (startDate.isBefore(endDate)) {
            if (startDate.getDayOfWeek() == DayOfWeek.SATURDAY || startDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                numberOfWeekendDays++;
            }
            startDate = startDate.plusDays(1);
        }
        return numberOfWeekendDays;
    }

    public static int findNumberOfDaysBetweenTwoDays(LocalDate startDate, LocalDate endDate) {
        return Math.toIntExact(ChronoUnit.DAYS.between(startDate, endDate));
    }

    public static int findLeavesAmountBetweenTwoDate(LocalDate startDate, LocalDate endDate){
       return findNumberOfDaysBetweenTwoDays(startDate, endDate) - findNumberOfWeekendDaysBetweenDates(startDate, endDate);
    }

}
