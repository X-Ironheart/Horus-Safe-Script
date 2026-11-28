import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Hospitaldata {


public static final String DRUGS_FILE = "../drugs_db.csv";
// ده اسم الملف البضان بتاع الداتا بتاع ام الدوا 
public static final String PATIENTS_FILE = "../patients_log.csv";
// ده اسم الملف البضان بتاع الداتا بتاع ام المرضى  

public static int drugs_count = 0;
public static int patients_count = 0;
static final int MAX = 100000;

public static String [] drug_name = new String [MAX];
public static String [] tdisease = new String [MAX];
public static String [] cont = new String [MAX];
public static double [] drug_price = new double [MAX];
public static String [] patientlogs = new String [MAX];


public static void load_drugs_file() {
    File drugs_file = new File(DRUGS_FILE);
    try (Scanner fileScanner = new Scanner(drugs_file)) {
        while (fileScanner.hasNextLine()&& drugs_count < MAX){
            String line = fileScanner.nextLine();
            if (line == null || line.trim().isEmpty() || line.contains("DrugName")) {continue;}
            String [] drug_details = line.split(";"); // تقسيم ال csv نحول الدوا ل Array 
            if (drug_details.length == 4){
                drug_name[drugs_count] = drug_details[0];
                tdisease[drugs_count] = drug_details[1];
                cont[drugs_count] = drug_details[2];
                drug_price[drugs_count] = Double.parseDouble(drug_details[3]);
                drugs_count++;
            }
        }
        // read from fileScanner انا طلع عين امي علشان افهم في ايرور ليه .Try , catch..-_-
    } catch (FileNotFoundException e) {
        System.out.println("ُError loading drug file [file not found]: " + DRUGS_FILE);
    }
}

public static void save_drugs_file(){
    try {
        PrintWriter writer = new PrintWriter(new FileWriter(DRUGS_FILE,false));    
        writer.println("DrugName;TargetDisease;Contraindications;Price");
        for(int i= 0; i< drugs_count; i++){
            writer.println(drug_name[i] + ";" + tdisease[i] + ";" + cont[i] + ";" + drug_price[i]);
            writer.close();
        }
    } catch (IOException e) {
        System.out.print("Error while sving drug file [Check your format]"+ DRUGS_FILE);
    }
}

//ده هيبقا زي الميثود اللي فوق بس بكرا بقا ولا حاجه <<
public static void load_patientlogs_file() {
    File patient_file = new File(PATIENTS_FILE);
    try (Scanner fileScanner = new Scanner(patient_file)) {
        while (fileScanner.hasNextLine() && patients_count < MAX) { 
            String line = fileScanner.nextLine();
            if (line == null || line.trim().isEmpty() || line.contains("PatientName;")) {continue;}
                patients_count++;
            // read from fileScanner انا طلع عين امي علشان افهم في ايرور ليه ...-_-
        }
    } catch (FileNotFoundException e) {
        System.out.println("ُError loading patient logs file [file not found]: " + PATIENTS_FILE);
    }
}

public static void save_patientlogs(String name, int age, String disease, String chronic, String drug, double bill) {
{        String record = name + ";" + age + ";" + disease + ";" + chronic + ";" + drug + ";" + bill;
        // 1. نضيفه في الـ Array لو لسه فيها مكان
        if (patients_count < MAX) {
            patientlogs[patients_count] = record;
            patients_count++;
        }

        // 2. نضيفه في الملف على الهارد ديسك
        try {
            File file = new File(PATIENTS_FILE);
            boolean isNewFile = !file.exists() || file.length() == 0;

            PrintWriter writer = new PrintWriter(new FileWriter(file, true));

            if (isNewFile) {
                writer.println("PatientName;Age;ActiveDisease;ChronicConditions;PrescribedDrug;TotalBill");
            }

            writer.println(record);
            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving patient log: " + e.getMessage());
        }
    }
}
}
