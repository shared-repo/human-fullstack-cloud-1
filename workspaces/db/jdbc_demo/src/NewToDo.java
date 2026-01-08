import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

// 테이블 만들기
/*
create table newtodo
(
	id int not null primary key auto_increment,
    title varchar(200) not null,
    writeDate Date default (now()),
    completed boolean default (false)
);
*/

public class NewToDo {

	private int id;
	private String title;
	private Date writeDate;
	private boolean completed;
	
	public NewToDo() {} // 다른 생성자를 만들었기 때문에 기본 생성자가 자동으로 제공되지 않아서 직접 구현
	public NewToDo(String title) {
		this.title = title;
		this.writeDate = new Date();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	@Override
	public String toString() {
		String sCompleted = this.completed ? "완료" : "진행";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 -> 지정된 형식의 문자열
		String sWriteDate = sdf.format(writeDate);
		
		// %d : 정수, %s : 문자열, %b : boolean
		return String.format("%3d.[%s][%s][%s]", id, title, sWriteDate, sCompleted);
	}
	
	
}











