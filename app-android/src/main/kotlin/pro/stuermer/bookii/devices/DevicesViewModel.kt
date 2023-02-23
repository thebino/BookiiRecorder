package pro.stuermer.bookii.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pro.stuermer.bookii.devices.domain.GetConnectionUseCase

internal class DevicesViewModel(
    private val getConnectionUseCase: GetConnectionUseCase,
): ViewModel() {
    val uiState = MutableStateFlow(DevicesViewState.Empty)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getConnectionUseCase().collect {
                withContext(Dispatchers.Main) {
                    uiState.value = uiState.value.copy(
                        isConnected = it.isConnected,
                        manufacture = it.deviceInfo?.manufacture,
                        model = it.deviceInfo?.model,
                        deviceFirmwareVersion = it.deviceInfo?.deviceVersion,
                        deviceSerial = it.deviceInfo?.deviceSerial,
                        capacity = it.storageInfo.getOrNull(0)?.capacity,
                        occupiedSpace = it.storageInfo.getOrNull(0)?.occupiedSpace,
                        freeSpace = it.storageInfo.getOrNull(0)?.freeSpace,
                    )
                }
            }
        }
    }
}
