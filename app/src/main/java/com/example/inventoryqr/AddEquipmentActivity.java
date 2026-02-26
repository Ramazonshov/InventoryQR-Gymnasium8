package com.example.inventoryqr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddEquipmentActivity extends AppCompatActivity {

    private EditText etName, etInvNumber, etModel, etCabinet;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_equipment);

        etName = findViewById(R.id.etName);
        etInvNumber = findViewById(R.id.etInvNumber);
        etModel = findViewById(R.id.etModel);
        etCabinet = findViewById(R.id.etCabinet);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> saveEquipment());
    }

    private void saveEquipment() {
        String name = etName.getText().toString().trim();
        String invNumber = etInvNumber.getText().toString().trim();
        String model = etModel.getText().toString().trim();
        String cabinet = etCabinet.getText().toString().trim();

        if (name.isEmpty() || invNumber.isEmpty()) {
            Toast.makeText(this, "Заполните хотя бы наименование и инвентарный номер", Toast.LENGTH_SHORT).show();
            return;
        }

        String qrCode = invNumber;  // QR-код = инвентарный номер (можно изменить логику)

        Equipment newEquipment = new Equipment(qrCode, name, invNumber, model.isEmpty() ? "-" : model,
                cabinet.isEmpty() ? "-" : cabinet, "Работает");

        // Сохраняем через репозиторий
        EquipmentRepository.add(newEquipment);

        Toast.makeText(this, "Оборудование добавлено! QR: " + qrCode, Toast.LENGTH_LONG).show();

        // Переход к экрану с QR
        Intent intent = new Intent(AddEquipmentActivity.this, QrDisplayActivity.class);
        intent.putExtra("QR_CODE", qrCode);
        startActivity(intent);

        finish();  // закрываем форму
    }
}