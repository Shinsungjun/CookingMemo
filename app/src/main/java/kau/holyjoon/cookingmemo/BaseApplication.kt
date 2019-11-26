package kau.holyjoon.cookingmemo

import android.app.Activity
import android.app.Application
import androidx.appcompat.app.AppCompatDialog
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView


class BaseApplication : Application(){
    var baseApplication:BaseApplication? = null
    var progressDialog:AppCompatDialog? = null

    fun getInstance():BaseApplication{
        return baseApplication!!
    }
    override fun onCreate(){
        super.onCreate()
        baseApplication = this
    }

    fun progressON(activity: Activity, message:String){
        if(activity == null || activity.isFinishing()){
            return
        }

        if (progressDialog != null && progressDialog!!.isShowing()) {
            progressSET(message)
        }
        else{
            val progressDialog = AppCompatDialog(activity)
            progressDialog.setCancelable(false)
            progressDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            progressDialog.setContentView(R.layout.loading_dialog)
            progressDialog.show()


        }
        val img_loading_frame:ImageView? = progressDialog!!.findViewById<ImageView>(R.id.iv_frame_loading)
        val frameAnimation: AnimationDrawable = img_loading_frame!!.getBackground() as AnimationDrawable
        img_loading_frame.post(Runnable(){
            fun run(){
                frameAnimation.start()
            }

        })
        val tv_progress_message: TextView? = progressDialog!!.findViewById(R.id.tv_progress_message)
        if(!TextUtils.isEmpty(message)){
            tv_progress_message!!.setText(message)
        }
    }
    fun progressSET(message:String){
        if(progressDialog==null||!progressDialog!!.isShowing()){
            return
        }
        val tv_progress_message:TextView? = progressDialog!!.findViewById<TextView>(R.id.tv_progress_message)
        if(!TextUtils.isEmpty(message)){
            tv_progress_message!!.setText(message)
        }
    }
    fun progressOFF(){
        if(progressDialog !=null && progressDialog!!.isShowing()){
            progressDialog!!.dismiss()
        }
    }
}