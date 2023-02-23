package pro.stuermer.bookii.devices.domain

import org.koin.dsl.module
import pro.stuermer.bookii.devices.domain.GetConnectionUseCase

val devicesDomainModule = module {
    factory {
        GetConnectionUseCase(
            connectionRepository = get()
        )
    }
}
