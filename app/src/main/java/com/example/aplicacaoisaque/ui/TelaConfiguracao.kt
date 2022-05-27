package com.example.aplicacaoisaque.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import com.example.aplicacaoisaque.R

class TelaConfiguracao : AppCompatActivity() {

    lateinit var radiokm: RadioButton
    lateinit var radioms: RadioButton
    lateinit var radioCaminhada: RadioButton
    lateinit var radioCorrida: RadioButton
    lateinit var radioBicicleta: RadioButton
    lateinit var radioNenhuma: RadioButton
    lateinit var radioNorthUp: RadioButton
    lateinit var radioCourseUp: RadioButton
    lateinit var radioVetorial: RadioButton
    lateinit var radioSatelite: RadioButton

    lateinit var sharedP: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_configuracao)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
        getSupportActionBar()!!.setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar()!!.setTitle("Configurações");

        // Inicializando o SharedPreferences
        sharedP = getSharedPreferences("SETTINGS", MODE_PRIVATE)

        // Inicializando os componentes
        radiokm = findViewById(R.id.radio_km)
        radioms = findViewById(R.id.radio_ms)
        radioCaminhada = findViewById(R.id.radio_caminhada)
        radioCorrida = findViewById(R.id.radio_corrida)
        radioBicicleta = findViewById(R.id.radio_bicicleta)
        radioNenhuma = findViewById(R.id.radio_nenhuma)
        radioNorthUp = findViewById(R.id.radio_nothup)
        radioCourseUp = findViewById(R.id.radio_courseup)
        radioVetorial = findViewById(R.id.radio_vetorial)
        radioSatelite = findViewById(R.id.radio_satelite)

        // Função para ler os dados salvos
        readData()

    }

    private fun readData() {

        val radiogroup1 = sharedP.getInt("settings1", 0)
        val radiogroup2 = sharedP.getInt("settings2", 0)
        val radiogroup3 = sharedP.getInt("settings3", 0)
        val radiogroup4 = sharedP.getInt("settings4", 0)

        when ( radiogroup1 ){
            1 -> {
                radiokm.isChecked = true
            }
            2 -> {
                radioms.isChecked = true
            }
        }

        when (radiogroup2 ){
            3 -> {
                radioCaminhada.isChecked = true
            }
            4 -> {
                radioCorrida.isChecked = true
            }
            5 -> {
                radioBicicleta.isChecked = true
            }
        }

        when ( radiogroup3 ){
            6 -> {
                radioNenhuma.isChecked = true
            }
            7 -> {
                radioNorthUp.isChecked = true
            }
            8 -> {
                radioCourseUp.isChecked = true
            }
        }

        when (radiogroup4 ){
            9 -> {
                radioVetorial.isChecked = true
            }
            10 -> {
                radioSatelite.isChecked = true
            }
        }


    }

    private fun saveData(){

        if( radiokm.isChecked ){
            sharedP.edit().putInt("settings1", 1).apply()
        }
        if( radioms.isChecked){
            sharedP.edit().putInt("settings1", 2).apply()
        }
        if( radioCaminhada.isChecked){
            sharedP.edit().putInt("settings2", 3).apply()
        }
        if( radioCorrida.isChecked){
            sharedP.edit().putInt("settings2", 4).apply()
        }
        if( radioBicicleta.isChecked){
            sharedP.edit().putInt("settings2", 5).apply()
        }
        if( radioNenhuma.isChecked){
            sharedP.edit().putInt("settings3", 6).apply()
        }
        if( radioNorthUp.isChecked){
            sharedP.edit().putInt("settings3", 7).apply()
        }
        if( radioCourseUp.isChecked){
            sharedP.edit().putInt("settings3", 8).apply()
        }
        if( radioVetorial.isChecked){
            sharedP.edit().putInt("settings4", 9).apply()
        }
        if( radioSatelite.isChecked){
            sharedP.edit().putInt("settings4", 10).apply()
        }


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

    fun onRadioSelect(view: View){
        if(view is RadioButton) {
            //Verificando se o botão foi checkado
            val checked = view.isChecked

            when(view.getId()){

                //Unidade de Velocidade
                R.id.radio_km ->
                    if( checked ){

                    }
                R.id.radio_ms ->
                    if( checked ){

                    }
                // Tipo de Exercicio
                R.id.radio_caminhada ->
                    if( checked ){

                    }
                R.id.radio_corrida ->
                    if( checked ){

                    }
                R.id.radio_bicicleta ->
                    if( checked ){
            //Orientação do Mapa
                    }
                R.id.radio_nenhuma ->
                    if( checked ){

                    }
                R.id.radio_nothup ->
                    if( checked ){

                    }
                R.id.radio_courseup ->
                    if( checked ){
            //Tipo do Mapa
                    }
                R.id.radio_vetorial ->
                    if( checked ){

                    }
                R.id.radio_satelite ->
                    if( checked ){

                    }
            }
        }
    }

    fun btn_concluir(view: View){

        saveData()
        startActivity(Intent(applicationContext, TelaInicial::class.java))
    }

    override fun onBackPressed() {
        startActivity(Intent(applicationContext, TelaInicial::class.java))
        finish()
    }

}