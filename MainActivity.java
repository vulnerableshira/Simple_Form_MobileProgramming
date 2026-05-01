package com.example.revision;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.Spinner;
import android.widget.RadioGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText txtFullName, txtEmail, txtAddress, txtPassword;
    Spinner spJurusan;
    RadioGroup rgSemester;
    CheckBox cbAgree;
    TextView tvResult, tvErrorJurusan, tvErrorSemester, tvErrorAgreement;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFullName = findViewById(R.id.txt_fullname);
        txtEmail = findViewById(R.id.txt_email);
        txtAddress = findViewById(R.id.txt_address);
        txtPassword = findViewById(R.id.txt_password);
        spJurusan = findViewById(R.id.spJurusan);
        rgSemester = findViewById(R.id.rgSemester);
        cbAgree = findViewById(R.id.cbAgree);
        tvResult = findViewById(R.id.tv_result);

        tvErrorJurusan = findViewById(R.id.tv_error_jurusan);
        tvErrorSemester = findViewById(R.id.tv_error_semester);
        tvErrorAgreement = findViewById(R.id.tv_error_agreement);

        btnRegister = findViewById(R.id.btn_register);

        String[] jurusanList = {
                "-- Pilih Jurusan --",
                "Teknik Informatika",
                "Sistem Informasi",
                "Ilmu Komputer"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                jurusanList
        );
        spJurusan.setAdapter(adapter);

        btnRegister.setOnClickListener(v -> {

            String fullName = txtFullName.getText().toString().trim();
            String email = txtEmail.getText().toString().trim();
            String address = txtAddress.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();

            int selectedSemesterId = rgSemester.getCheckedRadioButtonId();
            String jurusan = spJurusan.getSelectedItem().toString();

            tvErrorJurusan.setVisibility(View.GONE);
            tvErrorSemester.setVisibility(View.GONE);
            tvErrorAgreement.setVisibility(View.GONE);

            if (fullName.isEmpty() || email.isEmpty() || address.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isValid = true;

            if (jurusan.equals("-- Pilih Jurusan --")) {
                tvErrorJurusan.setVisibility(View.VISIBLE);
                isValid = false;
            }

            if (selectedSemesterId == -1) {
                tvErrorSemester.setVisibility(View.VISIBLE);
                isValid = false;
            }

            if (!cbAgree.isChecked()) {
                tvErrorAgreement.setVisibility(View.VISIBLE);
                isValid = false;
            }

            if (!isValid) return;

            RadioButton rbSemester = findViewById(selectedSemesterId);
            String semester = rbSemester.getText().toString();

            String result =
                    "Data yang Didaftarkan:\n\n" +
                            "Nama     : " + fullName +
                            "\nEmail    : " + email +
                            "\nAddress  : " + address +
                            "\nJurusan  : " + jurusan +
                            "\nSemester : " + semester;

            tvResult.setText(result);
        });

    }
}