package com.hospital.others;

import java.util.ArrayList;
import java.util.Scanner;

public class HospitalAppMethods extends Hospital {
	public HospitalAppMethods(String name) {
		super(name);
	}

	public void viewAppointments(String id, boolean isDoctor) {
		ArrayList<Appointment> app = getAppointments(id, isDoctor);
		if (app == null)
			System.out.println();
		else {
			System.out.println("\tAppointments: " + app.size());
			for (int i = 0; i < app.size(); i++) {
				System.out.println(app.get(i));
			}
		}

	}

	public void viewActiveAppointments(String id, boolean isDoctor) {
		ArrayList<Appointment> app = getActiveAppointments(id, isDoctor);
		if (app == null)
			System.out.println();
		else {
			System.out.println("\tActive Appointments: " + app.size());
			for (Appointment appointment : app) {
				System.out.println(appointment);
			}
		}
	}

	public void viewDoctorsList() {
		if (getDoctors().size() == 0)
			System.out.println("\tThere is no Doctor.");
		else {
			System.out.println(" Doctors List: ");
			for (Doctor doc : getDoctors()) {
				System.out.println(doc);
			}
		}
	}

	public void viewPatientsList() {
		if (getPatients().size() == 0)
			System.out.println("\tNo Patient !");
		else {
			System.out.println(" Patients list: ");
			for (Patient pa : getPatients()) {
				System.out.println(pa);
			}
		}
	}

	public void viewAPatientRecord(String id) {
		Patient pa = findPatient(id);
		if (pa == null) {
			System.out.println("\tPatient Not Found !");
		} else {
			if (pa.getPatientRecord().size() == 0)
				System.out.println("\tPatient haven't met any doctor yet.");
			else {
				System.out.println("\tPatient's Record: ");
				System.out.println(pa.getPatientRecord());
			}
		}
	}

	public void viewPatientRecordOfDoctor(String docId) {
		Doctor doc = findDoctor(docId);
		if (doc == null)
			System.out.println("\tDoctor not found !");
		else if (doc.getPatientRecord().size() == 0) {
			System.out.println("\tNo Patient record Found.");
		} else {
			System.out.println("\tPatient Records: ");
			System.out.println(doc.getPatientRecord());
		}
	}

	public void addDoctor() {
		Scanner in = new Scanner(System.in).useDelimiter("\r\n");
		String name, id, gender, speciality;
		int age, maxapp;
		System.out.print("Enter Doctor's name: ");
		name = in.next();
		System.out.print("Enter Doctor's ID: ");
		id = in.next();
		System.out.print("Enter Doctor's Age: ");
		age = in.nextInt();
		System.out.print("Enter Doctor's Gender: ");
		gender = in.next();
		System.out.print("Enter Doctor's Speciality: ");
		speciality = in.next();
		System.out.print("Enter Max Appointment per day: ");
		maxapp = in.nextInt();
		addNewDoctor(name, id, gender, age, speciality, maxapp);
		// in.close();
	}

	public void addPatient() {
		Scanner in = new Scanner(System.in).useDelimiter("\r\n");
		String name, id, gender;
		int age;
		System.out.print("Enter Patient's name: ");
		name = in.next();
		System.out.print("Enter Patient's ID: ");
		id = in.next();
		System.out.print("Enter Patients's Age: ");
		age = in.nextInt();
		System.out.print("Enter Patient's Gender: ");
		gender = in.next();
		addNewPatient(name, id, gender, age);
		// in.close();
	}

	public void viewAppointment() {
		Scanner in = new Scanner(System.in).useDelimiter("\r\n");

		System.out.println("1.All Appointment of a Doctor.");
		System.out.println("2.Active Appointment of a Doctor.");
		System.out.println("3.All Appointment of a Patient.");
		System.out.println("4.Active Appointment of a Patient.");
		int op = in.nextInt();
		switch (op) {
		case 1:
			System.out.print("Enter Doctor's ID: ");
			viewAppointments(in.next(), true);
			break;
		case 2:
			System.out.print("Enter Doctor's ID: ");
			viewActiveAppointments(in.next(), true);
			break;
		case 3:
			System.out.print("Enter Patient's ID: ");
			viewAppointments(in.next(), false);
			break;
		case 4:
			System.out.print("Enter Patient's ID: ");
			viewActiveAppointments(in.next(), false);
			break;
		}
		// in.close();
	}

	public void viewAppointments(String docId) {
		Scanner in = new Scanner(System.in).useDelimiter("\r\n");

		System.out.println("1.All Appointments.");
		System.out.println("2.Active Appointments.");
		int op = in.nextInt();
		if (op == 1) {
			viewAppointments(docId, true);
		} else if (op == 2) {
			viewActiveAppointments(docId, true);
		} else
			System.out.println("\t Choose 1 or 2.");
		// in.close();
	}

	public void viewAppointment(String pId) {
		Scanner in = new Scanner(System.in).useDelimiter("\r\n");
		System.out.println("1.All Appointments.");
		System.out.println("2.Active Appointments.");
		int op = in.nextInt();
		if (op == 1) {
			viewAppointments(pId, false);
		} else if (op == 2) {
			viewActiveAppointments(pId, false);
		} else
			System.out.println("\t Choose 1 or 2.");
		// in.close();
	}

}