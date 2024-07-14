package com.example.garay22101570ef202401

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class EnfrentamientoActivity : AppCompatActivity() {

    private lateinit var btnRegisEquip: Button
    private lateinit var btnRegisEnfren: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enfrentamiento)

        // Inicializar botones desde el layout
        btnRegisEquip = findViewById(R.id.btnRegisEquip)
        btnRegisEnfren = findViewById(R.id.btnRegisEnfren)

        // Configurar el botón para redireccionar a la pantalla de registro de equipos (Caso 01)
        btnRegisEquip.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Configurar el botón para redireccionar a la pantalla de registro de enfrentamientos (Caso 02)
        btnRegisEnfren.setOnClickListener {
            val intent = Intent(this, RegistroEnfrentamientos::class.java)
            startActivity(intent)
        }

    }
}
