package com.example.datastoreapp.DataStorePreference

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.datastoreapp.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClear.setOnClickListener {
            binding.edName.text = null
            binding.edAge.text = null
        }

        val dataStorePreferenceManger = DataStorePreferenceManger(this)
        binding.btnGet.setOnClickListener {
            binding.edName.setText(dataStorePreferenceManger.name)
            binding.edAge.setText(dataStorePreferenceManger.age.toString())
        }


        binding.btnSet.setOnClickListener {
            if (binding.edName.text.toString().isEmpty()){
                binding.edName.error = "Required"
            }else if (binding.edAge.text.toString().isEmpty()){
                binding.edAge.error = "Required"
            }else{
                dataStorePreferenceManger.name = binding.edName.text.toString()
                dataStorePreferenceManger.age = binding.edAge.text.toString().toInt()

                Toast.makeText(this, "Values Saved to data store", Toast.LENGTH_SHORT).show()

                binding.edName.text = null
                binding.edAge.text = null
            }
        }

    }

}