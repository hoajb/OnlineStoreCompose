package vn.hoanguyen.compose.onlinestore.features.service.faq

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import vn.hoanguyen.compose.onlinestore.components.AppSearchBar
import vn.hoanguyen.compose.onlinestore.components.AppTopAppBar
import vn.hoanguyen.compose.onlinestore.components.ListEmptyItem
import vn.hoanguyen.compose.onlinestore.data_providers.FAQ
import vn.hoanguyen.compose.onlinestore.features.main.home.components.FilterChoice
import vn.hoanguyen.compose.onlinestore.features.main.home.components.FilterItem
import vn.hoanguyen.compose.onlinestore.features.main.home.components.FilterSelectionBar
import vn.hoanguyen.compose.onlinestore.features.main.home.components.FilterSelectionBarViewModel
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme

@Composable
fun FAQScreen(
    onBack: () -> Unit, viewmodel: FAQViewmodel = hiltViewModel()
) {
    val faqList = viewmodel.faqList.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewmodel.loadFAQList()
    }

    var rememberSearchQuery by remember { mutableStateOf("") }
    var rememberPosType by remember { mutableIntStateOf(0) }

    FAQBody(onBack = onBack,
        faqList = faqList.value,
        listFilter = viewmodel.faqFilterTypeList,
        onFilterChange = { posType ->
            rememberPosType = posType
            viewmodel.filterFAQList(posType = posType, query = rememberSearchQuery)
        },
        onSearchChange = { query ->
            rememberSearchQuery = query
            viewmodel.filterFAQList(posType = rememberPosType, query = query)
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FAQBody(
    onBack: () -> Unit,
    faqList: List<FAQ>,
    listFilter: List<String>,
    onSearchChange: (String) -> Unit,
    onFilterChange: (Int) -> Unit
) {
    Scaffold(Modifier.background(Color.White), topBar = {
        AppTopAppBar(title = "FAQs", onBack = onBack)
    }) { padding ->
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(padding)
                .fillMaxWidth()
                .padding(16.dp)

        ) {
            val filterSelectionBarViewModel: FilterSelectionBarViewModel = viewModel()
            LaunchedEffect(key1 = Unit) {
                filterSelectionBarViewModel.select(0)
            }
            FilterSelectionBar(viewmodel = filterSelectionBarViewModel,
                listItem = listFilter.mapIndexed { index, item ->
                    FilterItem(id = index, text = item)
                },
                filterChoice = FilterChoice.Single,
                onSelectedChange = { indexList ->
                    onFilterChange.invoke(indexList[0])//Single choice
                })

            AppSearchBar(
                hintSearch = "Search for questions...",
                onSearch = {},
                onValueChange = onSearchChange
            )

            if (faqList.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    FAQListEmptyItem()
                }
            } else
                LazyColumn(
                    Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    items(faqList) { item ->
                        key(item.title) {
                            FAQItem(item = item)
                        }
                    }
                }
        }
    }
}

@Composable
fun FAQListEmptyItem() {
    ListEmptyItem(
        icon = Icons.Outlined.QuestionMark,
        title = "No Found Items!",
        content = "There's no FAQ matched.\nPlease update your filtering and searching.",
    )
}

@Composable
private fun FAQItem(
    modifier: Modifier = Modifier, item: FAQ
) {
    var contentVisibility by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(
                width = 0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(12.dp)
            )
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable(indication = null, // no ripple
                interactionSource = remember { MutableInteractionSource() }) {
                contentVisibility = !contentVisibility
            },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f), text = item.title, style = AppTypography.titleSmall
            )

            Icon(
                imageVector = if (contentVisibility) Icons.Default.KeyboardArrowDown
                else Icons.Default.KeyboardArrowUp, contentDescription = "expand"
            )
        }

        AnimatedVisibility(visible = contentVisibility) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = item.content,
                style = AppTypography.bodySmall
            )
        }

    }
}

@Preview
@Composable
private fun FAQScreenPrev() {
    OnlineStoreComposeTheme {
        FAQBody(onBack = {},
            listFilter = listOf("General", "Account", "Service", "Payment"),
            onSearchChange = {},
            onFilterChange = {},
            faqList = (0..9).map {
                FAQ(
                    type = it % 3,
                    title = "How do I blah blah How do I blah blah How do I blah blah How do I blah blah",
                    content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                )
            })
    }
}

@Preview
@Composable
private fun FAQScreenEmptyPrev() {
    OnlineStoreComposeTheme {
        FAQBody(
            onBack = {},
            listFilter = listOf("General", "Account", "Service", "Payment"),
            onSearchChange = {},
            onFilterChange = {},
            faqList = emptyList()
        )
    }
}