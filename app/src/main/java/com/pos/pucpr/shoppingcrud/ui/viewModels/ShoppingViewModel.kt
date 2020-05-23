package com.pos.pucpr.shoppingcrud.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pos.pucpr.shoppingcrud.common.State
import com.pos.pucpr.shoppingcrud.domain.usecases.GetShopping
import com.pos.pucpr.shoppingcrud.domain.usecases.SaveShopping
import com.pos.pucpr.shoppingcrud.ui.mappers.toModel
import com.pos.pucpr.shoppingcrud.ui.mappers.toViewData
import com.pos.pucpr.shoppingcrud.ui.viewdata.ShoppingViewData
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val getShopping: GetShopping,
    private val saveShopping: SaveShopping
) : ViewModel() {

    private var _shopping: ShoppingViewData = ShoppingViewData(
        id = null,
        name = "",
        amount = 0,
        brand = "",
        shelfLife = ""
    )
    val shopping: ShoppingViewData get() = _shopping

    private val _fetchShoppingState: MutableLiveData<State<Unit>> by lazy {
        MutableLiveData<State<Unit>>()
    }
    val fetchShoppingState: LiveData<State<Unit>> get() = _fetchShoppingState

    private val _saveShoppingState: MutableLiveData<State<Unit>> by lazy {
        MutableLiveData<State<Unit>>()
    }
    val saveShoppingState: LiveData<State<Unit>> get() = _saveShoppingState

    fun saveShopping(shopping: ShoppingViewData) {
        viewModelScope.launch {
            _saveShoppingState.value = State.Loading
            saveShopping.invoke(shopping = shopping.toModel()).fold(
                failed = {
                    _saveShoppingState.value = State.Error
                },
                succeeded = { _ ->
                    _saveShoppingState.value = State.Success(Unit)
                }
            )
        }
    }

    fun fetchShopping(id: String) {
        viewModelScope.launch {
            _fetchShoppingState.value = State.Loading
            getShopping.invoke(id = id).fold(
                failed = {
                    _fetchShoppingState.value = State.Error
                },
                succeeded = { response ->
                    response?.let {
                        _shopping = it.toViewData()
                    }
                    _fetchShoppingState.value = State.Success(Unit)
                }
            )
        }
    }
}
