package ve.ucv.ciens.cicore.concisa.utils;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;

public class QuitButtonListener implements ButtonListener {
	private TimeCounter m_tcCounter;

	public QuitButtonListener() {
		m_tcCounter = null;
	}
	
	@Override
	public void buttonPressed(Button b) {
		m_tcCounter = new TimeCounter();
		m_tcCounter.start();
	}

	@Override
	public void buttonReleased(Button b) {
		if (m_tcCounter != null) {
			m_tcCounter.finish();
			m_tcCounter = null;
		}
	}

	class TimeCounter extends Thread {
		private boolean done;
		private long timeMilisBefore;
		
		public TimeCounter() {
			done = false;
		}
		
		@Override
		public void run() {
			long m_lTimeMilisNow;
			
			timeMilisBefore = System.currentTimeMillis();
			
			while (!done) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
				
				m_lTimeMilisNow = System.currentTimeMillis();
				
				if (m_lTimeMilisNow - timeMilisBefore > 3000)
					System.exit(0);
			}
		}
		
		public void finish() {
			done = true;
		}
	}
}
