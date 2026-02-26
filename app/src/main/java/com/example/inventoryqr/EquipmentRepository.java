package com.example.inventoryqr;  // ← замени на свой package, если отличается

import java.util.ArrayList;
import java.util.List;

public class EquipmentRepository {
    private static final List<Equipment> equipmentList = new ArrayList<>();

    public static List<Equipment> getAll() {
        return equipmentList;
    }

    public static void add(Equipment equipment) {
        equipmentList.add(equipment);
    }

    // Если список пустой — добавляем тестовые данные один раз
    public static void initTestDataIfEmpty() {
        if (equipmentList.isEmpty()) {
            equipmentList.add(new Equipment("TEST123", "Компьютер Lenovo ThinkCentre", "INV-0456", "M75q Gen 5", "Каб. 205", "Работает"));
            equipmentList.add(new Equipment("MONITOR789", "Монитор Dell P2422H", "INV-0789", "P2422H", "Каб. 112", "В ремонте"));
            equipmentList.add(new Equipment("PRINTER001", "Принтер HP LaserJet Pro", "INV-0123", "M404n", "Каб. 101", "Работает"));
            // добавь ещё свои тестовые, если хочешь
        }
    }
}