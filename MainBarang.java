import java.util.Scanner;

public class MainBarang {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Barang11[] barangs = new Barang11[10];
        barangs[0] = new Barang11("B001", "Pensil", 2000, 50);
        barangs[1] = new Barang11("B002", "Buku", 5000, 30);
        barangs[2] = new Barang11("B003", "Penghapus", 1000, 100);
        barangs[3] = new Barang11("B004", "Penggaris", 3000, 40);
        barangs[4] = new Barang11("B005", "Bolpoin", 2500, 60);
        
        TransaksiBarang transaksiBarang = new TransaksiBarang(barangs);
        
        while (true) {
            System.out.println("\033[1;36m===================================================");
            System.out.println("============== TOKO ATK JAYA POLNEMA ==============");
            System.out.println("===================================================\033[0m");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Lihat Barang");
            System.out.println("3. Transaksi Barang");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan kode barang: ");
                    String kode = scanner.nextLine();
                    System.out.print("Masukkan nama barang: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan harga barang: ");
                    double harga = scanner.nextDouble();
                    System.out.print("Masukkan stok barang: ");
                    int stok = scanner.nextInt();
                    scanner.nextLine();
                    tambahBarang(barangs, new Barang11(kode, nama, harga, stok));
                    break;
                case 2:
                    transaksiBarang.tampilkanBarang();
                    break;
                case 3:
                    transaksiBarang.melakukanPembelian();
                    break;
                case 4:
                    System.out.println("Keluar dari program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public static void tambahBarang(Barang11[] barangs, Barang11 barangBaru) {
        for (int i = 0; i < barangs.length; i++) {
            if (barangs[i] == null) {
                barangs[i] = barangBaru;
                System.out.println("Barang berhasil ditambahkan.");
                return;
            }
        }
        System.out.println("Gagal menambahkan barang. Kapasitas penuh.");
    }
}