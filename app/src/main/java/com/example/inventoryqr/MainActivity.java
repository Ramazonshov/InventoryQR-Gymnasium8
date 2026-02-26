package com.example.inventoryqr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Находим элементы с экрана по их id
        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Обработчик нажатия на кнопку "Войти"
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = etLogin.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Простая проверка (для начала, потом заменим на нормальную авторизацию)
                if (login.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Введите логин и пароль", Toast.LENGTH_SHORT).show();
                } else if (login.equals("admin") && password.equals("12345")) {
                    Toast.makeText(MainActivity.this, "Вход выполнен!", Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();  // Закрываем экран логина, чтобы не возвращаться назад

                    // Здесь позже будет переход на главный экран
                    // Пока просто сообщение
                } else {
                    Toast.makeText(MainActivity.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}