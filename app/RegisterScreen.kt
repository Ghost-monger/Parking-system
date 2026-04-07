@Composable
fun RegisterScreen() {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }


    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1A237E),
            Color(0xFF283593),
            Color(0xFF3949AB)
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {

            Column(
                modifier = Modifier
                .padding(horizontal = 28.dp, vertical = 36.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier.size(140.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                        .shadow(4.dp, CircleShape)
                )
                Text(
                    text = "Register Here", fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
                Spacer(modifier = Modifier.height(28.dp))




                OutlinedTextField(
                    value = username,
                    label = { Text(text = "Enter Username") },
                    onValueChange = { username = it },
                    placeholder = { Text(text = "Please Enter Username") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(28.dp))

                OutlinedTextField(
                    value = email,
                    label = { Text(text = "Enter Your Email") },
                    onValueChange = { email = it },
                    placeholder = { Text(text = "Enter Email") },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(28.dp))

                OutlinedTextField(
                    value = password,
                    label = { Text(text = "Enter Password") },
                    onValueChange = { password = it },
                    placeholder = { Text(text = "Please Enter Password") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = if (passwordVisible) "Hide password" else "Show password"
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(28.dp))

                OutlinedTextField(
                    value = confirmpassword,
                    label = { Text(text = "Confirm Pasword") },
                    onValueChange = { confirmpassword = it },
                    placeholder = { Text(text = "") },
                    leadingIcon = { Icon(Icons.Default.Check, contentDescription = null) },
                    trailingIcon = {
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(
                                imageVector = if (confirmPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = null
                            )
                        }
                    },
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(28.dp))

                Button(onClick = {},
                     modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3949AB)
                    )
                ) { Text(text = "Register")}
                Row() {
                    Text(text = "Already Registered?", color = Color.Blue)
                    Text(text = "Login Here", color = Color.Red)
                }

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}