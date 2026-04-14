package com.example.carparkingsystem.ui.theme.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.carparkingsystem.data.AuthViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import com.example.carparkingsystem.navigation.ROUTE_CAR
import com.example.carparkingsystem.navigation.ROUTE_LOGIN
import kotlinx.coroutines.flow.StateFlow


// ── Colour tokens ──────────────────────────────────────────
val NavyDark    = Color(0xFF0A0F1E)
val NavyMid     = Color(0xFF0D1630)
val NavyCard    = Color(0xFF131D3B)
val NavyBorder  = Color(0xFF1E2D5A)
val Amber       = Color(0xFFF5C842)
val GreenAccent = Color(0xFF3DDB7F)
val RedAccent   = Color(0xFFFF6B6B)
val BlueAccent  = Color(0xFF7B9FFF)
val TextMuted   = Color(0xFF7A8AB5)
val TextDim     = Color(0xFF5A6A90)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(navController: NavController, authViewModel: AuthViewModel = viewModel()) {

    val selectedItem = remember { mutableStateOf(0) }  // close remember here

    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    LaunchedEffect(isLoggedIn) {
        if (!isLoggedIn) {
            navController.navigate(ROUTE_LOGIN) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    // rest of your Scaffold below...
    val context = LocalContext.current


    Scaffold(
        containerColor = NavyDark,
        // ── TOP BAR ──────────────────────────────────────
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "🅿 ParkSmart",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Amber
                    )
                },
                actions = {
                    TextButton(
                        onClick = {authViewModel.logout()
                        },
                        border = androidx.compose.foundation.BorderStroke(1.dp, Amber),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.padding(end = 12.dp),
                        colors = ButtonDefaults.textButtonColors(contentColor = Amber)
                    ) {
                        Text("Log Out", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NavyMid,
                    titleContentColor = Amber
                )
            )
        },

        // ── BOTTOM NAV ────────────────────────────────────
        bottomBar = {
            NavigationBar(containerColor = NavyMid) {
                NavigationBarItem(
                    selected = selectedItem.value == 0,
                    onClick = { selectedItem.value = 0 },
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Home") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Amber,
                        selectedTextColor = Amber,
                        indicatorColor = NavyCard
                    )
                )
                NavigationBarItem(
                    selected = selectedItem.value == 1,
                    onClick = { selectedItem.value = 1 },
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Profile") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Amber,
                        selectedTextColor = Amber,
                        indicatorColor = NavyCard
                    )
                )
                NavigationBarItem(
                    selected = selectedItem.value == 2,
                    onClick = { selectedItem.value = 2 },
                    icon = { Icon(Icons.Default.Settings, null) },
                    label = { Text("Settings") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Amber,
                        selectedTextColor = Amber,
                        indicatorColor = NavyCard
                    )
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            // ── Greeting ────────────────────────────────
            Text("Good morning, Admin", color = TextMuted, fontSize = 13.sp,
                fontWeight = FontWeight.Medium)
            Text("Parking Dashboard", color = Color.White, fontSize = 22.sp,
                fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 12.dp))

            // ── Status Badges ────────────────────────────
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 14.dp)) {
                StatusBadge("● Live", GreenAccent, Color(0xFF1A3A2A))
                StatusBadge("Today: KSh 4,200", Amber, Color(0xFF2A1F00))
                StatusBadge("50 Slots", BlueAccent, Color(0xFF1A1A3A))
            }

            // ── Slot Stats Card (your original logic, restyled) ──
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = NavyCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, NavyBorder)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    SlotStat("Available", "18", GreenAccent)
                    Divider(modifier = Modifier.height(40.dp).width(1.dp), color = NavyBorder)
                    SlotStat("Occupied", "32", RedAccent)
                    Divider(modifier = Modifier.height(40.dp).width(1.dp), color = NavyBorder)
                    SlotStat("Reserved", "5", Amber)
                }
            }

            // ── Revenue Pill ─────────────────────────────
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = NavyCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, NavyBorder)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Today's Revenue", color = TextMuted, fontSize = 12.sp)
                        Text("↑ 12% vs yesterday", color = TextDim, fontSize = 11.sp)
                    }
                    Text("KSh 4,200", color = GreenAccent, fontSize = 18.sp,
                        fontWeight = FontWeight.Bold)
                }
            }

            // ── Quick Actions Grid (your original cards + 2 new) ──
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Add Car (your original)
                QuickActionCard(
                    icon = { Icon(Icons.Default.Add, null, tint = Amber, modifier = Modifier.size(26.dp)) },
                    label = "Add Car",
                    highlighted = true,
                    modifier = Modifier.weight(1f),
                    onClick = {navController.navigate(ROUTE_CAR)}
                )
                // View Cars (your original)
                QuickActionCard(
                    icon = { Icon(Icons.Default.DirectionsCar, null, tint = BlueAccent, modifier = Modifier.size(26.dp)) },
                    label = "View Cars",
                    modifier = Modifier.weight(1f),
                    onClick = { /* TODO */ }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                QuickActionCard(
                    icon = { Icon(Icons.Default.History, null, tint = GreenAccent, modifier = Modifier.size(26.dp)) },
                    label = "History",
                    modifier = Modifier.weight(1f),
                    onClick = { /* TODO */ }
                )
                QuickActionCard(
                    icon = { Icon(Icons.Default.BarChart, null, tint = Color(0xFFFF9F6B), modifier = Modifier.size(26.dp)) },
                    label = "Reports",
                    modifier = Modifier.weight(1f),
                    onClick = { /* TODO */ }
                )
            }

            // ── Recent Activity ──────────────────────────
            Text("Recent Activity", color = Color.White, fontSize = 16.sp,
                fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))

            ActivityItem("KBZ 123A", "Slot B4 · Checked in", "2 min ago", GreenAccent)
            HorizontalDivider(color = NavyBorder, thickness = 0.5.dp)
            ActivityItem("KDH 456X", "Slot A2 · Checked out · KSh 300", "15 min ago", RedAccent)
            HorizontalDivider(color = NavyBorder, thickness = 0.5.dp)
            ActivityItem("KCF 789M", "Slot C7 · Reserved · 2 hrs", "34 min ago", Amber)
        }
    }
}

// ── Reusable composables ────────────────────────────────────

@Composable
fun StatusBadge(label: String, textColor: Color, bgColor: Color) {
    Box(
        modifier = Modifier
            .background(bgColor, RoundedCornerShape(20.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(label, color = textColor, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun SlotStat(label: String, value: String, valueColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, color = TextMuted, fontSize = 11.sp,
            fontWeight = FontWeight.Medium, letterSpacing = 0.5.sp)
        Spacer(Modifier.height(4.dp))
        Text(value, color = valueColor, fontSize = 22.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun QuickActionCard(
    icon: @Composable () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    highlighted: Boolean = false,
    onClick: () -> Unit
) {
    val borderColor = if (highlighted) Amber else NavyBorder
    val bgColor = if (highlighted) Color(0xFF1A1500) else NavyCard

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        border = androidx.compose.foundation.BorderStroke(1.dp, borderColor),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            icon()
            Spacer(Modifier.height(8.dp))
            Text(label, color = if (highlighted) Amber else Color(0xFFC5D0EE),
                fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun ActivityItem(plate: String, detail: String, time: String, dotColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(dotColor, RoundedCornerShape(50))
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(plate, color = Color(0xFFE8EEFF), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Text(detail, color = TextDim, fontSize = 11.sp)
        }
        Text(time, color = TextDim, fontSize = 11.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    Dashboard(rememberNavController())
}