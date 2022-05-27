package com.example.aplicacaoisaque.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.aplicacaoisaque.R

class TelaSobre : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_sobre)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
        getSupportActionBar()!!.setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar()!!.setTitle("Sobre");

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //Botão adicional na ToolBar
        when (item.getItemId()) {
            android.R.id.home -> {
                startActivity(
                    Intent(
                        this,
                        TelaInicial::class.java
                    )
                ) //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity() //Método para matar a activity e não deixa-lá indexada na pilhagem
            }
            else -> {}
        }
        return true
    }
    override fun onBackPressed() {
        finish()
        startActivity(Intent(applicationContext, TelaInicial::class.java))
    }
}