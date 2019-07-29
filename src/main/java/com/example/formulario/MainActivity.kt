package com.example.formulario

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var sexo = "Masculino"
    private var hobbies = ""
    private var lugar_nacimiento = ""
    private var fecha = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // -------------------------- Button --------------------------------
        b_guardar.setOnClickListener {
            var nombre = et_nombre.text.toString()
            var contraseña = et_contraseña.text.toString()
            var repetir_contraseña = et_repetir_contraseña.text.toString()
            var correo = et_correo.text.toString()

            if(cb_deportes.isChecked)
                hobbies += " Deportes"
            if(cb_pasear.isChecked)
                hobbies += " Pasear"
            if(cb_pintar.isChecked)
                hobbies += " Pintar"
            if(cb_bailar.isChecked)
                hobbies += " Bailar"

            var informacion = "Nombre: " + nombre + "\n" + "Contraseña: " + contraseña + "\n" + "Correo electrónico: " + correo + "\n" + "Sexo: " + sexo + "\n" + "Hobbies:" + hobbies + "\n" + "Fecha Naci: " + fecha + "\n" + "Lugar Naci: " + lugar_nacimiento
            if(nombre=="" || contraseña=="" || repetir_contraseña=="" || correo=="" || sexo=="" || hobbies=="" || fecha=="" || lugar_nacimiento==""){
                Toast.makeText(this, "HAY CAMPOS QUE NO SE HAN LLENADO", Toast.LENGTH_LONG).show()
                tv_informacion.text = ""
            }else
                if(contraseña == repetir_contraseña){
                    tv_informacion.text = informacion
                    hobbies = ""
                }else
                    Toast.makeText(this, "LAS CONTRASEÑAS NO COINCIDEN", Toast.LENGTH_LONG).show()
        }
        //-------------------------------------------------------------------

        //-------------------------------- CALENDAR ----------------------------------
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Button click to show DatePickerDialog
        b_fecha.setOnClickListener {
            val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view, mYear, mMonth, mDay ->
                // Set to TextView
                fecha = "" + mDay + "/" + (mMonth+1) + "/" + mYear
                tv_fecha.text = fecha
            }, year, month, day)
            // Show dialog
            dpd.show()
        }
        //-------------------------------------------------------------------------------

        // ---------------------------------- SPINNER -----------------------------------
        val adapter = ArrayAdapter.createFromResource(this, R.array.ciudades, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        sp_lugar.adapter = adapter
        lugar_nacimiento = sp_lugar.selectedItem.toString()
        // ------------------------------------------------------------------------------
    }

    //--------------------------- RadioButton --------------------------------------
    fun OnRadioButtonClicked(view: View) {

        if (view is RadioButton) {

            when (view.id) {
                R.id.rb_masculino ->
                    if (view.isChecked) {
                        sexo = "Masculino"
                    }
                R.id.rb_femenino ->
                    if (view.isChecked) {
                        sexo = "Femenino"
                    }
            }
        }
    }
    //---------------------------------------------------------------------------------
}