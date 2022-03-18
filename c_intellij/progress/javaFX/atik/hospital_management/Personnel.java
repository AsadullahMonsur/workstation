package LAB8_17101157;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Personnel {

	private String name;
	private String id;
	private int age;
	private String gender;
	
	private ArrayList<String> patientRecords; //currentTime:doctorId:patientId:symptom:prescription
	
	public Personnel(String name, String id, String gender, int age) {
		this.name = name;
		this.id = id;
		this.gender = gender;
		this.age = age;
		patientRecords = new ArrayList<>();
	}

	public String getAppTime() {
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String appTime = time.format(formatter);
		return appTime;
	}

	void addPatientRecord(String record) {
		patientRecords.add(record);
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public String getPatientRecords() {
		return patientRecords.toString();
	}

	public String toString() {
		String str = "\nName: " + name + "\nID: " + id + "\nGender: " + gender + "\nAge: " + age + "\n";
		return str;
	}
	
}