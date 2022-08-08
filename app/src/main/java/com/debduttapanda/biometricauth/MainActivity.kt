package com.debduttapanda.biometricauth

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.debduttapanda.biometricauth.ui.theme.BioMetricAuthTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            BioMetricAuthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text("Biometric Authentication")
                                }
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ){
                            TextButton(
                                onClick = {
                                    val result = Biometric.status(context)
                                    Toast.makeText(context, if(result) "Available" else "Not Available", Toast.LENGTH_SHORT).show()
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    "Availability Status"
                                )
                            }
                            TextButton(
                                onClick = {
                                    Biometric.authenticate(
                                        this@MainActivity,
                                        title = "Biometric Authentication",
                                        subtitle = "Authenticate to proceed",
                                        description = "Authentication is must",
                                        negativeText = "Cancel",
                                        onSuccess = {
                                            runOnUiThread {
                                                Toast.makeText(
                                                    context,
                                                    "Authenticated successfully",
                                                    Toast.LENGTH_SHORT
                                                )
                                                    .show()
                                            }
                                        },
                                        onError = {errorCode,errorString->
                                            runOnUiThread {
                                                Toast.makeText(
                                                    context,
                                                    "Authentication error: $errorCode, $errorString",
                                                    Toast.LENGTH_SHORT
                                                )
                                                    .show()
                                            }
                                        },
                                        onFailed = {
                                            runOnUiThread {
                                                Toast.makeText(
                                                    context,
                                                    "Authentication failed",
                                                    Toast.LENGTH_SHORT
                                                )
                                                    .show()
                                            }
                                        }
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    "Authenticate"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}