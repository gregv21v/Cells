


package main

import processing.core._
import scala.collection.mutable._
import scala.util.Random
import proteins.Protein


class World(pApp: PApplet) {
  private var _cells = MutableList[Cell]()
  private var _proteins = {
    for(i <- 0 until 20) yield {
      new Protein(pApp) {
        var rand = new Random
        position.x = rand.nextInt(pApp.width)
        position.y = rand.nextInt(pApp.height)
        color = pApp.color(0, 0, 255)
      }
    }
  }


  
  
  def interactions {
    //_cells.foreach { x => x.internalCollision }
    
    
    // protein colliding with cell
    
    _cells.foreach {
      e => {
        e.collectProtein(_proteins.toArray)
      }
    }
    
    _proteins = _proteins.filter { x => !x.marked }
    
    

  }
  
  
  def draw {
    
    _proteins.foreach {
      e => {
        e.draw 
      }
    }
    _cells.foreach {
      e => {
        e.draw
      }
    }
  }
  
  
  def move {
    _proteins.foreach {
      e => {
        e.move
      }
    }
    _cells.foreach {
      e => {
        e.move
      }
    }
  }
  
  
  
  
  def add(cell: Cell) {
    _cells :+= cell
  }
}