package cz.muni.fi.pa165.service.date;

import cz.muni.fi.pa165.service.base.BaseTest;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class DateServiceImplTest extends BaseTest {

    @InjectMocks
    private DateServiceImpl service;

    @Test
    public void whenGetCurrentTime_returnsCurrentTime() {
        //given
        Date date = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();

        //then
        assertEquals(date, service.getCurrentDate());
    }

    @Test
    public void whenCreateCalendarForDate_returnsValidDate() {
        //given
        Date date = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);

        //then
        assertEquals(calendar, service.createCalendarForDate(date));
    }
}