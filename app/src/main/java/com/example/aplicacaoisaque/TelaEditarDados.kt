package com.example.aplicacaoisaque

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class TelaEditarDados : AppCompatActivity() {

    var sexo:Boolean = false
    ///var stateSexo: Int = 3
    lateinit var sharedP: SharedPreferences
    lateinit var ed_data: EditText
    lateinit var ed_peso: EditText
    lateinit var ed_altura: EditText
    lateinit var radioMas: RadioButton
    lateinit var radioFem: RadioButton
    lateinit var bt_salvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_editar_dados)

        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
        getSupportActionBar()!!.setHomeButtonEnabled(true)      //Ativar o botão
        getSupportActionBar()!!.setTitle("Editar Dados")

        sharedP = getSharedPreferences("KEY", MODE_PRIVATE)

        ed_data = findViewById<EditText>(R.id.editText_data_nasc)
        ed_peso = findViewById<EditText>(R.id.editText_peso)
        ed_altura = findViewById<EditText>(R.id.editText_altura)

        bt_salvar = findViewById<Button>(R.id.btn_concluir)
        radioMas = findViewById<RadioButton>(R.id.radio_masc)
        radioFem = findViewById<RadioButton>(R.id.radio_fem)

        ed_data.isEnabled = false
        ed_peso.isEnabled = false
        ed_altura.isEnabled = false
        bt_salvar.isEnabled = false

        radioFem.isEnabled = false
        radioMas.isEnabled = false

        lerDados()

    }

    fun lerDados(){

        // Recuperando dados salvos
        val d = sharedP.getString("data", "")
        val p = sharedP.getString("peso", "")
        val a = sharedP.getString("altura", "")
        val rg = sharedP.getInt("sexo", 3)

        // Verifica qual radiobutton foi checado.
        if(rg == 0){
            radioMas.isChecked = true
        }
        else if(rg == 1){
            radioFem.isChecked = true
        }

        ed_altura.setText(a)
        ed_data.setText(d)
        ed_peso.setText(p)

    }

    fun editarDados(view: View){

        ed_data.isEnabled = true
        ed_peso.isEnabled = true
        ed_altura.isEnabled = true
        bt_salvar.isEnabled = true

        radioFem.isEnabled = true
        radioMas.isEnabled = true
        // Validação de dados

    }

    fun salvarDados(view: View){

        sharedP.edit().putString("data", ed_data.text.toString()).apply()
        sharedP.edit().putString("peso", ed_peso.text.toString()).apply()
        sharedP.edit().putString("altura", ed_altura.text.toString()).apply()

        if (radioFem.isChecked){
            sharedP.edit().putInt("sexo", 1).apply()
        }else if(radioMas.isChecked){
            sharedP.edit().putInt("sexo", 0).apply()
        }


        startActivity(intent)
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


    fun onRadioButtonClicked(view: View){
        if(view is RadioButton) {
            //Verificando se o botão foi checkado
            val checked = view.isChecked

            when(view.getId()){
                R.id.radio_masc ->
                    if( checked ){
                        sexo = true
                    }
                R.id.radio_fem ->
                    if( checked ){
                        sexo = true
                    }
            }
        }
    }
    override fun onBackPressed() {
        startActivity(Intent(applicationContext, TelaInicial::class.java))
    }

}
