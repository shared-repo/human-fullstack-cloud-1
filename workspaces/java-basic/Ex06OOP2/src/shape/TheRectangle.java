package shape;

// class TheRectangle { // default class는 같은 패키지에서만 사용할 수 있는 설정
public class TheRectangle { // public class는 다른 패키지에서 이 클래스를 사용할 수 있는 설정
	
	int leftX;
	private int topY;
	private int rightX;
	private int bottomY;
	
	public int getLeftX() {
		return leftX;
	}
	public void setLeftX(int leftX) {
		this.leftX = leftX;
	}
	public int getTopY() {
		return topY;
	}
	public void setTopY(int topY) {
		this.topY = topY;
	}
	public int getRightX() {
		return rightX;
	}
	public void setRightX(int rightX) {
		this.rightX = rightX;
	}
	public int getBottomY() {
		return bottomY;
	}
	public void setBottomY(int bottomY) {
		this.bottomY = bottomY;
	}

}
