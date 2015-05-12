package main

import processing.core._

object Sketch extends PApplet {
  
  private var cell: Cell = _
  private var world: World = _
  
  
  override def setup() {
    size(1000, 1000)
    
    world = new World(this)
    
    
    
    cell = new Cell(this)
    cell.center = new PVector(width/2, height/2)
    cell.construct
    cell.membrane(0).velocity = new PVector(-1, 2)
    cell.membrane(2).velocity = new PVector(2, -1)
    
    world.cells = Array(cell)
    
    
    
    
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