package research_a;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data // 모든 필드(변수)에 대해 자동으로 getter, setter 생성
public class Person {

	// @Getter // getName() 자동 생성
	// @Setter // setName() 자동 생성
	private String name;

	private String phone;
	private String email;
	private int age;

}
