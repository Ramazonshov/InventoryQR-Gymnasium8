package com.example.inventoryqr;  // свой package

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.journeyapps.barcodescanner.CaptureActivity;

public class DashboardActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SCAN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button btnScanQR = findViewById(R.id.btnScanQR);
        Button btnEquipmentList = findViewById(R.id.btnEquipmentList);
        btnEquipmentList.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, EquipmentListActivity.class);
            startActivity(intent);
        });
        Button btnAddEquipment = findViewById(R.id.btnAddEquipment);
        btnAddEquipment.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, AddEquipmentActivity.class);
            startActivity(intent);
        });
        Button btnReports = findViewById(R.id.btnReports);
        btnReports.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ReportsActivity.class);
            startActivity(intent);
        });

        btnScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Запускаем сканер QR-кода
                Intent intent = new Intent(DashboardActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
            }
        });

        // Пока оставляем другие кнопки без действий
    }

    // Обрабатываем результат сканирования
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String qrResult = data.getStringExtra("SCAN_RESULT");
                if (qrResult != null && !qrResult.isEmpty()) {
                    // Переходим на карточку оборудования
                    Intent intent = new Intent(DashboardActivity.this, EquipmentDetailActivity.class);
                    intent.putExtra("QR_CODE", qrResult);
                    startActivity(intent);
                }
            }
        }
    }
}