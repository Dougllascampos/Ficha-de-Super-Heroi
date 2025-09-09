package com.seu.pacote.herois.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.seu.pacote.herois.R
import com.seu.pacote.herois.ui.model.HeroProfile

class FichaHeroiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ficha_heroi)

        val imgAvatar = findViewById<ImageView>(R.id.imgAvatarFinal)
        val txtCodinome = findViewById<TextView>(R.id.txtCodinomeValor)
        val txtAlinhamento = findViewById<TextView>(R.id.txtAlinhamentoValor)
        val txtPoderes = findViewById<TextView>(R.id.txtPoderesValor)

        val profile = intent.getParcelableExtra<HeroProfile>(CriacaoHeroiActivity.EXTRA_PROFILE)

        val btnEditar = findViewById<Button>(R.id.btnEditar)

        val root = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.rootFicha)

        profile?.let {
            imgAvatar.setImageResource(it.avatarResId)
            txtCodinome.text = it.codinome
            txtAlinhamento.text = it.alinhamento
            txtPoderes.text = if (it.poderes.isEmpty()) "Nenhum" else it.poderes.joinToString(", ")

            val cor = when (it.alinhamento) {
                "Herói" -> R.color.bg_heroi
                "Vilão" -> R.color.bg_vilao
                "Anti-herói" -> R.color.bg_antiheroi
                else -> android.R.color.white
            }
            root.setBackgroundResource(cor)
        }


        btnEditar.setOnClickListener {
            profile?.let {
                val itn = Intent(this, CriacaoHeroiActivity::class.java)
                itn.putExtra(CriacaoHeroiActivity.EXTRA_PROFILE, it)
                startActivity(itn)
            }
        }


        profile?.let {
            imgAvatar.setImageResource(it.avatarResId)
            txtCodinome.text = it.codinome
            txtAlinhamento.text = it.alinhamento
            txtPoderes.text = if (it.poderes.isEmpty()) "Nenhum" else it.poderes.joinToString(", ")
        }
    }
}
