package pro.stuermer.bookii

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pro.stuermer.bookii.devices.domain.CloseConnectionUseCase
import pro.stuermer.bookii.devices.domain.InitConnectionUseCase

val appModule = module {
    viewModel {
        MainViewModel(
            initConnectionUseCase = get(),
            closeConnectionUseCase = get(),
        )
    }

    // domain

    factory {
        InitConnectionUseCase(
            connectionRepository = get()
        )
    }
    factory {
        CloseConnectionUseCase(
            connectionRepository = get()
        )
    }

    // data
    single<pro.stuermer.bookii.usb.repository.ConnectionRepository> {
        pro.stuermer.bookii.usb.repository.ConnectionRepositoryImpl(
            connectionService = get()
        )
    }
    // service
    factory {
        pro.stuermer.bookii.usb.ConnectionService(
            context = get()
        )
    }
}
