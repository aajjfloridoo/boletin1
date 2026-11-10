package com.example.boletin_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boletin_1.ui.theme.Boletin_1Theme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Boletin_1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FormularioCompletoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// BLOQUE 1

val CustomPrimaryColor = Color(0xFF1E88E5)

@Composable
fun HeaderBanner(title: String) {
    Text(
        text = title,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = CustomPrimaryColor
    )
}

@Composable
fun UserAvatar(resourceId: Int) {
    Image(
        painter = painterResource(id = resourceId),
        contentDescription = "Avatar del desarrollador",
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .padding(8.dp)
    )
}

@Composable
fun ProfileHeader(modifier: Modifier= Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserAvatar(resourceId = R.drawable.ic_launcher_background)

        Column(
            modifier = Modifier.padding(start = 12.dp)
        ) {
            HeaderBanner(title = "Antonio Jesús")
            Text(
                text = "Desarrollador Android Junior",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

// BLOQUE 2: FORMULARIO DE ENTRADA DE DATOS

@Composable
fun UsernameInput(modifier: Modifier = Modifier) {
    var username by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de usuario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = if (username.isNotEmpty()) "Hola, $username" else "Introduce tu nombre arriba",
            fontSize = 16.sp
        )
    }
}

@Composable
fun RegisterForm(modifier: Modifier = Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Icono de correo"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Icono de contraseña"
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun RoleSelector(modifier: Modifier = Modifier) {
    val roles = listOf("Frontend", "Backend", "Mobile", "Fullstack")
    var selectedRole by rememberSaveable { mutableStateOf(roles.first()) }

    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        Text(text = "Selecciona tu Rol Principal:", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))

        roles.forEach { role ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (role == selectedRole),
                        onClick = { selectedRole = role },
                        role = Role.RadioButton
                    )
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (role == selectedRole),
                    onClick = { selectedRole = role }
                )
                Text(
                    text = role,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun TechInterests(modifier: Modifier = Modifier) {
    var kotlinChecked by rememberSaveable { mutableStateOf(false) }
    var composeChecked by rememberSaveable { mutableStateOf(false) }
    var coroutinesChecked by rememberSaveable { mutableStateOf(false) }

    val totalSelected = listOf(kotlinChecked, composeChecked, coroutinesChecked).count { it }

    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        Text(text = "Tecnologías de Interés:", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = kotlinChecked,
                onCheckedChange = { kotlinChecked = it }
            )
            Text(text = "Kotlin", modifier = Modifier.padding(start = 8.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = composeChecked,
                onCheckedChange = { composeChecked = it }
            )
            Text(text = "Jetpack Compose", modifier = Modifier.padding(start = 8.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = coroutinesChecked,
                onCheckedChange = { coroutinesChecked = it }
            )
            Text(text = "Corrutinas", modifier = Modifier.padding(start = 8.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Total tecnologías marcadas: $totalSelected",
            fontSize = 16.sp
        )
    }
}

// =======================================================================
// BLOQUE 3: CONTROLES INTERACTIVOS Y PERSISTENCIA
// =======================================================================

@Composable
fun NotificationSettings(modifier: Modifier = Modifier) {
    var notificationsEnabled by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Recibir novedades del sector",
            fontSize = 16.sp
        )
        Switch(
            checked = notificationsEnabled,
            onCheckedChange = { notificationsEnabled = it }
        )
    }
}

@Composable
fun ExperienceSlider(modifier: Modifier = Modifier) {
    var experience by rememberSaveable { mutableFloatStateOf(0f) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = String.format(Locale.US, "Experiencia: %.1f años", experience),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Slider(
            value = experience,
            onValueChange = { experience = it },
            valueRange = 0f..10f,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun FormularioCompletoScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Bloque 1: Fundamentos y Diseño Visual",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = CustomPrimaryColor,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        ProfileHeader()

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Bloque 2: Formulario de Entrada de Datos",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = CustomPrimaryColor,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        UsernameInput()
        RegisterForm()
        RoleSelector()
        TechInterests()

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Bloque 3: Controles Interactivos y Persistencia",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = CustomPrimaryColor,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        NotificationSettings()
        ExperienceSlider()
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    Boletin_1Theme {
        FormularioCompletoScreen()
    }
}
