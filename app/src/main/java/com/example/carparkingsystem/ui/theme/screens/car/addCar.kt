package com.example.carparkingsystem.ui.theme.screens.car

import android.R.attr.padding
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.carparkingsystem.data.AuthViewModel
import com.example.carparkingsystem.data.CarViewModel
import com.example.carparkingsystem.ui.theme.screens.login.TextDim
import com.example.carparkingsystem.ui.theme.screens.register.Amber
import com.example.carparkingsystem.ui.theme.screens.register.NavyDark
import com.example.carparkingsystem.ui.theme.screens.register.ParkField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCarScreen(navController: NavController) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
        ){uri: Uri? ->
        imageUri = uri
    }
    var plate_number by remember { mutableStateOf("") }
    var vehicle_type by remember { mutableStateOf("") }
    var driver_name by remember { mutableStateOf("") }
    var phone_number by remember { mutableStateOf("") }
    val CarViewModel: CarViewModel = viewModel()
    val context = LocalContext.current

    Scaffold(topBar = {TopAppBar(title = {Text("Add Car Details")},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = Color.White))}) { padding->
        Column(modifier = Modifier.padding(padding)
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center)

        {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ){
                if (imageUri != null){
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else{
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(120.dp),
                        tint = Color.Gray

                    )
                }
            }
            Button(onClick = {
                launcher.launch("image/*")
            }, modifier = Modifier.align(Alignment.CenterHorizontally))
                { Text("Select Image")}
            ParkField(
                label = "PLATE NUMBER",
                value =plate_number,
                onValueChange = { plate_number = it },
                placeholder = "ABC4535",
                leadingIcon = { Icon(Icons.Default.Tag, null, tint = TextDim) }
            )
            ParkField(
                label = "VEHICLE TYPE",
                value = vehicle_type,
                onValueChange = { vehicle_type = it },
                placeholder = "Enter vehicle type",
                leadingIcon = { Icon(Icons.Default.DirectionsCar, null, tint = TextDim) }
            )
            ParkField(
                label = "DRIVER NAME",
                value = driver_name,
                onValueChange = { driver_name = it },
                placeholder = "enter driver name",
                leadingIcon = { Icon(Icons.Default.Person2, null, tint = TextDim) }
            )
            ParkField(
                label = "PHONE NUMBER",
                value = phone_number,
                onValueChange = { phone_number = it },
                placeholder = "Enter your phone number",
                leadingIcon = { Icon(Icons.Default.Phone, null, tint = com.example.carparkingsystem.ui.theme.screens.register.TextDim) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    CarViewModel.uploadCar(
                        plate_number = plate_number,
                        vehicle_type = vehicle_type,
                        driver_name = driver_name,
                        phone_number = phone_number,
                        navController = navController,
                        context = context,
                        imageUri = imageUri
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
                    text = "Save Car",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddCarScreenPreview() {
    AddCarScreen(rememberNavController())
}