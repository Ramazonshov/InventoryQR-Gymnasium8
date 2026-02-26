package com.example.inventoryqr;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EquipmentDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_detail);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvInvNumber = findViewById(R.id.tvInvNumber);
        TextView tvModel = findViewById(R.id.tvModel);
        TextView tvCabinet = findViewById(R.id.tvCabinet);
        TextView tvStatus = findViewById(R.id.tvStatus);

        // Получаем QR-код из Intent
        String qrCode = getIntent().getStringExtra("QR_CODE");

        // Тестовые данные (потом будет база)
        Equipment equipment = findEquipmentByQr(qrCode);

        if (equipment != null) {
            tvName.setText("Наименование: " + equipment.getName());
            tvInvNumber.setText("Инв. номер: " + equipment.getInvNumber());
            tvModel.setText("Модель: " + equipment.getModel());
            tvCabinet.setText("Кабинет: " + equipment.getCabinet());
            tvStatus.setText("Статус: " + equipment.getStatus());
        } else {
            Toast.makeText(this, "Оборудование не найдено", Toast.LENGTH_LONG).show();
            finish();
        }

        // Кнопки пока просто показывают сообщение
        Button btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(v -> Toast.makeText(this, "Редактирование (пока не реализовано)", Toast.LENGTH_SHORT).show());

        Button btnRepair = findViewById(R.id.btnRepairRequest);
        btnRepair.setOnClickListener(v -> Toast.makeText(this, "Заявка на ремонт создана", Toast.LENGTH_SHORT).show());
    }

    // Временная "база" — потом заменим на SQLite / Firebase
    private Equipment findEquipmentByQr(String qrCode) {
        if ("TEST123".equals(qrCode)) {
            return new Equipment("TEST123", "Компьютер Lenovo ThinkCentre", "INV-0456", "M75q Gen 5", "Каб. 205", "Работает");
        } else if ("MONITOR789".equals(qrCode)) {
            return new Equipment("MONITOR789", "Монитор Dell P2422H", "INV-0789", "P2422H", "Каб. 112", "В ремонте");
        }
        return null;
    }
}