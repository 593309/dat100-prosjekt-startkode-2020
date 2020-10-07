package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {
		gpspoints = new GPSPoint[n];
		antall = 0;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	protected boolean insertGPS(GPSPoint gpspoint) {
		boolean x= false;
		if (antall<gpspoints.length) {
			gpspoints[antall]=gpspoint;
			antall++;
			x=true;
			return x; 
		} else {
			return x; 
		}
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {
		
		GPSPoint[] v = new GPSPoint [1];
		String klokkeslett = time.substring(time.indexOf("T")+1,time.lastIndexOf("."));
		String[] sekunder= klokkeslett.split(":");

		
		int o=Integer.parseInt(sekunder[0])*3600 + Integer.parseInt(sekunder[1])*60+Integer.parseInt(sekunder[2]);
		int x = o;
		
		double y = Double.parseDouble(latitude);
		double m = Double.parseDouble(longitude);
		double n = Double.parseDouble(elevation);
		v[0]= new GPSPoint(x, y, m, n);
		return insertGPS(v[0]);
		
	}
	public void print() {
		
			for (int i = 0; i < gpspoints.length;i++) {
	            gpspoints[i].toString();
	            
	        	System.out.println("====== Konvertert GPS Data - START ======");
	        }
	}
}
