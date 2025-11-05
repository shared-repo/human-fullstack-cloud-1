
public class Person3 { 
	
	// private : 클래스 외부에서 접근할 수 없는 멤버
	private String name;
	private String phone;
	private String email;
	private int age;
		
	// public : 클래스 외부에서 접근할 수 있는 멤버
	public Person3() {	}	
	public Person3(String name, String p, String e, int a) {
		this.name = name; // this : 클래스의 멤버임을 표시하는 예약어
		this.phone = p;
		this.email = e;
		this.age = a;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}










