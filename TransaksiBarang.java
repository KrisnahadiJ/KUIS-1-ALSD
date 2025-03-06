import java.util.Scanner;

class TransaksiBarang {
    Scanner input = new Scanner(System.in);

    Barang11[] barangs;
    Barang11[] pembelians;
    int[] jumlahPembelian;

    public TransaksiBarang(Barang11[] barangs) {
        this.barangs = barangs;
        this.pembelians = new Barang11[barangs.length];
        this.jumlahPembelian = new int[barangs.length]; 
    }

    public void tampilkanBarang() {
        boolean isAda = false;

        System.out.println("\n\033[1;34m=====================================================");
        System.out.println("|                   Daftar Barang                   |");
        System.out.println("=====================================================");
        System.out.printf("| %-10s | %-15s | %-10s | %-5s |\n", "Kode", "Nama", "Harga", "Stok");
        System.out.println("=====================================================\033[0m");

        for (Barang11 barang : barangs) {
            if (barang != null) {
                System.out.printf("| %-10s | %-15s | %-10.2f | %-5d |\n",
                        barang.kode, barang.nama, barang.harga, barang.stok);
                isAda = true;
            }
        }

        if (!isAda) {
            System.out.println("|            Maaf, data barang kosong!             |");
        }

        System.out.println("\033[1;34m=====================================================\033[0m");
    }

    public void tampilkanPembelian() {
        System.out.println("\n\033[1;32m====================================================================");
        System.out.println("|                           List Belanjaan                         |");
        System.out.println("====================================================================");
        System.out.printf("| %-10s | %-15s | %10s | %5s | %12s |\n", "Kode", "Nama", "Harga", "Qty", "Total");
        System.out.println("====================================================================\033[0m");
    
        boolean isAdaPembelian = false;
        double totalHargaSemua = 0;
    
        for (int i = 0; i < pembelians.length; i++) {
            if (pembelians[i] != null) {
                double totalHarga = pembelians[i].harga * jumlahPembelian[i];
                System.out.printf("| %-10s | %-15s | %,10.2f | %5d | %,12.2f |\n",
                        pembelians[i].kode, pembelians[i].nama, pembelians[i].harga, jumlahPembelian[i], totalHarga);
                totalHargaSemua += totalHarga;
                isAdaPembelian = true;
            }
        }
    
        if (!isAdaPembelian) {
            System.out.println("|                    Belum ada barang dibeli                 |");
        } else {
            System.out.println("\033[1;32m====================================================================");
            System.out.printf("| %-35s | %,12.2f |\n", "Total Belanja:", totalHargaSemua);
        }
        System.out.println("====================================================================\033[0m");
    }
        
    public void melakukanPembelian() {
        tampilkanBarang();

        while (true) {
            System.out.print("\nMasukkan kode barang: ");
            String kodeBarang = input.nextLine();
            boolean barangDitemukan = false;

            for (int i = 0; i < barangs.length; i++) {
                if (barangs[i] != null && barangs[i].kode.equals(kodeBarang)) {
                    barangDitemukan = true;

                    if (barangs[i].stok > 0) {
                        int jumlah = 0;
                        while (true) {
                            System.out.print("Masukkan jumlah yang ingin dibeli: ");
                            if (input.hasNextInt()) {
                                jumlah = input.nextInt();
                                input.nextLine();
                                if (jumlah > 0 && jumlah <= barangs[i].stok) {
                                    break;
                                } else {
                                    System.out.println("Jumlah tidak valid atau stok tidak mencukupi.");
                                }
                            } else {
                                System.out.println("Input tidak valid! Harap masukkan angka.");
                                input.nextLine();
                            }
                        }

                        barangs[i].stok -= jumlah;

                        boolean sudahDibeli = false;
                        for (int j = 0; j < pembelians.length; j++) {
                            if (pembelians[j] != null && pembelians[j].kode.equals(kodeBarang)) {
                                jumlahPembelian[j] += jumlah;
                                sudahDibeli = true;
                                break;
                            }
                        }

                        if (!sudahDibeli) {
                            for (int j = 0; j < pembelians.length; j++) {
                                if (pembelians[j] == null) {
                                    pembelians[j] = barangs[i];
                                    jumlahPembelian[j] = jumlah;
                                    break;
                                }
                            }
                        }

                        System.out.println(
                                "Pembelian berhasil: " + barangs[i].nama + " sebanyak " + jumlah);
                        tampilkanPembelian();
                    } else {
                        System.out.println("Barang " + kodeBarang + " stok habis.");
                    }
                }
            }

            if (!barangDitemukan) {
                System.out.println("Barang " + kodeBarang + " tidak ditemukan.");
            }

            String lanjut;
            while (true) {
                System.out.print("Apakah akan belanja kembali (Y/N)? : ");
                lanjut = input.nextLine();

                if (lanjut.equalsIgnoreCase("Y") || lanjut.equalsIgnoreCase("N")) {
                    break;
                }
                System.out.println("Input tidak valid! Harap masukkan 'Y' atau 'N'.");
            }

            if (lanjut.equalsIgnoreCase("N")) {
                break;
            }
        }
    }
}