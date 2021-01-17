package org.atex.app.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.realm.mongodb.Credentials
import kotlinx.android.synthetic.main.activity_login.*
import org.atex.app.R
import org.atex.app.TAG
import org.atex.app.atexApp

/*
 * Developed by Hung Pham.
 * Email: admin@yomemo.com || numerotech@gmail.com.
 * Copyright (c) 2019. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *       https://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class LoginActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var createUserButton: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        username = findViewById(R.id.input_username)
        password = findViewById(R.id.input_password)
        loginButton = findViewById(R.id.button_login)
        createUserButton = findViewById(R.id.text_register)


        input_password.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                signIn()
                true
            } else {
                false
            }
        }
        loginButton.setOnClickListener {
            Log.d(TAG(), "loginButton")
            signIn()
        }
        createUserButton.setOnClickListener {
            create()
        }
    }

    private fun validateCredentials(): Boolean = when {
        username.text.toString().isEmpty() -> false
        password.text.toString().isEmpty() -> false
        else -> true
    }

    // Sign In
    private fun signIn() {
        if (!validateCredentials()) {
            onLoginFailed("Invalid username or password")
            return
        }
        createUserButton.isEnabled = false
        loginButton.isEnabled = false
        val username = this.username.text.toString()
        val password = this.password.text.toString()
        val creds = Credentials.emailPassword(username, password)
        atexApp.loginAsync(creds) {
            loginButton.isEnabled = true
            createUserButton.isEnabled = true
            if (it.isSuccess) {
                onLoginSuccess()
            } else {
                onLoginFailed("Wrong email or password: " + it.error.message)
            }
        }
    }

    private fun create() {

    }

    private fun onLoginSuccess() {
        Log.d(TAG(), "onLoginSuccess")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun onLoginFailed(errorMsg: String) {
        Log.e(TAG(), errorMsg)
        Toast.makeText(baseContext, errorMsg, Toast.LENGTH_LONG).show()
    }


}