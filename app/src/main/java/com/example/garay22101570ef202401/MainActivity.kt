package com.example.garay22101570ef202401

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTeamName: EditText
    private lateinit var editTextTeamUrl: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonRegisterMatches: Button
    private lateinit var buttonListMatches: Button

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        editTextTeamName = findViewById(R.id.etNombreEquipo)
        editTextTeamUrl = findViewById(R.id.etURL)
        buttonSave = findViewById(R.id.btnGuardar)
        buttonRegisterMatches = findViewById(R.id.btnRegistrar)
        buttonListMatches = findViewById(R.id.btnListado)

        buttonSave.setOnClickListener {
            saveTeam()
        }

        buttonRegisterMatches.setOnClickListener {
            val intent = Intent(this, RegistroEnfrentamientos::class.java)
            startActivity(intent)
        }

        buttonListMatches.setOnClickListener {
            // Redireccionar a la pantalla de listado de enfrentamientos
        }
    }

    private fun saveTeam() {
        val teamName = editTextTeamName.text.toString()
        val teamUrl = editTextTeamUrl.text.toString()

        if (teamName.isEmpty() || teamUrl.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val team = hashMapOf(
            "name" to teamName,
            "url" to teamUrl
        )

        db.collection("teams")
            .add(team)
            .addOnSuccessListener {
                Toast.makeText(this, "Equipo guardado", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al guardar el equipo", Toast.LENGTH_SHORT).show()
            }
    }
}
