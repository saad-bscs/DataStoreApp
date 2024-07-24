package com.example.datastoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.datastoreapp.MyApp.Companion.dataStore
import com.example.datastoreapp.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var txtAge = 0
    var txtName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSaveUser.setOnClickListener {
            if (isValidate()) {
                GlobalScope.launch {
                    txtName = binding.tfName.text.toString()
                    txtAge = binding.tfAge.text.toString().toInt()
                    Timber.e("--Save to data Store--")
                    UserManager().saveUserPreferences(this@MainActivity, txtName, txtAge)

                    Timber.e("-Extract from data Store-")

                    var userPref: Flow<UserPreferences> = UserManager().getUserPreferences(this@MainActivity)
                    userPref.collect {
                        binding.lblName.text = it.userName
                        binding.lblAge.text = it.userAge.toString()
                    }
                }


            }
        }

    }

    private fun isValidate(): Boolean {

        var returnValue = true
        var message = ""

        if (binding.tfAge.text.isEmpty()) {
            message = "Please enter age."
            returnValue = false
        }

        if (binding.tfName.text.isEmpty()) {
            message = "Please enter name."
            returnValue = false
        }

        if (!returnValue) {
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }

        return returnValue
    }

    /*fun saveUser(user: User) {
        sharedPref.edit {
            putString(PREF_FIRST_NAME, user.firstName)
            putString(PREF_LAST_NAME, user.lastName)
            putLong(PREF_BIRTH_DAY, user.birthDay)
        }
    }*/

    /*fun getUser(): User {
        val firstName = sharedPref.getString(PREF_FIRST_NAME, "")
        val lastName = sharedPref.getString(PREF_LAST_NAME, "")
        val birthDay = sharedPref.getLong(PREF_BIRTH_DAY, 0)

        return User(firstName = firstName ?: "", lastName = lastName ?: "", birthDay = birthDay)
    }*/
}