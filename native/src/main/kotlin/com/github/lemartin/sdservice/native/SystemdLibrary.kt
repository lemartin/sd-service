package com.github.lemartin.sdservice.native

import com.sun.jna.Library
import com.sun.jna.Native


@Suppress("FunctionName")
interface SystemdLibrary : Library {
    fun sd_notify(unset_environment: Int, state: String): Int
}

val Systemd: SystemdLibrary = Native.load(System.getProperty("systemd.library", "systemd"), SystemdLibrary::class.java)