import java.util.Locale;
import java.util.Scanner;

public class RekapLaundry10 {

    static Scanner sc = new Scanner(System.in);

    static void inputLayanan(String[] layanan, int[] harga) {
        for (int i = 0; i < layanan.length; i++) {
            System.out.print("Nama layanan ke-" + (i + 1) + "     : ");
            layanan[i] = sc.nextLine();
            System.out.print("Harga per kg " + layanan[i] + ": ");
            harga[i] = sc.nextInt();
            sc.nextLine();
        }
    }

    static void inputData(int[][] data, String[] layanan) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print("Berat " + layanan[i] + " Hari ke-" + (j + 1) + " (kg): ");
                data[i][j] = sc.nextInt();
            }
        }
    }

    static void tampilTabel(int[][] data, String[] layanan, int[] harga) {
        System.out.printf("%-14s%-10s", "Layanan", "Harga/kg");
        for (int j = 0; j < data[0].length; j++) {
            System.out.printf("%-11s", "Hari ke-" + (j + 1));
        }
        System.out.println();

        for (int i = 0; i < data.length; i++) {
            System.out.printf("%-14s%-10d", layanan[i], harga[i]);
            for (int j = 0; j < data[i].length; j++) {
                System.out.printf("%-11d", data[i][j]);
            }
            System.out.println();
        }
    }

    static int totalPerLayanan(int[][] data, int idxLayanan) {
        int total = 0;
        for (int j = 0; j < data[idxLayanan].length; j++) {
            total += data[idxLayanan][j];
        }
        return total;
    }

    static void tampilLayananTertinggi(int[][] data, String[] layanan) {
        int idxMax = 0;
        for (int i = 1; i < data.length; i++) {
            if (totalPerLayanan(data, i) > totalPerLayanan(data, idxMax)) {
                idxMax = i;
            }
        }
        System.out.println("Layanan dengan cucian terbanyak: " + layanan[idxMax]
                + " (" + totalPerLayanan(data, idxMax) + " kg)");
    }

    static int hariTersibuk(int[][] data) {
        int idxMax = 0;
        int totalMax = 0;
        for (int j = 0; j < data[0].length; j++) {
            int totalHari = 0;
            for (int i = 0; i < data.length; i++) {
                totalHari += data[i][j];
            }
            if (totalHari > totalMax) {
                totalMax = totalHari;
                idxMax = j;
            }
        }
        return idxMax;
    }

    static void tampilPendapatan(int[][] data, String[] layanan, int[] harga) {
        int totalPendapatan = 0;
        for (int i = 0; i < data.length; i++) {
            int pendapatan = totalPerLayanan(data, i) * harga[i];
            totalPendapatan += pendapatan;
            System.out.printf(Locale.US, "%-14s: Rp%,d%n", layanan[i], pendapatan);
        }
        System.out.printf(Locale.US, "%-14s: Rp%,d%n", "TOTAL", totalPendapatan);
    }

    public static void main(String[] args) {
        System.out.print("Jumlah layanan : ");
        int nLayanan = sc.nextInt();
        System.out.print("Jumlah hari    : ");
        int nHari = sc.nextInt();
        sc.nextLine();

        String[] layanan = new String[nLayanan];
        int[] harga = new int[nLayanan];
        int[][] data = new int[nLayanan][nHari];

        inputLayanan(layanan, harga);
        inputData(data, layanan);

        System.out.println("\n===== TABEL REKAP CUCIAN (kg) =====");
        tampilTabel(data, layanan, harga);

        System.out.println("\n===== TOTAL PER LAYANAN =====");
        for (int i = 0; i < data.length; i++) {
            System.out.println(layanan[i] + ": " + totalPerLayanan(data, i) + " kg");
        }

        System.out.println();
        tampilLayananTertinggi(data, layanan);

        int idxHari = hariTersibuk(data);
        System.out.println("Hari tersibuk: Hari ke-" + (idxHari + 1));

        System.out.println("\n===== PENDAPATAN =====");
        tampilPendapatan(data, layanan, harga);

        sc.close();
    }
}