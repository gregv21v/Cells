package main

import processing.core._

object Sketch extends PApplet {
  
  private var world: World = _
  
  
  override def setup() {
    size(1000, 1000)
    
    world = new World(this)

    
    
    
    
  }
  
  
  override def draw {
    fill(255, 255, 255)
    rect(0, 0, width, height)
    
    
    
    fill(0, 255, 0)
    world.collisions
    world.move
    world.draw
    
    
  }
}