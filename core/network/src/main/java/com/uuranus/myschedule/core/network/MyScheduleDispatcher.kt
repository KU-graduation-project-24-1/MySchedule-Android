package com.uuranus.myschedule.core.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val myScheduleDispatchers: MyScheduleDispatchers)

enum class MyScheduleDispatchers {
    Default,
    IO,
}
