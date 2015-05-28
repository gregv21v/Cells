package main

import processing.core._
import creatures._
import vectors._

class Player(pApp: PApplet, world: World) {
  
  private var _forms = List(new Cell(pApp).asInstanceOf[Creature], new Snake(pApp).asInstanceOf[Creature])
  
  
  for(form <- _forms) {
    form.x = pApp.width/2
    form.y = pApp.height/2
    form.enabled = false
    form.grow(5)
    world :+= form
  }

  
  
  private var _currentForm = 0
  
  
  currentForm.enabled = true
  private var _movementSpeed = 10
  
  
  
  
  def cycleForms {
    currentForm.enabled = false
    
    _currentForm = (_currentForm + 1) % _forms.length
    
    currentForm.enabled = true
    
    
  } 
  
  def eat {
    _forms(_currentForm).eat
  }
  
  def reproduce {
    _forms(_currentForm).reproduce
  }
  
  def grow {
    _forms(_currentForm).grow(1) 
  }
  
  
  
  def movement(keys: Array[Boolean]) { // keys: wasd
    for(form <- _forms)
        form.velocity = new Vector
    
    // up
    if(keys(0)) {
      for(form <- _forms)
        form.velocity = new Vector(0, -_movementSpeed)
    }
    
    // left
    if(keys(1)) {
      for(form <- _forms)
        form.velocity = new Vector(-_movementSpeed, 0)
    }
    
    // down
    if(keys(2)) {
      for(form <- _forms)
        form.velocity = new Vector(0, _movementSpeed)
    }
    
    // right
    if(keys(3)) {
      for(form <- _forms)
        form.velocity = new Vector(_movementSpeed, 0)
    }
  }
  
  
  
  
  
  def currentForm = _forms(_currentForm)

  

}