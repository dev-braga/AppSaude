package com.example.aplicacaoisaque

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class TelaInicial : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        var btn_monitorar = findViewById<CardView>(R.id.btn_monitoramento)
        var btn_usuario = findViewById<CardView>(R.id.btn_usuario)
        var btn_config = findViewById<CardView>(R.id.btn_configuracao)
        var btn_historico = findViewById<CardView>(R.id.btn_historico)
        var btn_sair = findViewById<CardView>(R.id.btn_logout)
        var btn_sobre = findViewById<CardView>(R.id.btn_Sobre)

        btn_monitorar.setOnClickListener(clickListener)
        btn_usuario.setOnClickListener(clickListener)
        btn_config.setOnClickListener(clickListener)
        btn_historico.setOnClickListener(clickListener)
        btn_sair.setOnClickListener(clickListener)
        btn_sobre.setOnClickListener(clickListener)


    }

    private val clickListener: View.OnClickListener = View.OnClickListener { view ->
        when(view.id){
            R.id.btn_usuario -> goToPerfil()
            R.id.btn_monitoramento -> goToMonitoramento()
            R.id.btn_configuracao -> goToConfig()
            R.id.btn_Sobre -> gotToSobre()
            R.id.btn_historico -> goToHistorico()
            R.id.btn_logout -> goToSair()
        }
    }

    private fun showAlert(){

        AlertDialog.Builder(this@TelaInicial)
            .setTitle("Alerta")
            .setMessage("Em desenvolvimento...")
            .setPositiveButton("OK"){
                d,_->
                d.dismiss()
            }
            .show()

    }

    private fun goToPerfil(){
        startActivity(Intent(applicationContext, TelaEditarDados::class.java))
    }

    private fun gotToSobre(){
        startActivity(Intent(applicationContext, TelaSobre::class.java))
    }
    private fun goToConfig(){
        startActivity(Intent(applicationContext, TelaConfiguracao::class.java))
    }
    private fun goToMonitoramento(){
        showAlert()
    }
    private fun goToHistorico(){
        showAlert()
    }
    private fun goToSair(){
        finish()
    }

    override fun onBackPressed() {
        finish()
    }
}