package com.example.a7minuteworkout.activity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.a7minuteworkout.R
import kotlinx.android.synthetic.main.activity_b_m_i.*
import kotlinx.android.synthetic.main.activity_exercise.toolbar_excerise_activity
import kotlinx.android.synthetic.main.dialog_back_pressed.*

class BMIActivity : AppCompatActivity() {

    val METRIC_UNIT = "METRIC_UNIT"
    val US_UNIT = "US_UNIT"

    var currentVisibleView = "METRIC_UNIT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_m_i)

        setSupportActionBar(toolbar_excerise_activity)
        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Calculate BMI"
        }

        toolbar_excerise_activity.setNavigationOnClickListener {
            dialogBackPress()
        }

        makeMetricUnits()
        rgUnits.setOnCheckedChangeListener { radioGroup, i ->
            if(i == R.id.rb_MetricUnit){
                makeMetricUnits()
            } else {
                makeUSUnits()
            }
        }

        btn_calculate.setOnClickListener {
            if(currentVisibleView.equals(METRIC_UNIT)) {
                var isEmpty = false
                if (et_weightMetric.text.toString().isEmpty()) {
                    isEmpty = true
                    et_weightMetric.error = "Field ini harus diisi"
                }
                if (et_heightMetric.text.toString().isEmpty()) {
                    isEmpty = true
                    et_heightMetric.error = "Field ini harus diisi"
                }

                if (!isEmpty) {
                    val weight = et_weightMetric.text.toString().toFloat()
                    val height = et_heightMetric.text.toString().toFloat() / 100
                    val bmi = weight / (height * height)
                    displayScore(bmi)
                }
            } else {
                var isEmpty = false
                if (et_weightUS.text.toString().isEmpty()) {
                    isEmpty = true
                    et_weightMetric.error = "Field ini harus diisi"
                }
                if (et_feet.text.toString().isEmpty()) {
                    isEmpty = true
                    et_heightMetric.error = "Field ini harus diisi"
                }
                if (et_inch.text.toString().isEmpty()) {
                    isEmpty = true
                    et_heightMetric.error = "Field ini harus diisi"
                }

                if(!isEmpty){
                    val weightUS = et_weightUS.text.toString().toFloat()
                    val feet = et_feet.text.toString().toFloat()
                    val inch = et_inch.text.toString().toFloat()

                    val heightValue = inch + feet * 12
                    val bmi = 703 * (weightUS / (heightValue * heightValue))
                    displayScore(bmi)
                }
            }
        }
    }

    private fun makeMetricUnits () {
        currentVisibleView = METRIC_UNIT
        llMetricUnit.visibility = View.VISIBLE
        llUsUnit.visibility = View.INVISIBLE
    }

    private fun makeUSUnits () {
        currentVisibleView = US_UNIT
        llUsUnit.visibility = View.VISIBLE
        llMetricUnit.visibility = View.INVISIBLE
    }

    private fun displayScore(bmi : Float) {
        val bmiLabel: String
        val bmiDescription: String

        if(bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severly underweight"
            bmiDescription = "You really need to take better care of yourself"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {
            bmiLabel = "Severly underweight"
            bmiDescription = "You really need to take better care of yourself"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {
            bmiLabel = "Underweight"
            bmiDescription = "You really need to take better care of yourself"
        } else if (bmi.compareTo(18.5) > 0 && bmi.compareTo(25f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {
            bmiLabel = "Overweight"
            bmiDescription = "You really need to take better care of yourself"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            bmiLabel = "Obese Class 1"
            bmiDescription = "You really need to take better care of yourself"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            bmiLabel = "Obese Class 2"
            bmiDescription = "OMG!! You are in a very dangerous condition!!"
        } else {
            bmiLabel = "Obese Class 3"
            bmiDescription = "OMG!! You are in a very dangerous condition!!"
        }

        tv_scoreBMI.text = bmi.toString()
        tv_scoreBMI_us.text = bmi.toString()

        tv_status.text = bmiLabel
        tv_status_us.text = bmiLabel

        tv_message.text = bmiDescription
        tv_message_us.text = bmiDescription
    }

    private fun dialogBackPress () {
        val dialog = Dialog(this)

        dialog.setContentView(R.layout.dialog_back_pressed)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.tvYes.setOnClickListener {
            finish()
            dialog.dismiss()
        }
        dialog.tvNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}