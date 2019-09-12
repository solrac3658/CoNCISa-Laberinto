package ve.ucv.ciens.cicore.concisa.subsumption;

import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.subsumption.Behavior;

public abstract class BaseBehavior implements Behavior {
	protected UltrasonicSensor m_usFront;
	protected UltrasonicSensor m_usRight;
	protected UltrasonicSensor m_usLeft;
	protected CompassHTSensor  m_csCompass;
	
	public BaseBehavior(UltrasonicSensor front, UltrasonicSensor right, UltrasonicSensor left, CompassHTSensor compass) {
		m_usFront = front;
		m_usRight = right;
		m_usLeft = left;
		m_csCompass = compass;
	}
	
	@Override
	public abstract boolean takeControl();

	@Override
	public abstract void action();

	@Override
	public abstract void suppress();
}
