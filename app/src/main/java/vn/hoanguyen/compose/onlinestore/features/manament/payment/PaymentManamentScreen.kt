@file:OptIn(ExperimentalMaterial3Api::class)

package vn.hoanguyen.compose.onlinestore.features.manament.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import vn.hoanguyen.compose.onlinestore.components.AppTopAppBar
import vn.hoanguyen.compose.onlinestore.data_providers.CardInfo
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme
import vn.hoanguyen.compose.onlinestore.utils.toCardIcon

@Composable
fun PaymentManagementScreen(
    viewmodel: PaymentManagementViewmodel = hiltViewModel(),
    onBack: () -> Unit,
    onAddNewCard: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewmodel.loadDefaultPayment()
    }
    val cardList = viewmodel.cardList.collectAsState()
    val (defaultPos, updateDefaultPos) = remember { mutableIntStateOf(0) }
    PaymentManagementBody(
        onBack = onBack,
        list = cardList.value,
        defaultPos = defaultPos,
        onUpdateDefaultPos = updateDefaultPos,
        onAddNewCard = onAddNewCard
    )
}

@Composable
fun PaymentManagementBody(
    onBack: () -> Unit = {},
    onAddNewCard: () -> Unit,
    list: List<CardInfo>,
    defaultPos: Int = 0,
    onUpdateDefaultPos: (Int) -> Unit
) {
    Scaffold(Modifier.background(Color.White), topBar = {
        AppTopAppBar(title = "Payment Method", onBack = onBack)
    }) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = "Saved Cards", style = AppTypography.titleMedium
            )
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                itemsIndexed(list) { index, cardInfo ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .border(
                                width = 0.5.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            modifier = Modifier.size(40.dp),
                            painter = painterResource(id = cardInfo.name.toCardIcon()),
                            contentDescription = "card type"
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            "**** **** **** ",
                            style = AppTypography.bodyMedium.copy(fontWeight = FontWeight.W500)
                        )

                        Text(
                            modifier = Modifier.weight(1f),
                            text = cardInfo.number.substring(
                                cardInfo.number.length - 4,
                                cardInfo.number.length
                            ),
                            style = AppTypography.bodyMedium.copy(fontWeight = FontWeight.W500)
                        )
                        if (defaultPos == index)
                            Text(
                                modifier = Modifier
                                    .background(
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(6.dp)
                                    )
                                    .padding(horizontal = 4.dp, vertical = 2.dp),
                                text = "default",
                                style = AppTypography.bodySmall
                            )

                        Spacer(modifier = Modifier.width(8.dp))
                        RadioButton(
                            selected = defaultPos == index,
                            onClick = { onUpdateDefaultPos(index) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color.Black
                            )
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(
                                color = Color.Transparent,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .border(
                                width = 0.5.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable {
                                onAddNewCard.invoke()
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Add New Card")
                    }
                }

            }

            Button(modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                ),
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    //update selection then back
                    onBack.invoke()
                }) {
                Text(text = "Apply")
            }
        }
    }

}

@Preview
@Composable
private fun PaymentManagementScreenPrev() {
    OnlineStoreComposeTheme {
        PaymentManagementBody(list = (0..5).map { index ->
            CardInfo(
                name = "American Express",
                number = "1234567890"
            )
        }, onBack = {}, defaultPos = 2, onUpdateDefaultPos = {}, onAddNewCard = {})
    }
}