package com.tests.test

import jdk.nashorn.internal.objects.NativeArray.map
import org.springframework.stereotype.Component
import java.security.KeyStore
import java.util.concurrent.ConcurrentHashMap
import java.util.stream.Collectors.toList

@Component
class MenuServiceImpl : MenuService {
    companion object {
        val initialMenus = arrayOf(Menu("123", "전체메뉴","","","","","","",
                "","","","","",""))
    }
    val menus = ConcurrentHashMap<String, Menu>(initialMenus.associateBy(Menu::menu_id))

    override fun getMenu(menu_id: String) = menus[menu_id]

    override fun createMenu(menu: Menu) {
        menus[menu.menu_id] = menu
    }

    override fun deleteMenu(menu_id: String) {
        menus.remove(menu_id)
    }

    override fun updateMenu(menu_id: String, menu: Menu) {
        deleteMenu(menu_id)
        createMenu(menu)
    }

    override fun searchMenus(nameFilter: String): List<Menu> =
            menus.filter {
                it.value.menu_nm.contains(nameFilter, true)
            }.map(Map.Entry<String, Menu>::value).toList()

}