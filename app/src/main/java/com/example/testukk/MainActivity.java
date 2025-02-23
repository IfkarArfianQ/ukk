package com.example.testukk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText harga,diskon;
    TextView hargasetelahdiskon,hargadiskon;

    Button hitungdiskon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        harga = findViewById(R.id.harga);
        diskon = findViewById(R.id.diskon);
        hargasetelahdiskon = findViewById(R.id.hargasetelahdiskon);
        hargadiskon = findViewById(R.id.hargadiskon);
        hitungdiskon = findViewById(R.id.hitungdiskon);

        hitungdiskon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTotalDiskon();
            }
        });
    }

    public void setTotalDiskon() {
        try {
            int hargaAwal = Integer.parseInt(harga.getText().toString());
            double diskonPersen = Double.parseDouble(diskon.getText().toString());

            // Cek diskon valid
            if (diskonPersen > 100) {
                diskon.setError("Diskon Tidak Boleh Lebih Dari 100%");
                return;
            }

            // Hitung diskon dan total harga
            int potonganHarga = (int) (hargaAwal * (diskonPersen / 100));
            int totalHarga = hargaAwal - potonganHarga;

            // Format ke dalam mata uang Rupiah
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

            // Tampilkan hasil
            hargadiskon.setText(formatRupiah.format(potonganHarga));
            hargasetelahdiskon.setText(formatRupiah.format(totalHarga));

        } catch (NumberFormatException e) {
            // Validasi input angka
            harga.setError("Masukkan angka yang valid");
            diskon.setError("Masukkan diskon yang valid");
        }
    }
}