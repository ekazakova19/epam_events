package helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

public class DateManager {
    public static LocalDate todayDate = LocalDate.now();
    public static DateTimeFormatter EVENTDATE_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);


    public static boolean isDateInCurrentWeek(LocalDate date){
        LocalDate mondayOfCurrentWeek = todayDate.with(previousOrSame(MONDAY));
        LocalDate sundayOfCurrentWeek = todayDate.with(nextOrSame(SUNDAY));
        if(date.isBefore(todayDate)){
            return false;
        }
        else if((date.isEqual(mondayOfCurrentWeek) || date.isAfter(mondayOfCurrentWeek))
                && (date.isEqual(sundayOfCurrentWeek)||date.isBefore(sundayOfCurrentWeek))){
            return true;
        }
        return false;
    }
    public static boolean isDateBeforeThanToday(LocalDate date){
        return date.isBefore(todayDate);
    }
    public static String handleRangeEventDate(String eventDate){
        if(eventDate.contains("-")){
            return eventDate.substring(eventDate.indexOf("-")+1).trim();
        }
        else {
            return eventDate;
        }
    }
}
