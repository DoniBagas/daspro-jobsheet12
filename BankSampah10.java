import java.util.Locale;
import java.util.Scanner;

public class BankSampah10 {

    static Scanner sc = new Scanner(System.in);

    static double[] inputSetoran(int n) {
        double[] setoran = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Berat setoran nasabah " + (i + 1) + " (kg): ");
            setoran[i] = sc.nextDouble();
        }
        return setoran;
    }

    static void tampilSetoran(double[] setoran) {
        System.out.println("\n===== DATA SETORAN =====");
        for (int i = 0; i < setoran.length; i++) {
            System.out.println("Nasabah " + (i + 1) + ": " + setoran[i] + " kg");
        }
    }

    static double totalSetoran(double[] setoran) {
        double total = 0;
        for (int i = 0; i < setoran.length; i++) {
            total += setoran[i];
        }
        return total;
    }

    static double hitungSaldo(double berat, double hargaPerKg) {
        return berat * hargaPerKg;
    }

    static int nasabahTerbanyak(double[] setoran) {
        int idxMax = 0;
        for (int i = 1; i < setoran.length; i++) {
            if (setoran[i] > setoran[idxMax]) {
                idxMax = i;
            }
        }
        return idxMax;
    }

    public static void main(String[] args) {
        System.out.print("Masukkan jumlah nasabah (N): ");
        int n = sc.nextInt();

        double[] setoran = inputSetoran(n);

        tampilSetoran(setoran);

        double total = totalSetoran(setoran);
        System.out.println("\nTotal setoran seluruh nasabah: " + total + " kg");

        double hargaPerKg = 3000;
        System.out.println("\n===== SALDO NASABAH (Rp3.000/kg) =====");
        for (int i = 0; i < n; i++) {
            double saldo = hitungSaldo(setoran[i], hargaPerKg);
            System.out.printf(Locale.US, "Nasabah %d: Rp%,.0f%n", (i + 1), saldo);
        }

        int idx = nasabahTerbanyak(setoran);
        System.out.println("\nSetoran terbanyak: Nasabah " + (idx + 1)
                + " (" + setoran[idx] + " kg)");

        sc.close();
    }
}