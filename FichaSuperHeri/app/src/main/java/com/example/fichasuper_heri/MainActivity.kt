package com.seu.pacote.herois.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.seu.pacote.herois.R

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PREFS = "prefs_heroi"
        private const val KEY_CODINOME = "codinome"
        const val EXTRA_CODINOME = "extra_codinome"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logo = findViewById<ImageView>(R.id.imgLogo)
        val titulo = findViewById<TextView>(R.id.txtTitulo)
        val edtCodinome = findViewById<EditText>(R.id.edtCodinome)
        val btnCriar = findViewById<Button>(R.id.btnCriarPerfil)


        val prefs = getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val salvo = prefs.getString(KEY_CODINOME, "") ?: ""
        if (salvo.isNotBlank()) edtCodinome.setText(salvo)

        btnCriar.setOnClickListener {
            val codinome = edtCodinome.text.toString().trim()

            prefs.edit().putString(KEY_CODINOME, codinome).apply()

            val itn = Intent(this, CriacaoHeroiActivity::class.java)
            itn.putExtra(EXTRA_CODINOME, codinome)
            startActivity(itn)
        }
    }
}
