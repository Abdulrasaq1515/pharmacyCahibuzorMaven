package pharmacyDrug.utils;

import pharmacyDrug.data.models.Category;
import pharmacyDrug.data.models.Type;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleUtils {

    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static String readNonEmptyLine(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("❌ Input cannot be empty. Please try again.");
        }
    }

    public static double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            try {
                double value = Double.parseDouble(sc.nextLine().trim());
                if (value > 0) {
                    return value;
                }
                System.out.println("❌ Value must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number. Try again.");
            }
        }
    }

    public static int readPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                if (value > 0) {
                    return value;
                }
                System.out.println("❌ Value must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid integer. Try again.");
            }
        }
    }

    public static LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt + " (dd-MM-yyyy): ");
            String input = sc.nextLine().trim();
            try {
                return LocalDate.parse(input, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("❌ Invalid date format. Please use dd-MM-yyyy.");
            }
        }
    }

    public static Type readType(String prompt) {
        while (true) {
            System.out.print(prompt + " (TABLET, SYRUP, INJECTION): ");
            try {
                return Type.valueOf(sc.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid type. Try again.");
            }
        }
    }

    public static Category readCategory(String prompt) {
        while (true) {
            System.out.print(prompt + " (ANALGESIC, ANTIBIOTICS, ANTIFUNGAL, ANTIHYPERTENSIVE, MULTIVITAMIN): ");
            try {
                return Category.valueOf(sc.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid category. Try again.");
            }
        }
    }

    public static LocalDate[] readValidatedDates() {
        LocalDate manufactureDate;
        LocalDate expiryDate;

        while (true) {
            manufactureDate = readDate("Manufacture Date");
            expiryDate = readDate("Expiry Date");

            if (manufactureDate.isAfter(expiryDate)) {
                System.out.println("❌ Manufacture date cannot be after expiry date. Try again.");
            } else if (expiryDate.isBefore(LocalDate.now())) {
                System.out.println("❌ Expiry date must be in the future. Try again.");
            } else {
                return new LocalDate[]{manufactureDate, expiryDate};
            }
        }
    }
}
