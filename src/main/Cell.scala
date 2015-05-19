/*
 * Cell
 * 
 * 
 * A cell consists of a permeable membrane that deforms to let 
 * objects in, then reforms.
 * 
 * 
 */


package main

import processing.core._
import scala.collection.mutable._

class Cell(pApp: PApplet) {

  private var _center = new PVector()
  private var _membrane: Array[MembraneUnit] = _
  private var _radius = 100.0f
  private var _membraneUnitRadius = 20.0f
   

 
    
  
  def construct {
    
    var membraneUnitCount = ((Math.PI * _radius) / (_membraneUnitRadius)).toInt
    println("MembraneUnitCount: " + membraneUnitCount)
    var angle = 2 * Math.PI / membraneUnitCount
    
    _membrane = Array.ofDim[MembraneUnit](membraneUnitCount)
    for(i <- 0 until membraneUnitCount) {
      _membrane(i) = new MembraneUnit(pApp)
      _membrane(i).position = new PVector(
                                          (_center.x + _radius * Math.cos(i*angle)).toFloat,
                                          (_center.y + _radius * Math.sin(i*angle)).toFloat
                                         )
      _membrane(i).radius = _membraneUnitRadius
      _membrane(i).id = i
    }
  }
  
  def interact(protein: Protein) {
    // find the membrane units being collided with
    var collisionIndices = MutableList[Int]()
    for(i <- 0 until membrane.length) {
      if(protein.position.dist(membrane(i).position) < protein.radius + membrane(i).radius) {
        membrane(i).color = pApp.color(64, 224, 208)
        collisionIndices :+= i
      }
    }
    
    if(collisionIndices.length == 2) {
      // break cell at the point between these two indices
    }
    
    
    // if inside the cell, stop the proteins movement
    
    // otherwise, push the membrane units into the cell
    // radially move the the collided membrane units
    for(index <- collisionIndices) {
      // rotate around the membrane unit that has not been collided with
      
      // the pivot is the center to rotate around
      var pivot = {
        if(!collisionIndices.contains(index+1)) {
          membrane((index + 1) % membrane.length)
        } else {
          membrane((index - 1) % membrane.length)
        }
      }
      
      

      
      membrane(index).position.x = 
        pivot.position.x + (pivot.radius + membrane(index).radius) * Math.cos(Math.PI/10.0).toFloat
                                    
      membrane(index).position.y = 
        pivot.position.y + (pivot.radius + membrane(index).radius) * Math.sin(Math.PI/10.0).toFloat

    }
 
    
    
    // then reform when the protein is fully in the cell
    
    
    
    

  }
  
  def internalCollision {
    for(i <- 0 until membrane.length) {
      for(j <- i+1 until membrane.length) {
        if(membrane(i).collidesWith(membrane(j))) {
          membrane(i).collisionInteraction(membrane(j))
        }
      }
    } 
  }
  
  def draw {
    for(i <- 0 until _membrane.length) {
      _membrane(i).draw
    }
  }
  
  def move {
    for(i <- 0 until _membrane.length) {
      _membrane(i).move
    }
  }
  
  
  
  def center = _center
  def center_=(value: PVector) {
    _center.x = value.x
    _center.y = value.y
  }
  
  def membrane = _membrane
  def membrane_=(value: Array[MembraneUnit]) {
    _membrane = value
  }
  
  
  
  
  
  
}