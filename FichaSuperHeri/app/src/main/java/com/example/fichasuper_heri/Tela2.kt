package com.seu.pacote.herois.ui

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.seu.pacote.herois.R
import com.seu.pacote.herois.ui.model.HeroProfile

class CriacaoHeroiActivity : AppCompatActivity() {

    private val avatares = listOf(
        R.drawable.avatar1,
        R.drawable.avatar2,
        R.drawable.avatar3,
        R.drawable.avatar4
    )
    private var avatarIndex = 0
    private lateinit var codinome: String

    companion object {
        const val EXTRA_PROFILE = "extra_profile"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criacao_heroi)

        codinome = intent.getStringExtra(MainActivity.EXTRA_CODINOME).orEmpty()

        val txtBemVindo = findViewById<TextView>(R.id.txtBemVindo)
        val rgAlinhamento = findViewById<RadioGroup>(R.id.rgAlinhamento)
        val cbVoo = findViewById<CheckBox>(R.id.cbVoo)
        val cbSuperForca = findViewById<CheckBox>(R.id.cbSuperForca)
        val cbTelepatia = findViewById<CheckBox>(R.id.cbTelepatia)
        val cbRajadas = findViewById<CheckBox>(R.id.cbRajadas)
        val cbVelocidade = findViewById<CheckBox>(R.id.cbVelocidade)
        val imgAvatar = findViewById<ImageView>(R.id.imgAvatar)
        val btnGerar = findViewById<Button>(R.id.btnGerarFicha)

        txtBemVindo.text = "Personalize o perfil de: $codinome"
        imgAvatar.setImageResource(avatares[avatarIndex])

        imgAvatar.setOnClickListener {
            avatarIndex = (avatarIndex + 1) % avatares.size
            imgAvatar.setImageResource(avatares[avatarIndex])
        }

        btnGerar.setOnClickListener {

            val alinhamento = when (rgAlinhamento.checkedRadioButtonId) {
                R.id.rbHeroi -> "Herói"
                R.id.rbVilao -> "Vilão"
                R.id.rbAntiHeroi -> "Anti-herói"
                else -> "Herói"
            }


            val poderes = mutableListOf<String>()
            if (cbVoo.isChecked) poderes += "Voo"
            if (cbSuperForca.isChecked) poderes += "Super-força"
            if (cbTelepatia.isChecked) poderes += "Telepatia"
            if (cbRajadas.isChecked) poderes += "Rajadas de Energia"
            if (cbVelocidade.isChecked) poderes += "Super-velocidade"

            val profile = HeroProfile(
                codinome = codinome,
                alinhamento = alinhamento,
                poderes = poderes,
                avatarResId = avatares[avatarIndex]
            )


            val itn = Intent(this, FichaHeroiActivity::class.java)
            itn.putExtra(EXTRA_PROFILE, profile)
            startActivity(itn)
        }
    }
}
