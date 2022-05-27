package com.example.aplicacaoisaque.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.example.aplicacaoisaque.R
import java.text.SimpleDateFormat
import java.util.*


class TelaEditarDados : AppCompatActivity() {

    private var sexo:Boolean = false
    lateinit var sharedP: SharedPreferences
    lateinit var ed_data: EditText
    lateinit var ed_peso: EditText
    lateinit var ed_altura: EditText
    lateinit var radioMas: RadioButton
    lateinit var radioFem: RadioButton
    lateinit var bt_salvar: Button
    lateinit var calendar: Calendar
    lateinit var layout: NestedScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_editar_dados)

        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
        getSupportActionBar()!!.setHomeButtonEnabled(true)      //Ativar o botão
        getSupportActionBar()!!.setTitle("Editar Dados")

        sharedP = getSharedPreferences("KEY", MODE_PRIVATE)

        // *---------------------------------*
        // | INICIALIZANDO OS COMPONENTES   |
        // *---------------------------------*

        layout = findViewById(R.id.nestedScroll)
        ed_data = findViewById(R.id.editText_data_nasc)
        ed_peso = findViewById(R.id.editText_peso)
        ed_altura = findViewById(R.id.editText_altura)
        bt_salvar = findViewById(R.id.btn_concluir)
        radioMas = findViewById(R.id.radio_masc)
        radioFem = findViewById(R.id.radio_fem)

        // *---------------------------------*
        // | Desabilitando os componentes    |
        // *---------------------------------*
        ed_data.isEnabled = false
        ed_peso.isEnabled = false
        ed_altura.isEnabled = false
        bt_salvar.isEnabled = false
        radioFem.isEnabled = false
        radioMas.isEnabled = false

        // Retira o focus dos campos quando inicia a activity
        layout.requestFocus()

        readData()

    }

    private fun readCalendar(view: View){

        calendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateEdit(calendar)

        }
        DatePickerDialog(this, datePicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun updateEdit(calendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        ed_data.setText(sdf.format(calendar.time))

    }

    private fun readData(){

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

   private fun editarDados(view: View){

        ed_data.isEnabled = true
        ed_peso.isEnabled = true
        ed_altura.isEnabled = true
        bt_salvar.isEnabled = true

        radioFem.isEnabled = true
        radioMas.isEnabled = true

    }

    private fun saveData(view: View){

       try {

           if (!ed_peso.text.isEmpty() ){ // Verificando se o campo EditText PESO está vazio
               if( !ed_data.text.isEmpty() ){ // Verificando se o campo EditText DATA está vazio
                   if(!ed_altura.text.isEmpty() ){ // Verificando se o campo EditText ALTURA está vazio

                       // SALVANDO DADOS NAS PREFERÊNCIAS DO ANDROID
                       sharedP.edit().putString("data", ed_data.text.toString()).apply()
                       sharedP.edit().putString("peso", ed_peso.text.toString()).apply()
                       sharedP.edit().putString("altura", ed_altura.text.toString()).apply()

                       if (radioFem.isChecked){
                           sharedP.edit().putInt("sexo", 1).apply()
                       }else if(radioMas.isChecked){
                           sharedP.edit().putInt("sexo", 0).apply()
                       }

                       startActivity(intent)

                   }else{
                       ed_altura.error = "Insira a sua altura*"
                   }
               }else{
                   ed_data.error = "Insira sua data de nascimento*"
               }
           }else{
               ed_peso.error = "Insira seu peso*"
           }

       }catch (e: Exception){
           e.printStackTrace()
           Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_SHORT).show()
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


     private fun onRadioButtonClicked(view: View){
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
        onDestroy()
        finish()
    }

}
