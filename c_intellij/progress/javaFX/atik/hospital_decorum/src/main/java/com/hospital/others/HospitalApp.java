package com.hospital.others;
import java.util.Scanner;

public class HospitalApp {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in).useDelimiter("\r\n");
		HospitalAppMethods hsp = new HospitalAppMethods("DMCH");
		String opt;
		int op;
		while (true) {
			System.out.println();
			System.out.println("\t\tEnter 'A' for Admin.");
			System.out.println("\t\tEnter 'D' for Doctor.");
			System.out.println("\t\tEnter 'P' for Patient.");
			System.out.println("\t\tEnter 'E' for Exit.");
			opt = in.next();
			if (opt.equalsIgnoreCase("E"))
				System.exit(0);
			else if (opt.equalsIgnoreCase("A")) {
				System.out.print("Enter id: ");
				String id = in.next();
				Outer: while (true) {
					System.out.println();
					System.out.println("\t\tEnter 1 to view Doctors' list.");
					System.out.println("\t\tEnter 2 to Set-up Appointment.");
					System.out.println("\t\tEnter 3 to view Appointment");
					System.out.println("\t\tEnter 4 to view A Patient's Record");
					System.out.println("\t\tEnter 5 to view Patient Records of a Doctor.");
					System.out.println("\t\tEnter 6 to view Patient's list.");
					System.out.println("\t\tEnter 7 to add new Doctor.");
					System.out.println("\t\tEnter 8 to add new Patient.");
					System.out.println("\t\tEnter 0 to Exit.");
					op = in.nextInt();
					switch (op) {
					case 0:
						break Outer;
					case 1:
						hsp.viewDoctorsList();
						break;
					case 2:
						System.out.print("Enter Doctor's Id: ");
						String docId = in.next();
						System.out.print("Enter Patient's Id: ");
						String pId = in.next();
						hsp.makeAppointment(docId, pId);
						break;
					case 3:
						hsp.viewAppointment();
						break;
					case 4:
						System.out.print("Enter Patient's Id: ");
						hsp.viewAPatientRecord(in.next());
						break;
					case 5:
						System.out.println("Enter Doctor's Id: ");
						hsp.viewPatientRecordOfDoctor(in.next());
						break;
					case 6:
						hsp.viewPatientsList();
						break;
					case 7:
						hsp.addDoctor();
						break;
					case 8:
						hsp.addPatient();
						break;
					default:
						System.out.println("\tInvalid entry.");
					}
				}
			} else if (opt.equalsIgnoreCase("D")) {
				System.out.print("Enter Your Id: ");
				String docId = in.next();
				if (hsp.findDoctor(docId) != null) {
					Outer: while (true) {
						System.out.println();
						System.out.println("\t\tEnter 1 to view Doctors' list.");
						System.out.println("\t\tEnter 2 to Set-up Appointment.");
						System.out.println("\t\tEnter 3 to view Appointment");
						System.out.println("\t\tEnter 4 to Meet Patient.");
						System.out.println("\t\tEnter 5 to view Your Patient Records");
						System.out.println("\t\tEnter 0 to Exit.");
						op = in.nextInt();
						switch (op) {
						case 0:
							break Outer;
						case 1:
							hsp.viewDoctorsList();
							break;
						case 2:
							System.out.print("Enter Patient's Id: ");
							hsp.makeAppointment(docId, in.next());
							break;
						case 3:
							hsp.viewAppointments(docId);
							break;
						case 4:
							System.out.print("Enter Patients Id: ");
							String pId = in.next();
							System.out.print("Enter Symptoms: ");
							String symp = in.next();
							System.out.print("Enter Prescription: ");
							String pres = in.next();
							hsp.meetDoctor(docId, pId, symp, pres);
							break;
						case 5:
							hsp.viewPatientRecordOfDoctor(docId);
							break;
						default:
							System.out.println("\tInvalid Choice.");
						}
					}
				}
			} else if (opt.equalsIgnoreCase("P")) {
				System.out.print("Enter your Id: ");
				String pId = in.next();
				if (hsp.findPatient(pId) != null) {
					Outer: while (true) {
						System.out.println();
						System.out.println("\t\tEnter 1 to view Doctors' list.");
						System.out.println("\t\tEnter 2 to Set-up Appointment.");
						System.out.println("\t\tEnter 3 to view Appointment.");
						System.out.println("\t\tEnter 4 to view Your Record.");
						System.out.println("\t\tEnter 0 to Exit.");
						op = in.nextInt();
						switch (op) {
						case 0:
							break Outer;
						case 1:
							hsp.viewDoctorsList();
							break;
						case 2:
							System.out.println("Enter Doctor's Id: ");
							hsp.makeAppointment(in.next(), pId);
							break;
						case 3:
							hsp.viewAppointment(pId);
							break;
						case 4:
							hsp.viewAPatientRecord(pId);
							break;
						default:
							System.out.println("\tInvalid !");
						}
					}
				}

			}

		}
	}
}