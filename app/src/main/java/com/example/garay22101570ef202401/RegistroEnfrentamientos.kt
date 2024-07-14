package com.example.garay22101570ef202401

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class RegistroEnfrentamientos : AppCompatActivity() {

    private lateinit var spinnerLocalTeam: Spinner
    private lateinit var spinnerVisitorTeam: Spinner
    private lateinit var editTextWinLocal: EditText
    private lateinit var editTextDraw: EditText
    private lateinit var editTextWinVisitor: EditText
    private lateinit var buttonRegister: Button
    private lateinit var buttonTeams: Button
    private lateinit var buttonListMatches: Button

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_enfrentamientos)

        // Habilitar el manejo de bordes a borde (edge-to-edge)
        enableEdgeToEdge()

        // Vincular vistas desde el layout
        spinnerLocalTeam = findViewById(R.id.srEquipoLocal)
        spinnerVisitorTeam = findViewById(R.id.srEquipoVisitante)
        editTextWinLocal = findViewById(R.id.etLocal)
        editTextDraw = findViewById(R.id.etEmpate)
        editTextWinVisitor = findViewById(R.id.etVisita)
        buttonRegister = findViewById(R.id.btnRegistrar2)
        buttonTeams = findViewById(R.id.btnRegistroEquipos)
        buttonListMatches = findViewById(R.id.btnListEnfrent)


        buttonRegister.setOnClickListener {
            registerMatch()
        }


        buttonTeams.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // Configurar el botón para redireccionar a la pantalla de listado de enfrentamientos (Caso 03)
        buttonListMatches.setOnClickListener {
            // Aquí deberías implementar la navegación hacia la pantalla de listado de enfrentamientos
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun enableEdgeToEdge() {
        // Implementación para habilitar el modo edge-to-edge en dispositivos compatibles
        // Aquí puedes añadir la lógica específica según las necesidades de tu aplicación
        // Por ejemplo, manejar el comportamiento de la barra de navegación y status bar
    }

    private fun registerMatch() {
        val localTeam = spinnerLocalTeam.selectedItem.toString()
        val visitorTeam = spinnerVisitorTeam.selectedItem.toString()
        val winLocal = editTextWinLocal.text.toString().toDoubleOrNull() ?: 0.0
        val draw = editTextDraw.text.toString().toDoubleOrNull() ?: 0.0
        val winVisitor = editTextWinVisitor.text.toString().toDoubleOrNull() ?: 0.0

        val match = hashMapOf(
            "localTeam" to localTeam,
            "visitorTeam" to visitorTeam,
            "winLocal" to winLocal,
            "draw" to draw,
            "winVisitor" to winVisitor
        )

        db.collection("matches")
            .add(match)
            .addOnSuccessListener {
                // Registro exitoso, podrías mostrar un mensaje de confirmación si lo deseas
            }
            .addOnFailureListener { e ->
                // Manejar errores durante el registro, por ejemplo, mostrar un mensaje de error
            }
    }
}
