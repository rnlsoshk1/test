package com.tests.test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class MenuController {
    @Autowired
    private lateinit var menuService: MenuService

    @GetMapping("/menu/{menu_id}")
    fun getMenu(@PathVariable menu_id: String) : ResponseEntity<Menu?> {
        val menu = menuService.getMenu(menu_id)
        val status = if (menu == null) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(menu, status)
    }

    @GetMapping("/menus")
    fun getMenus(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
        menuService.searchMenus(nameFilter)

    @PostMapping("/menu/")
    fun createMenu(@RequestBody menu: Menu): ResponseEntity<Unit> {
        menuService.createMenu(menu)
        return ResponseEntity(Unit, HttpStatus.CREATED)
    }

    @DeleteMapping("/menu/{menu_id}")
    fun deleteMenu(@PathVariable menu_id: String): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if(menuService.getMenu(menu_id) != null){
            menuService.deleteMenu(menu_id)
            status = HttpStatus.OK
        }
        return ResponseEntity(Unit, status)
    }

    @PutMapping("/menu/{menu_id}")
    fun updateMenu(@PathVariable menu_id: String, @RequestBody menu: Menu): ResponseEntity<Unit>{
        var status = HttpStatus.NOT_FOUND
        if (menuService.getMenu(menu_id) != null){
            menuService.updateMenu(menu_id, menu)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }
}