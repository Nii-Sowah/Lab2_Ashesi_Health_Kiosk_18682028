import java.util.Scanner;
import java.util.Random;

public class HealthKiosk {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Ashesi Health Kiosk");

        // Task 1: Service Router
        System.out.print("Enter service code (P/L/T/C): ");
        String codeInput = scanner.nextLine().trim();
        char code = !codeInput.isEmpty() ? Character.toUpperCase(codeInput.charAt(0)) : ' ';
        String service = "";
        boolean validService = true;
        switch (code) {
            case 'P':
                service = "Pharmacy Desk";
                break;
            case 'L':
                service = "Lab Desk";
                break;
            case 'T':
                service = "Triage Desk";
                break;
            case 'C':
                service = "Counseling Desk";
                break;
            default:
                validService = false;
                System.out.println("Invalid service code");
        }
        if (validService) {
            System.out.println("Go to: " + service);
        }

        // Task 2: Mini Health Metric (only for Triage)
        double metricValue = 0;
        int metricInt = 0;
        if (code == 'T') {
            System.out.println("Health metric: 1 for BMI, 2 for Dosage round-up, 3 for simple trig helper");
            System.out.print("Enter option (1/2/3): ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.print("Enter weight(kg): ");
                    double weight = scanner.nextDouble();
                    System.out.print("Enter height(m): ");
                    double height = scanner.nextDouble();
                    scanner.nextLine();

                    double bmi = weight / (height * height);
                    double bmiRounded = Math.round(bmi * 10) / 10.0;

                    metricValue = bmiRounded;
                    metricInt = (int)Math.round(bmi);
                    System.out.print("BMI: " + bmiRounded + " Category: ");

                    if (bmi < 18.5) {
                        System.out.println("Underweight");
                    } else if (bmi < 25.0) {
                        System.out.println("Normal");
                    } else if (bmi < 30.0) {
                        System.out.println("Overweight");
                    } else {
                        System.out.println("Obese");
                    }
                    break;

                case 2:
                    System.out.print("Enter required dosage mg: ");
                    double dosage = scanner.nextDouble();
                    scanner.nextLine();

                    int tablets = (int)Math.ceil(dosage / 250.0);
                    metricValue = tablets;
                    metricInt = tablets;
                    System.out.println("Tablets: " + tablets);
                    break;

                case 3:
                    System.out.print("Enter angle in degrees: ");
                    double angle = scanner.nextDouble();
                    scanner.nextLine();

                    double radians = Math.toRadians(angle);
                    double sin = Math.round(Math.sin(radians) * 1000) / 1000.0;
                    double cos = Math.round(Math.cos(radians) * 1000) / 1000.0;

                    metricValue = sin;
                    metricInt = (int)Math.round(sin * 100);
                    System.out.println("sin: " + sin + " cos: " + cos);
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        // Task 3: ID Sanity Check
        Random rand = new Random();
        char idChar = (char)('A' + rand.nextInt(26));
        StringBuilder idDigits = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int digit = 3 + rand.nextInt(7);
            idDigits.append(digit);
        }
        String shortID = idChar + idDigits.toString();

        if (shortID.length() != 5) {
            System.out.println("Invalid length.");
        } else if (!Character.isLetter(shortID.charAt(0))) {
            System.out.println("Invalid: first char must be a letter");
        } else if (!(Character.isDigit(shortID.charAt(1)) && Character.isDigit(shortID.charAt(2)) &&
                Character.isDigit(shortID.charAt(3)) && Character.isDigit(shortID.charAt(4)))) {
            System.out.println("Invalid: last 4 must be digits");
        } else {
            System.out.println("ID OK");
        }
        System.out.println("Generated ID: " + shortID);

        // Task 4: Secure Display Code
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine().trim();
        char base = !firstName.isEmpty() ? Character.toUpperCase(firstName.charAt(0)) : 'A';
        char shifted = (char)('A' + (base - 'A' + 2) % 26);
        String lastTwo = shortID.substring(shortID.length() - 2);
        String displayCode = shifted + lastTwo + "-" + metricInt;
        System.out.println("Display Code: " + displayCode);

        // Task 5: Service Summary
        String summary = switch (code) {
            case 'P' -> "You selected Pharmacy. Please proceed to the Pharmacy Desk.";
            case 'L' -> "You selected Lab. Please proceed to the Lab Desk.";
            case 'T' -> "You selected Triage. Metric value: " + metricValue;
            case 'C' -> "You selected Counseling. Please proceed to the Counseling Desk.";
            default -> "No valid service selected.";
        };
        System.out.println("Summary: " + summary);

        scanner.close();
    }
}
