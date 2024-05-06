@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)

package vn.hoanguyen.compose.onlinestore.features.manament.address

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import vn.hoanguyen.compose.onlinestore.components.AppTopAppBar
import vn.hoanguyen.compose.onlinestore.components.dialog.AppDialog
import vn.hoanguyen.compose.onlinestore.features.manament.address.components.AddressNewPopupContent
import vn.hoanguyen.compose.onlinestore.features.permission.PermissionRationaleDialog
import vn.hoanguyen.compose.onlinestore.features.permission.RationaleState
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme

private val HoChiMinhCityVietNam = LatLng(10.7756, 106.7019)
private val HaNoiCityVietNam = LatLng(21.028511, 105.804817)

private const val initialZoom = 6f
private const val finalZoom = 10f

@SuppressLint("MissingPermission")
@Composable
fun AddressAddNewScreen(
    onBack: () -> Unit,
) {
    // When precision is important request both permissions but make sure to handle the case where
    // the user only grants ACCESS_COARSE_LOCATION
    val fineLocationPermissionState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
        ),
    )

    // Keeps track of the rationale dialog state, needed when the user requires further rationale
    var rationaleState by remember {
        mutableStateOf<RationaleState?>(null)
    }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    val currentLatLngText = remember { mutableStateOf("Getting location") }
    val currentLatLng = remember { mutableStateOf(HaNoiCityVietNam) }

    LaunchedEffect(key1 = fineLocationPermissionState.allPermissionsGranted) {
        if (fineLocationPermissionState.allPermissionsGranted.not()) {
            if (fineLocationPermissionState.shouldShowRationale) {
                rationaleState = RationaleState(
                    "Request Precise Location to use Maps",
                    "In order to use this feature please grant access by accepting " + "the location permission dialog." + "\n\nWould you like to continue?",
                ) { proceed ->
                    if (proceed) {
                        fineLocationPermissionState.launchMultiplePermissionRequest()
                    }
                    rationaleState = null
                }
            } else {
                fineLocationPermissionState.launchMultiplePermissionRequest()
            }
        } else {
            //All permission granted

            //To get more accurate or fresher device location use this method
            scope.launch(Dispatchers.IO) {
                val priority = Priority.PRIORITY_BALANCED_POWER_ACCURACY
                val result = locationClient.getCurrentLocation(
                    priority,
                    CancellationTokenSource().token,
                ).await()

                if (result == null) {
                    currentLatLngText.value = "Unknown"
                } else {
                    currentLatLngText.value =
                        "Current Lat: ${result.latitude} Long: ${result.longitude}"
                }
                result?.let { fetchedLocation ->
                    currentLatLng.value =
                        LatLng(fetchedLocation.latitude, fetchedLocation.longitude)
                }
            }
        }
    }

    var showBottomSheet by remember { mutableStateOf(false) }
    var showSuccess by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Box(
        Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .animateContentSize(),
    ) {
        // Show rationale dialog when needed
        rationaleState?.run { PermissionRationaleDialog(rationaleState = this) }

        AddressAddNewBody(onBack = onBack, currentLatLng = currentLatLng.value, onShowAddPopup = {
            showBottomSheet = true
        })
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            }, containerColor = Color.White, sheetState = sheetState
        ) {
            // Sheet content
            AddressNewPopupContent(onHidePopup = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet = false
                    }
                }
            }, onAddAddress = { nickName, address ->
                showSuccess = true
            })
        }
    }

    if (showSuccess) {
        AppDialog(
            onDismissRequest = onBack,
            onPositivePressed = {},
            title = "New Address!",
            content = "Added successfully!",
            positive = "Ok",
            icon = Icons.Default.CheckCircle,
            iconTint = Color.Green
        )
    }
}

@Composable
fun AddressAddNewBody(
    defaultLatLng: LatLng = HaNoiCityVietNam,
    currentLatLng: LatLng = HaNoiCityVietNam,
    onShowAddPopup: () -> Unit,
    onBack: () -> Unit = {},
) {
    Scaffold(Modifier.background(Color.White), topBar = {
        AppTopAppBar(title = "New Address", onBack = onBack)
    }) { padding ->
        Box(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
        ) {
            val cameraPositionState = rememberCameraPositionState {
                val isDefaultLatlng = currentLatLng == defaultLatLng
                position = if (isDefaultLatlng) {
                    CameraPosition.fromLatLngZoom(defaultLatLng, initialZoom)
                } else {
                    CameraPosition.fromLatLngZoom(currentLatLng, finalZoom)
                }
            }

            LaunchedEffect(key1 = currentLatLng) {
                cameraPositionState.animate(
                    update = CameraUpdateFactory.newCameraPosition(
                        CameraPosition(currentLatLng, finalZoom, 0f, 0f)
                    ), durationMs = 1000
                )
            }

            GoogleMap(
                modifier = Modifier.fillMaxSize(), cameraPositionState = cameraPositionState
            ) {
                if (currentLatLng != defaultLatLng) Marker(
                    state = rememberMarkerState(position = currentLatLng),
                    title = "Position",
                    snippet = "Marker in your position",
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                )
            }

            FloatingActionButton(
                onClick = onShowAddPopup,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 30.dp),
                containerColor = Color.Black

            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add_address",
                    tint = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun AddressManagementScreenPrev() {
    OnlineStoreComposeTheme {
        AddressAddNewBody(onBack = {}, onShowAddPopup = {})
    }
}