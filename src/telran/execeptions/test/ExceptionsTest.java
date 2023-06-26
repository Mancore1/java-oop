package telran.execeptions.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import telran.exceptions.BallBrokenFloor;

class ExceptionsTest {


	@Test
	void testException() {
		int res = 0;
		try {
			res = itThrowsCheckedException(-10);
			System.out.println("everything ok");
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			res = 100;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			res = 200;
		}
		assertEquals(20, res);
	}
	
	private int itThrowsCheckedException(int number) throws Exception {
		if (number < 0) {
		throw new Exception("just test checked exception");
		}
		if (number > 1000) {
			throw new RuntimeException("number cannot be grater than 1000");
		}
		return number * 2;
	}
	
	@Test
	void ballBrokenFloorTest() {
		BallBrokenFloor bbf = new BallBrokenFloor(200);
		assertEquals(bbf.getFloor(), getMinFloor(bbf));
	}
	
	private int getMinFloor(BallBrokenFloor bbf) {
		int res = 0;
		int nFloors = bbf.getnFloors();
		int left = 1;
		int right = nFloors;
		
		while (left <= right) {
			int mid = left + (right - left) / 2;
			
			try {
				bbf.broken(mid);
				left = mid + 1;
			} catch (Exception e) {
				res = mid;
				right = mid - 1;
			}
		}
		return res;
	}
}
