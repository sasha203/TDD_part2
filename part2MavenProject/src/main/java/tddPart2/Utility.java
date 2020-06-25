package tddPart2;

import java.util.Random;

public class Utility {

	public static float genRandFltNum(float min, float max ) {
		Random r = new Random();
		return min + r.nextFloat() * (max - min);
	}
	
}
