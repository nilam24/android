package in.cdac.sunshineapp.data;

import android.content.Context;

import java.util.StringTokenizer;

/**
 * Created by Dell1 on 02/04/2017.
 */

public class SunshinePreferences {

    public static final String PREF_CITY_NAME="city_name";
    public static final String PREF_COORD_LAT="coord_lat";
    public static final String PREF_COORD_LONG="coord_long";
    private static final String DEFAULT_WEATHER_LOCATION="94043,USA";
    private  static final double[] DEFAULT_WEATHER_COORDINATES={37.4284, 122.0724};
    private static final String DEFAULT_MAP_LOCATION="1600 Amphitheatre Parkway, Mountain View, CA 94043";


    static public  void setLocationDetails(Context context,String cityName,Double lat,Double lon)
    {

    }

    static public  void resetLocationCoordinates(Context c)
    {

    }

    public static String getPrefferedWeatherLocation(Context c)
    {
        return getDefaultWeatherLocation();
    }



    public static boolean isMetric(Context c)
    {
        return true;
    }


    public static double[] getLocationCoordinates()
    {
        return getDefaultWheatherCoordinates();

    }
    public static boolean isLocationLatLonAvailable(Context context)
    {
        return false ;
    }

    public static String getDefaultWeatherLocation()
    {
        return DEFAULT_WEATHER_LOCATION;
    }

    private static double[] getDefaultWheatherCoordinates()
    {
        return DEFAULT_WEATHER_COORDINATES;

    }
}
