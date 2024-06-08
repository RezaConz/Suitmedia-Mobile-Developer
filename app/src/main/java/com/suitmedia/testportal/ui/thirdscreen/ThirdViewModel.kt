package com.suitmedia.testportal.ui.thirdscreen

import androidx.lifecycle.ViewModel
import com.suitmedia.testportal.repository.UserRepository

class ThirdViewModel (private val userRepo : UserRepository) : ViewModel() {

    fun getUsers(page: Int, perPage: Int) = userRepo.getUsers(page, perPage)
}