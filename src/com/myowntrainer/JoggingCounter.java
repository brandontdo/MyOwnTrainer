package com.myowntrainer;

public class JoggingCounter extends ExerciseCounter {
	
	@Override
	public void update(float r, float p, float y) {
		if (P.size() > cachePoints) {
			//Po.removeFirst();
			//R.removeFirst();
			P.removeFirst();
			//Y.removeFirst();
		}
		//Po.add(po);
		//R.add(r);
		P.add(p);
		//Y.add(y);
		interpretData();
	}
	
	@Override
	public void interpretData() {
		if (P.size() > 50) {
			float d_tot = 0.0f;
			for (int i = 1; i < P.size(); i++) {
				float d = Math.abs(P.get(i) - P.get(i - 1));
				if (d > 0.5) d_tot += d;
			}
			//approximation: every change in 50 pitch = 1 meter distance
			if (d_tot > 50.0) addRep(d_tot/100.0f);
		}
	}
	
}
