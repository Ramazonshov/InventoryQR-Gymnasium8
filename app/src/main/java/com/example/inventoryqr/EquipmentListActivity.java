package com.example.inventoryqr;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EquipmentListActivity extends AppCompatActivity {

    private RecyclerView rvEquipmentList;
    private EditText etSearch;
    private EquipmentAdapter adapter;
    private List<Equipment> equipmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_list);

        rvEquipmentList = findViewById(R.id.rvEquipmentList);
        etSearch = findViewById(R.id.etSearch);

        // Получаем общий список из репозитория
        equipmentList = EquipmentRepository.getAll();

        // Добавляем тестовые данные один раз, если список пуст
        EquipmentRepository.initTestDataIfEmpty();

        adapter = new EquipmentAdapter(equipmentList);
        rvEquipmentList.setLayoutManager(new LinearLayoutManager(this));
        rvEquipmentList.setAdapter(adapter);

        // Поиск в реальном времени
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}