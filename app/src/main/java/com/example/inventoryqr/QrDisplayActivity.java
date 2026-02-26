package com.example.inventoryqr;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QrDisplayActivity extends AppCompatActivity {

    private ImageView ivQRCode;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_display);  // ← обязательно должен быть создан layout!

        ivQRCode = findViewById(R.id.ivQRCode);
        btnBack = findViewById(R.id.btnBack);

        // Получаем содержимое QR из предыдущего экрана
        String qrContent = getIntent().getStringExtra("QR_CODE");

        if (qrContent == null || qrContent.trim().isEmpty()) {
            Toast.makeText(this, "QR-код не передан", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        try {
            // Генерируем QR-код размером 600×600 пикселей
            Bitmap qrBitmap = generateQRCode(qrContent, 600, 600);
            ivQRCode.setImageBitmap(qrBitmap);
        } catch (WriterException e) {
            Toast.makeText(this, "Ошибка генерации QR-кода: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (Exception e) {
            Toast.makeText(this, "Неизвестная ошибка: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        // Кнопка возврата
        btnBack.setOnClickListener(v -> finish());
    }

    /**
     * Генерирует Bitmap с QR-кодом
     * @param text текст, который будет закодирован в QR
     * @param width ширина в пикселях
     * @param height высота в пикселях
     * @return Bitmap с QR-кодом или null при ошибке
     */
    private Bitmap generateQRCode(String text, int width, int height) throws WriterException {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bitmap;
    }
}