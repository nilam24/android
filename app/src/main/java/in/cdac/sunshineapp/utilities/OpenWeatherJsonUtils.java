package in.cdac.sunshineapp.utilities;

import android.content.ContentValues;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

/**
 * Created by Dell1 on 01/04/2017.
 */

public final class OpenWeatherJsonUtils {

    public static String[] getSimpleWeatherStringsFromJson(Context context,String forcastJsonStr) throws JSONException
    {
        final String OWM_LIST="List";
        final String OWM_TEMPEATURE="temp";
        final String OWM_MAX="max";
        final String OWM_MIN="min";
        final String OWM_WEATHER="weather";
        final String OWM_DESCRIPTION="main";
        final String OWM_MESSAGE_CODE="cod";

        String[] parsedWeatherData=null;
        JSONObject forecastJson=new JSONObject(forcastJsonStr);

        if(forecastJson.has(OWM_MESSAGE_CODE))
        {
            int errorCode=forecastJson.getInt(OWM_MESSAGE_CODE);

                    switch(errorCode)
                    {
                        case HttpURLConnection.HTTP_OK:
                            break;
                        case HttpURLConnection.HTTP_NOT_FOUND:
                            return  null;
                        default:
                            return  null;
                    }


        }


        JSONArray weatherArray=forecastJson.getJSONArray(OWM_LIST);
        parsedWeatherData=new String[weatherArray.length()];
        long localDate=System.currentTimeMillis();
        long utcDate=SunshineDateUtils.getLocalDateFromUTC(localDate);
        long startDay=SunshineDateUtils.normalizeDate(utcDate);

        for(int i=0;i<weatherArray.length();i++)
        {

            String date;
            String highAndLow;
            long dateTimeMillis;
            double high,low;
            String description;

            JSONObject weathrForecast=weatherArray.getJSONObject(i);


            dateTimeMillis=startDay+SunshineDateUtils.DAYS_IN_MILLIS * i;
            date=SunshineDateUtils.getFriendlyDateString(context,dateTimeMillis,false);

            JSONObject weatherObject=weathrForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            description=weatherObject.getString(OWM_DESCRIPTION);

            JSONObject temperatureObject=weathrForecast.getJSONObject(OWM_TEMPEATURE);
            high=temperatureObject.getDouble(OWM_MAX);
            low =temperatureObject.getDouble(OWM_MIN);
            highAndLow=SunshineWeatherUtils.formatHighLows(context,high,low);

            parsedWeatherData[i]=date+"-"+description+"-"+highAndLow;



        }
        return parsedWeatherData;


    }


    public  static ContentValues[] getFullWeatherDataFromJson(Context context,String forcastJsonStr)
    {
        return null;

    }





}
