package com.example.soundsynthesistest;

public enum Interval {
	Unison(0),
	m2(1),
	M2(2),
	m3(3),
	M3(4),
	P4(5),
	A4(6),
	P5(7),
	m6(8),
	M6(9),
	m7(10),
	M7(11),
	Octave(12),
	m9(13),
	M9(14),
	m10(15),
	M10(16),
	P11(17),
	A11(18);
	
	public int val;
	
	Interval(int val_){
	 	this.val = val_;
	}
}
