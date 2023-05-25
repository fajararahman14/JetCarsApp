package com.fajar.jetcarsapp.screen.about

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fajar.jetcarsapp.R
import com.fajar.jetcarsapp.ui.theme.JetCarsAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.Black,
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back),
                            tint = Color.White
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.about_menu),
                        style = MaterialTheme.typography.h1,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            Text(
                text = stringResource(id = R.string.about),
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(8.dp)
            )
            Spacer(modifier = modifier
                .fillMaxWidth()
                .padding(16.dp))
            Image(
                painter = painterResource(id = R.drawable.my_profile_image),
                contentDescription = "profile_picture",
                modifier = modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.name_profile),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)
            )
            Text(
                text = stringResource(id = R.string.email_profile),
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic,
                modifier = modifier.padding(0.dp, 6.dp, 0.dp, 0.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    JetCarsAppTheme {
        val navController = rememberNavController()
        AboutScreen(navController = navController)
    }
}
