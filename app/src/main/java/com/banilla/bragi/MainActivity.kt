package com.banilla.bragi

import android.app.Activity
import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var input_text: EditText
    lateinit var loadVideo: ImageButton
    lateinit var videoView: VideoView
    lateinit var buttonSend: Button
    lateinit var text_path: TextView
    lateinit var text_output: TextView

    companion object {
        private const val REQUEST_VIDEO = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        input_text = findViewById(R.id.input_text)
        loadVideo = findViewById(R.id.button_loadVideo)
        videoView = findViewById(R.id.videoView)
        buttonSend = findViewById(R.id.button_send)
        text_path = findViewById(R.id.text_path)
        text_output = findViewById(R.id.text_output)

        loadVideo.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "video/*"
            startActivityForResult(intent, REQUEST_VIDEO)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_VIDEO && resultCode == Activity.RESULT_OK) {
            val videoUri: Uri? = data?.data
            videoUri?.let {
                text_path.text = videoUri.path
                videoView.setVideoURI(it)
                videoView.start()
            }
        }
    }
}