package com.github.lemartin.sdservice.example

import com.github.lemartin.sdservice.native.Systemd

fun main() {
    notifiy("STATUS=Starting the sd-service-example")

    for (i in 0..10) {
        notifiy("STATUS=Lift off in ${10 - i} seconds")
        Thread.sleep(1000)
    }

    notifiy("STATUS=Lift off")
    notifiy("READY=1")

    while (true) {
        Thread.sleep(250)
    }
}

fun notifiy(state: String) {
    val result = Systemd.sd_notify(0, state)
    when {
        result == 0 -> System.err.println("Failed to notify service manager: \$NOTIFY_SOCKET is not set")
        result < 0 -> System.err.println("Failed to notify service manager: sd_notify returned '${result}'")
    }
}