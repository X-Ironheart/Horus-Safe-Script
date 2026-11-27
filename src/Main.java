public class Main {
    public static void main(String[] args){
        Hospitaldata.load_drugs_file();
        Hospitaldata.load_patientlogs_file();
        System.out.println("=============================================");
        System.out.println("===              WELLCOME TO              ===");
        System.out.println("==            HORUS-SAFE-SCRIPT            ==");
        System.out.println("=============================================");
        System.out.println("==    loaded Drugs: " + Hospitaldata.drugs_count + "    ==    users: " + Hospitaldata.patients_count +"    ==");
        System.out.println("=============================================");
        System.out.println("nour's love you guys");
    }
}
















