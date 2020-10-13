package no.hvl.dat100ptc.oppgave4;

import static java.lang.Math.atan2;

import static java.lang.Math.sqrt;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double total = 0;
		for (int i = 0; i < gpspoints.length-1;i++) {
		double x = GPSUtils.distance(gpspoints[i],gpspoints[i+1] );
		total = total+x;
				
		}
		
		return total;

	}


	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;
		
		
		
       for(int i = 0; i < gpspoints.length-1;) {
    	   double elev1 = gpspoints[i].getElevation();
    	   double elev2 = gpspoints[i+1].getElevation();
    	   if (Math.max(elev2, elev1) == elev2) {
    		   elevation = elevation + elev2-elev1;
    		   i++;
    	   } else {
    		  i++;
    	   }
       }
       
       return elevation;
		

		

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

		int secs = gpspoints[gpspoints.length-1].getTime() - gpspoints[0].getTime();
		
		return secs;

	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		double[] speeds = new double[gpspoints.length-1];
		for (int i = 0; i < speeds.length; i++) {
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
		}
		return speeds;
	 }
        


		// TODO - SLUTT

	
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		
		// TODO - START
		
		for (int i = 1; i< speeds().length; i++) {
			if (speeds()[i]> maxspeed)
				maxspeed = speeds()[i];
		}
		
		
		return maxspeed;
		// TODO - SLUTT
		
	}

	public double averageSpeed() {

		double average = 0;
		
		
		
         int secs = totalTime();
         
        double dist = totalDistance();
		
		double speed = dist/secs; //m/s
		
		speed *= 3.6; //km/t
		
		return speed;
		
		
	}

	public static double MS = 2.236936;

	
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		double met=0;		
		double mph = (speed * 0.62)*MS;
		
		
		if (mph<10) 
        	met = 4.0;
        
        else if (mph>=10 && mph<11.9) 
        	met = 6.0;

        else if (mph>=12 && mph<13.9) 
        	met = 8.0;

        else if (mph>=14 && mph<15.9) 
        	met = 10.0;

        else if (mph>=16 && mph<19) 
        	met = 12.0;
        
        else if (mph>=19) 
        	met = 16.0;
        
        
		

		kcal = met*weight*(secs/3600.0);
		
		System.out.println("kcal:" + kcal);
		return kcal;

	
		
	}

	public double totalKcal(double weight) {

		
       
        
		
		return kcal(weight, totalTime(), averageSpeed()); 
				
				
		
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START

		
		System.out.println("Total time : " + totalTime());
		System.out.println("Total distance : " + totalDistance());
		System.out.println("Total elevation : " + totalElevation());
		System.out.println("Max speed : " + maxSpeed());
		System.out.println("Average Speed : " + averageSpeed());
		System.out.println("Energy : " + totalKcal(WEIGHT));
		
		
		
		// TODO - SLUTT
		System.out.println("==============================================");
	}

}
