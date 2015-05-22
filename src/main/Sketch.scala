package main

import processing.core._

object Sketch extends PApplet {
  
  private var world: World = _
  private var keys = Array.fill[Boolean](4)(false) // wasd
  private var player: Player = _
  
  
  override def setup() {
    size(2000, 2000)
    
    world = new World(this)
    player = new Player(this, world)

    
  }
  
  
  override def draw {
    fill(255, 255, 255)
    rect(0, 0, width, height)
    
    
    
    fill(0, 255, 0)
    player.movement(keys)
    world.interactions
    world.move
    world.draw
    
    
    
  }
  
  
  override def keyPressed {
    key match {
      case 'w' => keys(0) = true
      case 'a' => keys(1) = true
      case 's' => keys(2) = true
      case 'd' => keys(3) = true
      case 'q' => {
        player.divide
      }
      case  _  =>
    }
    
  }
  
  override def keyReleased {
    key match {
      case 'w' => keys(0) = false
      case 'a' => keys(1) = false
      case 's' => keys(2) = false
      case 'd' => keys(3) = false
      case  _  =>
    }
  }
}