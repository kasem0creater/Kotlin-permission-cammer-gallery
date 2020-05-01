package com.redev.grantpermissiondemo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // array permission
    private val CAMERA_PERMISSION = arrayOf(Manifest.permission.CAMERA , Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private val GALLERY_PERMISSION = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

    //request code permission
    private val REQUEST_CAMERA = 100
    private val REQUEST_GALLERY = 200


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        checkPermissionCamera()

        checkPermissionGallery()
    }

    private fun checkPermissionGallery() {
        btn_gallery.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this@MainActivity , Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissionGallery()
            }
            else
            {
                Toast.makeText(this@MainActivity , "User permission grant gallery",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun requestPermissionGallery() {
        ActivityCompat.requestPermissions(this , GALLERY_PERMISSION , REQUEST_GALLERY)
    }

    private fun checkPermissionCamera() {
        btn_camera.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this , Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissionCamera()
            }
            else
            {
                Toast.makeText(this@MainActivity , "User permission grant camera",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun requestPermissionCamera() {
        ActivityCompat.requestPermissions(this , CAMERA_PERMISSION , REQUEST_CAMERA)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == REQUEST_CAMERA)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
             Toast.makeText(this@MainActivity , "User permission grant camera",Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(this@MainActivity , "User permission grant camera error",Toast.LENGTH_LONG).show()
            }
        }
        else if(requestCode == REQUEST_GALLERY)
        {
            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED )
            {
                Toast.makeText(this@MainActivity , "User permission grant gallery",Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(this@MainActivity , "User permission grant gallery error",Toast.LENGTH_LONG).show()
            }
        }
    }
}
