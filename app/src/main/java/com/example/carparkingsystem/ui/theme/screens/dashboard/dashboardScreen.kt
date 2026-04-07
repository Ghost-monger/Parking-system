package com.example.carparkingsystem.ui.theme.screens.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalParking
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(navController: NavController) {
    val selectedItem = remember { mutableStateOf(0) }
    val DarkNavy = Color(0xFF0A0F1E)
    val CardNavy = Color(0xFF111827)
    val BorderNavy = Color(0xFF1E2A4A)
    val ElectricBlue = Color(0xFF4D9EFF)
    val SlotGreen = Color(0xFF1D9E75)
    val WarningAmber = Color(0xFFEF9F27)
    val DangerRed = Color(0xFFE24B4A)
    val TextPrimary = Color(0xFFE8F0FF)
    val TextMuted = Color(0xFF6B80A8)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "ParkIQ",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                },
                actions = {
                    TextButton(
                        onClick = {
                            // Clear session + navigate to login
                            navController.navigate("login") {
                                popUpTo(0) { inclusive = true }
                            }
                        },
                        border = BorderStroke(1.dp, DangerRed),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        Text(
                            text = "Log out",
                            color = DangerRed,
                            fontSize = 13.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0D1530)
                )
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.Red) {
                Row {
                    NavigationBarItem(
                        selected = selectedItem.value == 0,
                        onClick = { selectedItem.value = 0 },
                        icon = { Icon(Icons.Default.Home, null) },
                        label = { Text(text = "Home") }
                    )
                    NavigationBarItem(
                        selected = selectedItem.value == 1,
                        onClick = { selectedItem.value = 1 },
                        icon = { Icon(Icons.Default.Person, null) },
                        label = { Text(text = "Profile") }
                    )
                    NavigationBarItem(
                        selected = selectedItem.value == 2,
                        onClick = { selectedItem.value = 2 },
                        icon = { Icon(Icons.Default.Settings, null) },
                        label = { Text(text = "Settings") }
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {

            // ── ORIGINAL: Title ──
            Text(
                text = "Smart Parking System",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            // ── ORIGINAL: Available / Occupied summary card ──
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Red)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Available", color = Color.White)
                        Text(text = "18 Slots", fontSize = 20.sp, color = Color.White)
                    }
                    Column {
                        Text(text = "Occupied", color = Color.White)
                        Text(text = "32 Slots", fontSize = 20.sp, color = Color.White)
                    }
                }
            }

            // ── ORIGINAL: Add Car / View Cars cards ──
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    onClick = { /* TODO */ },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = Color(0xFF4CAF50),
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Add Car", color = Color.White)
                    }
                }

                Card(
                    onClick = { /* TODO */ },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.DirectionsCar,
                            contentDescription = null,
                            tint = Color(0xFF2196F3),
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "View Cars", color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ── NEW: Parking Occupancy Progress Bar ──
            Text(
                text = "Lot Occupancy",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "32 / 50 spots used", color = Color.White, fontSize = 13.sp)
                        Text(text = "64%", color = Color.Red, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = 0.64f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .clip(RoundedCornerShape(50)),
                        color = Color.Red,
                        trackColor = Color(0xFF3A3A3A)
                    )
                }
            }

            // ── NEW: Stats Row ──
            Text(
                text = "Today's Stats",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatCard(icon = Icons.Default.DirectionsCar, label = "Check-ins", value = "47", tint = Color(0xFF4CAF50))
                Spacer(modifier = Modifier.width(8.dp))
                StatCard(icon = Icons.Default.Schedule, label = "Avg. Stay", value = "2h 15m", tint = Color(0xFFFF9800))
                Spacer(modifier = Modifier.width(8.dp))
                StatCard(icon = Icons.Default.Speed, label = "Turnover", value = "12/hr", tint = Color(0xFF2196F3))
            }

            // ── NEW: Recent Activity ──
            Text(
                text = "Recent Activity",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    ActivityRow(plate = "KCA 123A", slot = "A-04", time = "08:32 AM", entering = true)
                    Divider(color = Color(0xFF2E2E2E), modifier = Modifier.padding(vertical = 10.dp))
                    ActivityRow(plate = "KBZ 456B", slot = "B-11", time = "09:10 AM", entering = false)
                    Divider(color = Color(0xFF2E2E2E), modifier = Modifier.padding(vertical = 10.dp))
                    ActivityRow(plate = "KDD 789C", slot = "C-02", time = "09:45 AM", entering = true)
                }
            }

            // ── NEW: Reserved / VIP Spots ──
            Text(
                text = "Special Zones",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ZoneCard(label = "VIP Zone", spots = "3 left", icon = Icons.Default.Star, color = Color(0xFFFFD700), modifier = Modifier.weight(1f).padding(end = 6.dp))
                ZoneCard(label = "Handicap", spots = "2 left", icon = Icons.Default.LocalParking, color = Color(0xFF2196F3), modifier = Modifier.weight(1f).padding(start = 6.dp))
            }
        }
    }
}

// ── Helper Composable: Stat Card ──
@Composable
fun StatCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    tint: Color
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        modifier = Modifier.width(108.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = tint, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = label, color = Color.Gray, fontSize = 11.sp)
        }
    }
}

// ── Helper Composable: Activity Row ──
@Composable
fun ActivityRow(plate: String, slot: String, time: String, entering: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(if (entering) Color(0xFF1B3A1B) else Color(0xFF3A1B1B)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.DirectionsCar,
                    contentDescription = null,
                    tint = if (entering) Color(0xFF4CAF50) else Color.Red,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = plate, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Text(text = "Slot $slot", color = Color.Gray, fontSize = 12.sp)
            }
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = if (entering) "Entered" else "Exited",
                color = if (entering) Color(0xFF4CAF50) else Color.Red,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(text = time, color = Color.Gray, fontSize = 11.sp)
        }
    }
}

// ── Helper Composable: Zone Card ──
@Composable
fun ZoneCard(
    label: String,
    spots: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = label, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = spots, color = color, fontSize = 13.sp)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    Dashboard(rememberNavController())
}