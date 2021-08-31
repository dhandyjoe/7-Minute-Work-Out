package com.example.a7minuteworkout.activity

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkout.database.Constants
import com.example.a7minuteworkout.model.ExcerciseModel
import com.example.a7minuteworkout.adapter.ExcerciseStatusAdapter
import com.example.a7minuteworkout.R
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.dialog_back_pressed.*
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var excerciseList: ArrayList<ExcerciseModel>? = null
    private var currentPosition = -1

    private var speech: TextToSpeech? = null
    private var media: MediaPlayer? = null

    private var excerciseAdapter: ExcerciseStatusAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setSupportActionBar(toolbar_excerise_activity)
        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        toolbar_excerise_activity.setNavigationOnClickListener {
            dialogBackPress()
        }

        speech = TextToSpeech(this, this)

        excerciseList = Constants.defaultExcerciseList()

        setupRest()
        setupExcerciseStatus()
    }

    override fun onDestroy() {
        if(restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }

        if(exerciseTimer != null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        if(speech != null){
            speech!!.stop()
            speech!!.shutdown()
        }

        if(media != null) {
            media!!.stop()
        }

        super.onDestroy()
    }

    private fun setRestProgressBar(){
        progres_bar.progress = restProgress
        restTimer = object : CountDownTimer(10000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progres_bar.progress = 10 - restProgress
                tvTimer.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                restProgress = 0
                currentPosition++
                excerciseList!![currentPosition].setIsSelected(true)
                excerciseAdapter!!.notifyDataSetChanged()

                setupExercise()
            }
        }.start()
    }

    private fun setExcersiceProgressBar(){
        progres_bar2.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(5000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                progres_bar2.progress = 5 - exerciseProgress
                tvTimer2.text = (5 - exerciseProgress).toString()
            }

            override fun onFinish() {
                exerciseProgress = 0
                if(currentPosition < excerciseList?.size!! - 1) {
                    excerciseList!![currentPosition].setIsSelected(false)
                    excerciseList!![currentPosition].setIsCompleted(true)
                    excerciseAdapter!!.notifyDataSetChanged()
                    setupRest()
                } else {
                    finish()
                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }

    private fun setupExercise(){
        ll_RestView.visibility = View.INVISIBLE
        ll_RestView2.visibility = View.VISIBLE

        speakOut(excerciseList!![currentPosition].getName())

        setExcersiceProgressBar()
        ivExcercise.setImageResource(excerciseList!![currentPosition].getImage())
        tvExcersiceName.text = excerciseList!![currentPosition].getName()
    }

    private fun setupRest(){
        media = MediaPlayer.create(applicationContext, R.raw.press_start)
        media!!.isLooping = false
        media!!.start()

        ll_RestView.visibility = View.VISIBLE
        ll_RestView2.visibility = View.INVISIBLE

        tvUpComing.text = excerciseList!![currentPosition+1].getName()
        setRestProgressBar()


    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS) {
            val result = speech!!.setLanguage(Locale.ENGLISH)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("SPEECH", "The languange not supported!")
            }
        } else {
            Log.e("SPEECH", "Failed!")
        }
    }

    private fun speakOut (teks: String) {
        speech!!.speak(teks, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun setupExcerciseStatus () {
        rv_Excercise.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        excerciseAdapter = ExcerciseStatusAdapter(this, excerciseList!!)
        rv_Excercise.adapter = excerciseAdapter
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