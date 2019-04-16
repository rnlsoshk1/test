package com.tests.test

interface MenuService {
    fun searchMenus(nameFilter: String) : List<Menu>
    fun getMenu(menu_id: String) : Menu?
    fun createMenu(menu: Menu)
    fun deleteMenu(menu_id: String)
    fun updateMenu(menu_id: String, menu: Menu)
}