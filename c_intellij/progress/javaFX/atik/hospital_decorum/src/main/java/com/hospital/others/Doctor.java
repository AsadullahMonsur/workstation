package com.hospital.others;

public class Doctor extends Personnel {
	private String speciality;
	private int maxAppointmentPerDay;

	public Doctor(String name, String id, String gender, int age, String speciality, int maxAppointmentPerDay) {
		super(name, id, gender, age);
		this.speciality = speciality;
		this.maxAppointmentPerDay = maxAppointmentPerDay;
	}

	// For adding appointments
	// int serial=0;
	public void addAppointment(String patientId) {
		if (getAppointments().size() <= maxAppointmentPerDay) {
			int serial = (int) getAppointments().size();
			Appointment appt = new Appointment(super.getId(), patientId, "Scheduled", serial+=1);
			super.addAppointment(appt);
		}
	}

	// For updating appointment status
	public void meetPatient(String patientId) {
		Appointment ap = getAppointment(getId(), patientId);
		if (ap != null && ap.getStatus().equalsIgnoreCase("Scheduled"))
			ap.setStatus("Visited");
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tSpeciality: " + speciality.toUpperCase() + "\tMaxAppointmentPerDay: "
				+ maxAppointmentPerDay;
	}

	public String getSpeciality() {
		return speciality;
	}

	public int getMaxAppointmentPerDay() {
		return maxAppointmentPerDay;
	}
}