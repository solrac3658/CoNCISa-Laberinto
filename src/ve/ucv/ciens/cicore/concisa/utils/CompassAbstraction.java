package ve.ucv.ciens.cicore.concisa.utils;

import lejos.nxt.addon.CompassHTSensor;

public class CompassAbstraction {
	private CompassHTSensor m_csCompass;
	
	public CompassAbstraction(CompassHTSensor compass) {
		m_csCompass = compass;
	}
	
	public void setNorth() {
		m_csCompass.resetCartesianZero();
	}
	
	public float getDegreesCartesian() {
		float measure = m_csCompass.getDegreesCartesian();
		System.out.println("Comp: " + measure);
		
		if (measure >= 0.0f && measure <= 180.0f) {
			System.out.println("WEST: " + measure);
			return measure;
		} else {
			System.out.println("EAST: " + (measure - 360.0f));
			return measure - 360.0f;
		}
	}

}
