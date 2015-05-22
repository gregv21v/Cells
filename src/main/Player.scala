package main

import processing.core._

class Player(pApp: PApplet, world: World) {
  
  private var _cell = new Cell(pApp) {
    center.x = pApp.width/2
    center.y = pApp.height/2
  }
  private var _movementSpeed = 10
  
  _cell.grow(10)
  world.add(_cell)
  
  
  
  
  
  def divide {
    var division = _cell.divide
    
    if(division != null) {
      world.add(division(0))
      world.add(division(1))
      _cell = division(0)
    }
  }
  
  
  
  def movement(keys: Array[Boolean]) { // keys: wasd
    if(keys(0)) {
      _cell.center = new PVector(_cell.center.x, _cell.center.y - _movementSpeed)
    }
    
    if(keys(1)) {
      _cell.center = new PVector(_cell.center.x - _movementSpeed, _cell.center.y)
    }
    
    if(keys(2)) {
      _cell.center = new PVector(_cell.center.x, _cell.center.y + _movementSpeed)
    }
    
    if(keys(3)) {
      _cell.center = new PVector(_cell.center.x + _movementSpeed, _cell.center.y)
    }

  }
  
  
  def cell = _cell

  

}