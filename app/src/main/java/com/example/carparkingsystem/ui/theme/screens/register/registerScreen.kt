package com.example.carparkingsystem.ui.theme.screens.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.carparkingsystem.R
import com.example.carparkingsystem.data.AuthViewModel
import com.example.carparkingsystem.navigation.ROUTE_LOGIN

// ── Reuse the same colour tokens from Dashboard ────────────
val NavyDark   = Color(0xFF0A0F1E)
private val NavyMid    = Color(0xFF0D1630)
private val NavyCard   = Color(0xFF131D3B)
private val NavyBorder = Color(0xFF1E2D5A)
val Amber      = Color(0xFFF5C842)
private val TextMuted  = Color(0xFF7A8AB5)
val TextDim    = Color(0xFF5A6A90)

@Composable
fun RegisterScreen(navController: NavController) {
    var username             by remember { mutableStateOf("") }
    var email                by remember { mutableStateOf("") }
    var password             by remember { mutableStateOf("") }
    var confirmpassword      by remember { mutableStateOf("") }
    var passwordVisible      by remember { mutableStateOf(false) }
    var confirmPwVisible     by remember { mutableStateOf(false) }
    var phonenumber          by remember { mutableStateOf("") }
    val authViewModel: AuthViewModel = viewModel()
    val context = LocalContext.current


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NavyDark),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            // ── Logo circle ────────────────────────────
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(NavyCard)
                    .then(
                        Modifier.clip(CircleShape)
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Replace with your actual Image composable:
                // Image(painter = painterResource(R.drawable.logo), ...)
                Text("🅿", fontSize = 36.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Create Account",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Amber
            )
            Text(
                text = "Join ParkSmart — manage parking smarter",
                fontSize = 12.sp,
                color = TextDim,
                modifier = Modifier.padding(bottom = 28.dp)
            )

            // ── Card ───────────────────────────────────
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = NavyMid),
                border = BorderStroke(1.dp, NavyBorder)
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 28.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    // Username
                    ParkField(
                        label = "USERNAME",
                        value = username,
                        onValueChange = { username = it },
                        placeholder = "Enter username",
                        leadingIcon = { Icon(Icons.Default.Person, null, tint = TextDim) }
                    )

                    // Email
                    ParkField(
                        label = "EMAIL",
                        value = email,
                        onValueChange = { email = it },
                        placeholder = "Enter email address",
                        leadingIcon = { Icon(Icons.Default.Email, null, tint = TextDim) }
                    )

                    ParkField(
                        label = "PHONE NUMBER",
                        value = phonenumber,
                        onValueChange = { phonenumber = it },
                        placeholder = "Enter your phone number",
                        leadingIcon = { Icon(Icons.Default.Phone, null, tint = TextDim) }
                    )

                    // Password
                    ParkField(
                        label = "PASSWORD",
                        value = password,
                        onValueChange = { password = it },
                        placeholder = "Create a password",
                        leadingIcon = { Icon(Icons.Default.Lock, null, tint = TextDim) },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Default.VisibilityOff
                                    else Icons.Default.Visibility,
                                    contentDescription = null,
                                    tint = TextDim
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None
                        else PasswordVisualTransformation()
                    )

                    // Confirm Password
                    ParkField(
                        label = "CONFIRM PASSWORD",
                        value = confirmpassword,
                        onValueChange = { confirmpassword = it },
                        placeholder = "Repeat your password",
                        leadingIcon = { Icon(Icons.Default.Check, null, tint = TextDim) },
                        trailingIcon = {
                            IconButton(onClick = { confirmPwVisible = !confirmPwVisible }) {
                                Icon(
                                    imageVector = if (confirmPwVisible) Icons.Default.VisibilityOff
                                    else Icons.Default.Visibility,
                                    contentDescription = null,
                                    tint = TextDim
                                )
                            }
                        },
                        visualTransformation = if (confirmPwVisible) VisualTransformation.None
                        else PasswordVisualTransformation()
                    )

                    // Register button
                    Button(
                        onClick = {
                            authViewModel.signup(
                                username = username,
                                email = email,
                                phonenumber = phonenumber,
                                password = password,
                                confirmpassword = confirmpassword,
                                navController = navController,
                                context = context,
                             )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Amber,
                            contentColor = NavyDark
                        )
                    ) {
                        Text(
                            text = "Register",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Login link
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Already registered? ", color = TextDim, fontSize = 13.sp)
                        Text(
                            text = "Login here",
                            color = Amber,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.clickable {
                                navController.navigate(ROUTE_LOGIN)
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

// ── Reusable themed text field ──────────────────────────────
@Composable
fun ParkField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column {
        Text(
            text = label,
            color = TextMuted,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.6.sp,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color(0xFF3A4A6A), fontSize = 14.sp) },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Amber,
                unfocusedBorderColor = NavyBorder,
                focusedContainerColor = NavyCard,
                unfocusedContainerColor = NavyCard,
                focusedTextColor = Color(0xFFE8EEFF),
                unfocusedTextColor = Color(0xFFE8EEFF),
                cursorColor = Amber
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(navController = rememberNavController())
}