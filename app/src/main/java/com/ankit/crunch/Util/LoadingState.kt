package com.ankit.crunch.Util

data class LoadingState(val status:Status,val msg:String?=null) {
companion object{
    val LOADED=LoadingState(Status.SUCCESS)
    val LODING=LoadingState(Status.RUNNING)
}

enum class Status{
    RUNNING,
    SUCCESS
}
}