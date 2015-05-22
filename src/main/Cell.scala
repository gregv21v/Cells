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
import proteins.Protein

class Cell(pApp: PApplet) {

  private var _center = new PVector
  private var _membrane: MutableList[MembraneUnit] = MutableList[MembraneUnit]()
  private var _proteins: MutableList[Protein] = MutableList[Protein]()
  private var _radius = 0.0f
  private var _membraneUnitRadius = 20.0f
  
  
  
  /* 
   * Testing Variables
   */
  private var _lastId = 0
  
  
  private def contains(gameObject: GameObject): Boolean = (
    gameObject.position.dist(center) < _radius + gameObject.radius - _membraneUnitRadius * 2
  )
  /*
   * Orient the membranes cells around the center
   */
  private def orientMembrane {
    var angle = 2.0f * Math.PI / _membrane.length
    _radius = ((_membraneUnitRadius * _membrane.length) / Math.PI).toFloat
    
    // reposition membrane units
    for(i <- 0 until _membrane.length) {
      _membrane(i).position = new PVector(
                                    (_center.x + _radius * Math.cos(i*angle)).toFloat,
                                    (_center.y + _radius * Math.sin(i*angle)).toFloat
                                 )
    }    
  }
    
  /*
   * Grows the cell's membrane by count units.
   */
  def grow(count: Int) {
    
    // add the number of new units to the membrane
    for(i <- 0 until count) {
      _membrane :+= new MembraneUnit(pApp) {
        radius = _membraneUnitRadius
        id = _lastId
      }
      _lastId += 1
    }
    
    orientMembrane
  }
  
  
  def collectProtein(proteins: Array[Protein]) {
    // find all the protein in the cell
    for(protein <- proteins) {
      if(contains(protein)) {
        _proteins :+= protein
        protein.marked = true
      }
    }
    
  }
  /*
   * If the cell contains enough protein, it will grow.
   */
  def checkForGrowth(proteins: Array[Protein]) {

    var inCell = MutableList[Protein]()
    
    // find all the protein in the cell
    for(protein <- proteins) {
      if(contains(protein)) {
        inCell :+= protein
        protein.marked = true
      }
    }
    
    // check if there is enough protein for cell growth
    if(!inCell.isEmpty) {
      grow(inCell.length)
    }
    
  }
  
  def divide: Array[Cell] = {
    if(_membrane.length >= 8) 
    {
      var cells = Array(new Cell(pApp), new Cell(pApp))
      cells(0).grow(_membrane.length/2)
      cells(1).grow(_membrane.length/2 + {
        if(_membrane.length % 2 == 0) 0 else 1
      })
      
      _membrane = MutableList[MembraneUnit]()
      
      cells(0).center = new PVector(center.x - _radius, center.y)
      cells(1).center = new PVector(center.x + _radius, center.y)
      
      cells
    } else {
      null
    }
  }
  
  /*
   * This is meant to open and close the membrane visually when a protein collides with it.
   * 
   * TODO: I'm going to forgo this for a little while because its so difficult (10).
   */
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
    _proteins.foreach {
      e => {
        e.draw
      }
    }
  }
  
  def move {
    for(i <- 0 until _membrane.length) {
      _membrane(i).move
    }
  }
  
  
  
  def center = _center
  def center_=(value: PVector) {
    var delta = new PVector(_center.x - value.x, _center.y - value.y)
    
    
    _center.x = value.x
    _center.y = value.y
    
    
    
    _proteins.foreach {
      e => {
        e.position.x -= delta.x 
        e.position.y -= delta.y
      }
    }
    orientMembrane
    
    
  }
  
  def membrane = _membrane
  def membrane_=(value: MutableList[MembraneUnit]) {
    _membrane = value
  }
  
  
  def membraneUnitRadius = _membraneUnitRadius
  def membraneUnitRadius_=(value: Float) {
    _membraneUnitRadius = value
    for(membraneUnit <- membrane) {
      membraneUnit.radius = value
    }
  }
  
  
  
  
  
}