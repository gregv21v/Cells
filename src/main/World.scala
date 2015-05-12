package main

import processing.core._
import scala.collection.mutable._


class World(pApp: PApplet) {
  private var _cells: Array[Cell] = _
  
  def collisions {
    _cells.foreach { x => x.internalCollision }
  }
  
  
  def draw {
    _cells.foreach {
      e => {
        e.draw
      }
    }
  }
  
  
  def move {
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