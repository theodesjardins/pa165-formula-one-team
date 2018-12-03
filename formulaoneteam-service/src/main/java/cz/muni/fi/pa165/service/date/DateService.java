package cz.muni.fi.pa165.service.date;

import java.util.Calendar;
import java.util.Date;

public interface DateService {

    Date getCurrentDate();

    Calendar createCalendarForDate(Date date);
}
