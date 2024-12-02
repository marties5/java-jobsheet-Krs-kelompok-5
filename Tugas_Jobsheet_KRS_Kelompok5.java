import java.util.Scanner;

public class Tugas_Jobsheet_KRS_Kelompok5 {
    

    //Function yang digunakan untuk tambah KRS
    public static void tambahDataKRS(String[][] krsArray, int[] index) {
        Scanner input = new Scanner(System.in);

        System.out.print("Nama Mahasiswa: ");
        String namaMahasiswa = input.nextLine();

        System.out.print("NIM: ");
        String NIM = input.nextLine();

        System.out.print("Kode Mata Kuliah: ");
        String kodeMatkul = input.nextLine();

        System.out.print("Nama Mata Kuliah: ");
        String namaMatkul = input.nextLine();

        int jumlahSKS;
        while (true) {
            System.out.print("Jumlah SKS (1-3 per mata kuliah): ");
            jumlahSKS = input.nextInt();
            if (jumlahSKS >= 1 && jumlahSKS <= 3) {
                break;
            } else {
                System.out.println("Jumlah SKS harus antara 1 hingga 3.");
            }
        }

        int totalSKS = hitungTotalSKS(krsArray, index[0], NIM);
        if (totalSKS + jumlahSKS > 24) {
            System.out.println("Total SKS mahasiswa melebihi batas 24.");
        } else {
            krsArray[index[0]][0] = namaMahasiswa;
            krsArray[index[0]][1] = NIM;
            krsArray[index[0]][2] = kodeMatkul;
            krsArray[index[0]][3] = namaMatkul;
            krsArray[index[0]][4] = Integer.toString(jumlahSKS);
            index[0]++;
            System.out.println("Data KRS berhasil ditambahkan.");
        }
    }

    
    public static int hitungTotalSKS(String[][] krsArray, int index, String NIM) {
        int total = 0;
        for (int i = 0; i < index; i++) {
            if (krsArray[i][1].equals(NIM)) {
                total += Integer.parseInt(krsArray[i][4]);
            }
        }
        return total;
    }

    
    public static void tampilkanKRS(String[][] krsArray, int index, String NIM) {
        int totalSKS = 0;
        System.out.println("Daftar KRS Mahasiswa dengan NIM: " + NIM);
        for (int i = 0; i < index; i++) {
            if (krsArray[i][1].equals(NIM)) {
                System.out.println("Mata Kuliah: " + krsArray[i][3] + " | SKS: " + krsArray[i][4]);
                totalSKS += Integer.parseInt(krsArray[i][4]);
            }
        }
        System.out.println("Total SKS: " + totalSKS);
    }

    
    public static void analisisMahasiswa(String[][] krsArray, int index) {
        int count = 0;
        for (int i = 0; i < index; i++) {
            String currentNIM = krsArray[i][1];
            int totalSKS = hitungTotalSKS(krsArray, index, currentNIM);
            if (totalSKS < 20) {
                boolean sudahDihitung = false;
                for (int j = 0; j < i; j++) {
                    if (krsArray[j][1].equals(currentNIM)) {
                        sudahDihitung = true;
                        break;
                    }
                }
                if (!sudahDihitung) {
                    count++;
                }
            }
        }
        System.out.println("Jumlah mahasiswa dengan SKS kurang dari 20: " + count);
    }

    
    public static void tampilkanMenu() {
        String[][] krsArray = new String[100][5];
        int[] index = { 0 }; 

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menu Utama ===");
            System.out.println("1. Tambah Data KRS");
            System.out.println("2. Tampilkan KRS Mahasiswa");
            System.out.println("3. Analisis Mahasiswa dengan SKS < 20");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");

            int pilihan = input.nextInt();
            input.nextLine(); 

            switch (pilihan) {
                case 1:
                    tambahDataKRS(krsArray, index);
                    break;
                case 2:
                    System.out.print("Masukkan NIM untuk melihat KRS: ");
                    String NIM = input.nextLine();
                    tampilkanKRS(krsArray, index[0], NIM);
                    break;
                case 3:
                    analisisMahasiswa(krsArray, index[0]);
                    break;
                case 4:
                    System.out.println("Terima kasih! Program selesai.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public static void main(String[] args) {
        tampilkanMenu();
    }
}
