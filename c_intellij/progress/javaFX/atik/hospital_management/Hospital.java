package LAB8_17101157;
import java.util.ArrayList;

public class Hospital {

	private String hospitalName;

	private ArrayList<Doctor> doctors;
	private ArrayList<Patient> patients;

	public Hospital(String name) {
		hospitalName = name;
		doctors = new ArrayList<>();
		patients = new ArrayList<>();
	}
	
	public void addDoctor(String name, String id, String gender, int age, String specialty) {
		Doctor d = new Doctor(name, id, gender, age, specialty);
		doctors.add(d);
	}

	public void addPatient(String name, String id, String gender, int age) {
		Patient p = new Patient(name, id, gender, age);
		patients.add(p);
	}

	public void meetDoctor(String doctorId, String patientId, String symptoms, String prescription) {
		Doctor d = findDoctor(doctorId);
		if(d == null) {
			return;
		}
		Patient p = findPatient(patientId);
		if(p == null) {
			return;
		}
		String record = String.join(":", p.getAppTime(), d.getId(), p.getId(), symptoms, prescription);
		d.addPatientRecord(record);
		p.addPatientRecord(record);
	}

	public Doctor findDoctor(String docId) {
		for(Doctor d : doctors) {
			if(docId.equals(d.getId())) {
				return d;
			}
		}
		return null;
	}

	public Patient findPatient(String patientId) {
		for(Patient p : patients) {
			if(patientId.equals(p.getId())) {
				return p;
			}
		}
		return null;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public String getDoctors() {
		return doctors.toString();
	}

	public String getPatients() {
		return patients.toString();
	}

}