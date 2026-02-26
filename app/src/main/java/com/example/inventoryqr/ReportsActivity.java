package com.example.inventoryqr;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportsActivity extends AppCompatActivity {

    private TextView tvTotal, tvByStatus, tvByCabinet;
    private Button btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        tvTotal = findViewById(R.id.tvTotal);
        tvByStatus = findViewById(R.id.tvByStatus);
        tvByCabinet = findViewById(R.id.tvByCabinet);
        btnRefresh = findViewById(R.id.btnRefresh);

        updateReports();

        btnRefresh.setOnClickListener(v -> updateReports());
    }

    private void updateReports() {
        List<Equipment> list = EquipmentRepository.getAll();

        int total = list.size();

        Map<String, Integer> statusCount = new HashMap<>();
        statusCount.put("Работает", 0);
        statusCount.put("В ремонте", 0);
        statusCount.put("Списано", 0);

        Map<String, Integer> cabinetCount = new HashMap<>();

        for (Equipment eq : list) {
            String status = eq.getStatus();
            statusCount.put(status, statusCount.getOrDefault(status, 0) + 1);

            String cabinet = eq.getCabinet();
            cabinetCount.put(cabinet, cabinetCount.getOrDefault(cabinet, 0) + 1);
        }

        // Общее количество
        tvTotal.setText("Общее количество: " + total);

        // По статусам
        StringBuilder statusText = new StringBuilder("По статусам:\n");
        for (Map.Entry<String, Integer> entry : statusCount.entrySet()) {
            statusText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        tvByStatus.setText(statusText.toString());

        // По кабинетам (топ-5, отсортируем по убыванию)
        StringBuilder cabinetText = new StringBuilder("По кабинетам (топ):\n");
        cabinetCount.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(5)
                .forEach(e -> cabinetText.append(e.getKey()).append(": ").append(e.getValue()).append(" шт.\n"));

        tvByCabinet.setText(cabinetText.toString());
    }
}