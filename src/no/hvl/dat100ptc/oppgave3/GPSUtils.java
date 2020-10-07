package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min = da[0];
		
		for (double d: da) {
			if (d < min) {
				min = d;
			}
		}
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] lat = new double[gpspoints.length];
		for (int i = 0; i < lat.length; i++) {
			lat[i] = gpspoints[i].getLatitude();
		}
		return lat;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] lon = new double[gpspoints.length];
		for (int i = 0; i < lon.length; i++) {
			lon[i] = gpspoints[i].getLongitude();
		}
		return lon; 	
	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1 = toRadians(gpspoint1.getLatitude()),
				
				longitude1 = toRadians(gpspoint1.getLongitude()), 
				
				latitude2 = toRadians(gpspoint2.getLatitude()), 
				
				longitude2 = toRadians(gpspoint2.getLongitude());

		double deltaLat = latitude2 - latitude1;
		double deltalon = longitude2 - longitude1;
		
		double a = pow(sin(deltaLat/2),2) + cos(latitude1)*cos(latitude2)*pow(sin(deltalon/2),2);
		
		double c = 2 * atan2(sqrt(a), sqrt(1 - a));
		d = R * c;
		
		return d;
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		
		
		int secs = gpspoint2.getTime() - gpspoint1.getTime();
		double dist = distance(gpspoint1, gpspoint2);
		
		double speed = dist/secs; //m/s
		
		speed *= 3.6; //km/t
		
		return speed;

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		int s = secs % 60;
		secs /= 60;
		int m = secs % 60;
		secs /= 60;
		int h = secs % 24;
		
		timestr = (h < 10 ? "0" : "") + h + TIMESEP +(m < 10 ? "0" : "")+ m + TIMESEP +(s < 10 ? "0" : "")+ s;
		
		return " ".repeat(TEXTWIDTH - timestr.length()) + timestr;
	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str = Double.toString((double)((int)((d+0.005)*100))/100);

		return " ".repeat(TEXTWIDTH - str.length()) + str;
		
	}
	
	
}
