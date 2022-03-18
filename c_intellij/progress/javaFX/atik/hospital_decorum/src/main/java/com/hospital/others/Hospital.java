package com.hospital.others;

import java.util.ArrayList;

public class Hospital {
	String name;
	private ArrayList<Doctor> doctors = new ArrayList<>();
	private ArrayList<Patient> patients = new ArrayList<>();

	public Hospital(String name) {
		this.name = name;
	}

	public void addNewDoctor(String name, String id, String gender, int age, String speciality, int maxapp) {
		Doctor doc = new Doctor(name, id, gender, age, speciality, maxapp);
		doctors.add(doc);
	}

	public void addNewPatient(String name, String id, String gender, int age) {
		Patient pa = new Patient(name, id, gender, age);
		patients.add(pa);
	}

	public void makeAppointment(String doctorId, String patientId) {
		Doctor doc = findDoctor(doctorId);
		Patient pa = findPatient(patientId);
		if (doc != null && pa != null) {
			doc.addAppointment(patientId);
			pa.addAppointment(doctorId);
		}
	}

	public void meetDoctor(String doctorId, String patientId, String symptoms, String prescription) {
		Doctor doc = findDoctor(doctorId);
		Patient pa = findPatient(patientId);
		PatientRecord pr = new PatientRecord(doctorId, patientId, symptoms, prescription);
		if (doc != null && pa != null) {
			doc.meetPatient(patientId);
			doc.addPatientRecord(pr);
			pa.addPatientRecord(pr);
		}
	}

	public Doctor findDoctor(String id) {
		for (int i = 0; i < doctors.size(); i++) {
			if (doctors.get(i).getId().equals(id))
				return doctors.get(i);
		}
		return null;
	}

	public Patient findPatient(String id) {
		for (int i = 0; i < patients.size(); i++) {
			if (patients.get(i).getId().equals(id))
				return patients.get(i);
		}
		return null;
	}

	public ArrayList<Appointment> getAppointments(String id, boolean isDoctor) {
		if (isDoctor == true) {
			Doctor doc = findDoctor(id);
			if (doc != null)
				return doc.getAppointments();
			else
				System.out.println("\tDoctor not Found !");
		} else if (isDoctor == false) {
			Patient pa = findPatient(id);
			if (pa != null)
				return pa.getAppointments();
			else
				System.out.println("\tPatient not Found !");
		}
		return null;
	}

	public ArrayList<Appointment> getActiveAppointments(String id, boolean isDoctor) {
		if (isDoctor) {
			Doctor doc = findDoctor(id);
			if (doc != null)
				return doc.getAppointments("Scheduled");
			else
				System.out.println("\tDoctor not Found !");
		} else if (isDoctor == false) {
			Patient pa = findPatient(id);
			if (pa != null)
				return pa.getAppointments("Scheduled");
			else
				System.out.println("\tPatient not Found !");
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(ArrayList<Doctor> doctors) {
		this.doctors = doctors;
	}

	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}

}