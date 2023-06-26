package telran.exceptions;

public class BallBrokenFloor {
	private int floor;
	private int nFloors;
	
	public int getFloor() {
		return floor;
	}
	
	public void broken(int floor) throws Exception {
		if (floor >= this.floor) {
			throw new Exception();
		}
	}

	public BallBrokenFloor(int nFloors) {
		this.nFloors = nFloors;
		floor = (int) (1 + Math.random() * nFloors);
	}

	public int getnFloors() {
		return nFloors;
	}
}
