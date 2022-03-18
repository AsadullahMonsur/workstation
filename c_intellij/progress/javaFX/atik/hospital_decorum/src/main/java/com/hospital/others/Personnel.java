package com.hospital.others;

import java.util.ArrayList;

public class Personnel {
	private String name, id, gender;
	private int age;
	private ArrayList<PatientRecord> patientRecord = new ArrayList<>();
	private ArrayList<Appointment> appointments = new ArrayList<>();

	public Personnel(String name, String id, String gender, int age) {
		this.name = name;
		this.id = id;
		this.gender = gender;
		this.age = age;
	}

	public void addAppointment(Appointment app) {
		appointments.add(app);
	}

	public void addPatientRecord(PatientRecord record) {
		patientRecord.add(record);
	}

	public Appointment getAppointment(String docId, String pId) {
		for (Appointment appointment : appointments) {
			if (appointment.getDoctorId().equalsIgnoreCase(docId) && appointment.getPatientId().equalsIgnoreCase(pId)
					&& appointment.getStatus().equalsIgnoreCase("Scheduled"))
				return appointment;
		}
		return null;
	}

	public ArrayList<Appointment> getAppointments(String status) {
		ArrayList<Appointment> app = new ArrayList<>();
		for (Appointment appointment : appointments) {
			if (appointment.getStatus().equalsIgnoreCase(status))
				app.add(appointment);
		}
		return app;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public ArrayList<PatientRecord> getPatientRecord() {
		return patientRecord;
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	@Override
	public String toString() {
		return "\n\tId: " + id + "\n\tName: " + name.toUpperCase() + "\n\tGender: " + gender + "\t\tAge: " + age;
	}

}