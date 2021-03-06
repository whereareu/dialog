package com.dialogapp.dialog.vo

import com.dialogapp.dialog.model.EndpointData

data class Listing<T>(
        // the LiveData of endpoint data for the UI to observe
        val endpointData: EndpointData?,
        // the LiveData of post data for the UI to observe
        val postData: List<T>?)