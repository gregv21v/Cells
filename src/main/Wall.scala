package main

import processing.core._
import scala.collection.mutable._

class Wall(pApp: PApplet) {

  private var _membrane: MutableList[StructuralUnit] = MutableList()
  private var _start = new PVector  
  private var _membraneUnitRadius = 20.0f
  
  
  def grow(amount: Int) {
    for(i <- 0 until amount) {
      _membrane
      
    }  
  }
  
  
  def updatePositions {
    for(i <- 0 until _membrane.length) {
    }
  }
  
}