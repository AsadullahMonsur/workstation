package LAB8_17101157;
public class Doctor extends Personnel {

	private String specialty;

	public Doctor(String name, String id, String gender, int age, String specialty) {
		super(name, id, gender, age);
		this.specialty = specialty;
	}

	public String getSpecialty() {
		return specialty;
	}

	public String toString() {
		String str = super.toString();
		str += "Specialty: " + specialty + "\n";
		return str;
	}
	
}