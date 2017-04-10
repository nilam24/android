package in.cdac.sunshineapp.utilities;

import android.content.Context;
import android.text.format.DateUtils;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.TimerTask;

import in.cdac.sunshineapp.R;

/**
 * Created by Dell1 on 02/04/2017.
 */

public final class SunshineDateUtils {

    public static final long SECOND_IN_MILLIS=1000;
    public static final long MINUTE_IN_MILLIS=SECOND_IN_MILLIS * 60;
    public static final long HOUR_IN_MILLIS=MINUTE_IN_MILLIS * 60;
    public static final long DAYS_IN_MILLIS=HOUR_IN_MILLIS * 24;




     public  static long getDayNumber(long date){
         TimeZone tz=TimeZone.getDefault();
         long gmtOffset=tz.getOffset(date);
         return date+gmtOffset / DAYS_IN_MILLIS ;



    }

    public static long normalizeDate(long date){

        long retValNew = date / DAYS_IN_MILLIS * DAYS_IN_MILLIS;
        return retValNew;

    }

        public static long getLocalDateFromUTC(long utcDate){

            TimeZone tz=TimeZone.getDefault();
            long gmtOffset=tz.getOffset(utcDate);
            return utcDate-gmtOffset;
    }

       public static String getFriendlyDateString(Context context,long dateInMillis, boolean showFullDate){


           long localDate=getLocalDateFromUTC(dateInMillis);
           long dayNumber=getDayNumber(localDate);
           long currentDayNumber=getDayNumber(System.currentTimeMillis());

           if(dayNumber==currentDayNumber || showFullDate) {

               String dayName = getDayName(context, localDate);
               String readableDate = getReadableDateString(context, localDate);

               if (dayNumber - currentDayNumber < 2) {
                   String localizedDayName = new SimpleDateFormat("EEEE").format(localDate);
                   return readableDate.replace(localizedDayName, dayName);

               } else {
                   return readableDate;
               }

           }
               else if(dayNumber<currentDayNumber+7)
                 {

                     return getDayName(context,localDate);



                }

               else {

                        int flags = DateUtils.FORMAT_SHOW_DATE |DateUtils.FORMAT_NO_YEAR | DateUtils.FORMAT_ABBREV_ALL | DateUtils.FORMAT_SHOW_WEEKDAY ;


                        return DateUtils.formatDateTime(context,localDate,flags);


                     }


           }




    private  static String getReadableDateString(Context context,long timeInMillis){

          int flags=DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NO_YEAR | DateUtils.FORMAT_SHOW_WEEKDAY ;
        return DateUtils.formatDateTime(context,timeInMillis,flags);

    }

    private  static String getDayName(Context context,long dateInMills){

        long dayNumber=getDayNumber(dateInMills);
        long currentDayNumber=getDayNumber(System.currentTimeMillis());
                if(dayNumber==currentDayNumber)
                {
                    return context.getString(R.string.today);
                }

                else if(dayNumber==currentDayNumber+1)
                  {
                      return context.getString(R.string.tomorrow);
                  }

            else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");

                    return dateFormat.format(dateInMills);
                }
              }


}
