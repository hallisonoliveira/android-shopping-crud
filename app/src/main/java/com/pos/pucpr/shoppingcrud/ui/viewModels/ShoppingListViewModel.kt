package com.pos.pucpr.shoppingcrud.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pos.pucpr.shoppingcrud.common.State
import com.pos.pucpr.shoppingcrud.domain.usecases.DeleteShopping
import com.pos.pucpr.shoppingcrud.domain.usecases.GetAllShopping
import com.pos.pucpr.shoppingcrud.ui.mappers.toViewData
import com.pos.pucpr.shoppingcrud.ui.viewdata.ShoppingViewData
import kotlinx.coroutines.launch

class ShoppingListViewModel(
    private val getAllShoppingUseCase: GetAllShopping,
    private val deleteShopping: DeleteShopping
) : ViewModel() {

    private val _shoppingList: MutableList<ShoppingViewData> = mutableListOf()
    val shoppingList: List<ShoppingViewData> get() = _shoppingList

    private val _fetchShoppingListState: MutableLiveData<State<Unit>> by lazy {
        MutableLiveData<State<Unit>>()
    }
    val fetchShoppingListState: LiveData<State<Unit>> get() = _fetchShoppingListState

    private val _deleteShoppingState: MutableLiveData<State<Unit>> by lazy {
        MutableLiveData<State<Unit>>()
    }
    val deleteShoppingState: LiveData<State<Unit>> get() = _deleteShoppingState

    fun fetchShopping() {
        viewModelScope.launch {
            _fetchShoppingListState.value = State.Loading
            getAllShoppingUseCase.invoke().fold(
                failed = {
                    _fetchShoppingListState.value = State.Error
                },
                succeeded = { response ->
                    _shoppingList.run {
                        clear()
                        addAll(response.map { it.toViewData() })
                    }
                    _fetchShoppingListState.value = State.Success(Unit)
                }
            )

        }
    }

    fun deleteShopping(id: String) {
        viewModelScope.launch {
            _deleteShoppingState.value = State.Loading
            deleteShopping.invoke(id = id).fold(
                failed = {
                    _deleteShoppingState.value = State.Error
                },
                succeeded = {
                    _deleteShoppingState.value = State.Success(Unit)
                }
            )

        }
    }
}