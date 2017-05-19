package com.myowntrainer;

public class SitupsCounter extends ExerciseCounter {

	@Override
	public void interpretData() {
		if (P.size() > 150) {
			//float ravg1 = 0, ravg2 = 0;
			float pavg1 = 0, pavg2 = 0;
			//float yavg1 = 0, yavg2 = 0;
			
			//get the average of the earliest few
			
			for (int i = 0; i < sampleSize; i++) {
				//ravg1 += R.get(i);
				pavg1 += P.get(i);
				//yavg1 += Y.get(i);
			}
			//ravg1 /= sampleSize;
			pavg1 /= sampleSize;
			//yavg1 /= sampleSize;
			
			//get the average of the last few
			int hi = R.size() - 1;
			int lo = R.size() - sampleSize;
			for (int i = hi; i >= lo; i--) {
				//ravg2 += R.get(i);
				pavg2 += P.get(i);
				//yavg2 += Y.get(i);
			}
			//ravg2 /= sampleSize;
			pavg2 /= sampleSize;
			//yavg2 /= sampleSize;
			
			//try and see if there's a major change in the pitch
			if (Math.abs(pavg2 - pavg1) >= 40.0 || pavg2 < -44) {
				addRep();
			}
		}
	}
	
}
