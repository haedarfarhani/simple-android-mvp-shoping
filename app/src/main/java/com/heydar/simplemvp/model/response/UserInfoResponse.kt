package com.heydar.simplemvp.model.response

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(

	@field:SerializedName("profilePicture")
	val profilePicture: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
