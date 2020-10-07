package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	// konverter tidsinformasjon i gps data punkt til antall sekunder fra midnatt
	// dvs. ignorer information om dato og omregn tidspunkt til sekunder
	// Eksempel - tidsinformasjon (som String): 2017-08-13T08:52:26.000Z
    // skal omregnes til sekunder (som int): 8 * 60 * 60 + 52 * 60 + 26 
	
	private static int TIME_STARTINDEX = 11; // startindex for tidspunkt i timestr

	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min, sec;
		
        String time = timestr.substring(11,13);
        String minu = timestr.substring(14,16);
        String sek = timestr.substring(17,19);
         hr = Integer.parseInt(time);
         min = Integer.parseInt(minu);
         sec = Integer.parseInt(sek);
        secs = (hr*60*60)+(min*60)+sec;
 
return secs;
		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint[] GPS1 = new GPSPoint[1];
		double x = Double.parseDouble(longitudeStr);
		double y = Double.parseDouble(latitudeStr);
		double m = Double.parseDouble(elevationStr);
		int  A = toSeconds(timeStr);
        GPS1[0] = new GPSPoint(A,y,x,m);
        return GPS1[0];
	}
	public static String latitudeStr(String a) {

        String c = a.substring(25,34);

        return c;
    }
	public static String longitudeStr(String a) {

        String c = a.substring(35,43);

        return c;
    }
	public static String elevationStr(String a) {

        String c = a.substring(44,48);

        return c;
    }
	
}
