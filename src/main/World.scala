


package main

import processing.core._
import scala.collection.mutable._


class World(pApp: PApplet) {
  private var _cells = Array(new Cell(pApp))
  private var _protein = new Protein(pApp)
  
  _cells(0).center = new PVector(pApp.width/2, pApp.height/2)
  _cells(0).construct
  _protein.velocity = new PVector(1, 1)
  _protein.color = pApp.color(0, 0, 255)
  _protein.position = new PVector(100, 100)

  
  
  def collisions {
    //_cells.foreach { x => x.internalCollision }
    
    
    // protein colliding with cell
    _cells(0).interact(_protein)
  }
  
  
  def draw {
    
    _protein.draw
    _cells.foreach {
      e => {
        e.draw
      }
    }
  }
  
  
  def move {
    _protein.move
    _cells.foreach {
      e => {
        e.move
      }
    }
  }
  
  
  
  
  def cells = _cells
  def cells_=(value: Array[Cell]) {
    _cells = value
  }
}