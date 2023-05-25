package com.fajar.jetcarsapp.screen.home


import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fajar.jetcarsapp.R
import com.fajar.jetcarsapp.ViewModelFactory
import com.fajar.jetcarsapp.component.CarsListItem
import com.fajar.jetcarsapp.component.ScrollToTopButton
import com.fajar.jetcarsapp.component.SearchBar
import com.fajar.jetcarsapp.repository.CarsRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(CarsRepository())
    ),
    navigateToDetail: (String) -> Unit
) {
    val groupedCars by viewModel.groupedCars.collectAsState()
    val query by viewModel.query
    Box(modifier = modifier) {

        val listState = rememberLazyListState()
        val scope = rememberCoroutineScope()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn(state = listState, contentPadding = PaddingValues(bottom = 80.dp)) {
            item {
                Box(modifier = modifier){
                    Image(
                        painter = painterResource(id = R.drawable.banner),
                        contentDescription = "banner",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(120.dp)
                    )
                    SearchBar(
                        query = query,
                        onQueryChange = viewModel::search
                    )
                }
            }
            groupedCars.forEach { (_, cars) ->
                items(cars, key = { it.id }) { car ->
                    val idCar = car.id
                    CarsListItem(
                        name = car.name,
                        photoUrl = car.photoUrl,
                        modifier = modifier
                            .clickable {
                                navigateToDetail(idCar)
                            }
                            .fillMaxWidth()
                            .animateItemPlacement(tween(durationMillis = 1200)),

                        )
                }
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            ScrollToTopButton(
                onClick = {
                    scope.launch {
                        listState.scrollToItem(index = 0)
                    }
                }
            )
        }
    }
}