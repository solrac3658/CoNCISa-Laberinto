package ve.ucv.ciens.cicore.concisa.control;

import lejos.nxt.UltrasonicSensor;

public class UltraSoundChecker {
	public static final int WALL_DISTANCE_CM = 20;
	public static final int WALL_TOL_CM = 2;
	
	private UltrasonicSensor m_usFront;
	private UltrasonicSensor m_usRight;
	private UltrasonicSensor m_usLeft;

	public UltraSoundChecker(UltrasonicSensor front, UltrasonicSensor right, UltrasonicSensor left) {
		m_usFront = front;
		m_usRight = right;
		m_usLeft = left;
	}

	public boolean Derecha() throws Exception {
		int ret = m_usRight.ping();
		
		if (ret != 0)
			throw new Exception("exDER: " + ret);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) { }
		
		//if (Math.abs(WALL_DISTANCE_CM - m_usRight.getDistance()) > WALL_TOL_CM)
		if (m_usRight.getDistance() < WALL_DISTANCE_CM)
			return false;
		else
			return true;
	}
	
	public boolean Izquierda() throws Exception {
		int ret = m_usLeft.ping();
		
		if (ret != 0)
			throw new Exception("exIZQ: " + ret);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) { }
		
		//if (Math.abs(WALL_DISTANCE_CM - m_usLeft.getDistance()) > WALL_TOL_CM)
		if (m_usLeft.getDistance() < WALL_DISTANCE_CM)
			return false;
		else
			return true;
	}
	
	public boolean Frente() throws Exception {
		int ret = m_usFront.ping();
		
		if (ret != 0)
			throw new Exception("exFRN: " + ret);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) { }
		
		//if (Math.abs(WALL_DISTANCE_CM - m_usFront.getDistance()) > WALL_TOL_CM)
		if (m_usFront.getDistance() < WALL_DISTANCE_CM)
			return false;
		else
			return true;
	}
}
