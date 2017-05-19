package com.myowntrainer;

import java.util.LinkedList;

public class ExerciseCounter {
	
	public static final int cachePoints = 1000;
	public static final int sampleSize = 50;
	public static final int latentLength = sampleSize*2 + 20;
	
	protected float totalReps;
	
	protected LinkedList<Float> R; //roll
	protected LinkedList<Float> P; //pitch
	protected LinkedList<Float> Y; //yaw
	
	public ExerciseCounter() {
		totalReps = 0;
		R = new LinkedList<Float>();
		P = new LinkedList<Float>();
		Y = new LinkedList<Float>();
	}
	
	public void update(float r, float p, float y) {
		if (R.size() > cachePoints) {
			R.removeFirst();
			P.removeFirst();
			Y.removeFirst();
		}
		R.add(r);
		P.add(p);
		Y.add(y);
		interpretData();
	}
	
	//implement your own override
	//interpretData should either increase the totalReps
	public void interpretData() {}
	
	public float getReps() {
		return totalReps;
	}
	
	//reset all of the data and count them as an extra rep
	public void addRep(float delta) {
		totalReps += delta;
		R.clear();
		P.clear();
		Y.clear();
	}
	
	public void addRep() {
		addRep(1.0f);
	}
	
	public void setRep(float reps) {
		totalReps = reps;
	}
}
