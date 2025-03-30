package com.heydar.simplemvp.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("sessionToken")
	val sessionToken: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null
)
