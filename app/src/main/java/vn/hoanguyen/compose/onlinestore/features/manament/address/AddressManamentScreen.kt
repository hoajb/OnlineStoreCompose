@file:OptIn(ExperimentalMaterial3Api::class)

package vn.hoanguyen.compose.onlinestore.features.manament.address

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.LocationOn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import vn.hoanguyen.compose.onlinestore.components.AppTopAppBar
import vn.hoanguyen.compose.onlinestore.data_providers.Address
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme

@Composable
fun AddressManagementScreen(
    viewmodel: AddressManagementViewmodel = hiltViewModel(),
    onBack: () -> Unit,
    onAddNewAddress: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewmodel.loadDefaultAddress()
    }
    val addressList = viewmodel.addressList.collectAsState()
    val (defaultPos, updateDefaultPos) = remember { mutableIntStateOf(0) }
    AddressManagementBody(
        onBack = onBack,
        list = addressList.value,
        defaultPos = defaultPos,
        onUpdateDefaultPos = updateDefaultPos,
        onAddNewAddress = onAddNewAddress
    )
}

@Composable
fun AddressManagementBody(
    onBack: () -> Unit = {},
    onAddNewAddress: () -> Unit,
    list: List<Address>,
    defaultPos: Int = 0,
    onUpdateDefaultPos: (Int) -> Unit
) {
    Scaffold(Modifier.background(Color.White), topBar = {
        AppTopAppBar(title = "Address", onBack = onBack)
    }) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = "Saved Address", style = AppTypography.titleMedium
            )
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                itemsIndexed(list) { index, address ->
                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .border(
                                width = 0.5.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(8.dp), verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = "LocationOn",
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = address.name, style = AppTypography.titleSmall)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = address.address,
                                style = AppTypography.bodyMedium.copy(color = Color.Gray)
                            )
                        }
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
                                onAddNewAddress.invoke()
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Add New Address")
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
private fun AddressManagementScreenPrev() {
    OnlineStoreComposeTheme {
        AddressManagementBody(list = (0..5).map { index ->
            Address(
                name = "Home $index",
                address = "12 Le Loi Street, Hue City, Vietnam, Postal Code: 530000"
            )
        }, onBack = {}, defaultPos = 2, onUpdateDefaultPos = {}, onAddNewAddress = {})
    }
}