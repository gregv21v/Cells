/*
 * Cell
 * 
 * 
 * A cell consists of a permeable membrane that deforms to let 
 * objects in, then reforms.
 * 
 * 
 */

/* TODO: use the movement of the cell to calculate the forces applied by incoming proteins */
package creatures

import processing.core._
import vectors._
import scala.collection.mutable._
import proteins.Protein
import main.CircleObject
import main.StructuralUnit


class Cell(pApp: PApplet) extends Creature(pApp) {
  
  private var _proteins: MutableList[Protein] = MutableList[Protein]()
  private var _radius = 0.0f
  private var _membraneUnitRadius = 20.0f
  
  
  /* 
   * Testing Variables
   */
  private var _lastId = 0
  
  
  private def contains(circleObject: CircleObject): Boolean = (
    circleObject.position.distance(center) < _radius + circleObject.radius - _membraneUnitRadius * 2
  )
  /*
   * Orient the membranes cells around the center
   */
  private def orientMembrane {
    var angle = 2.0f * Math.PI / _structure.length
    _radius = ((_membraneUnitRadius * _structure.length) / Math.PI).toFloat
    
    // reposition membrane units
    for(i <- 0 until _structure.length) {
      _structure(i).position = new Vector(
                                    (_position.x + _radius * Math.cos(i*angle)).toFloat,
                                    (_position.y + _radius * Math.sin(i*angle)).toFloat
                                 )
      // force holding the cell together      
      _structure(i).directionToCenter = (_structure(i).position - center).normalize
    }    
    
  }
  /*
   * Grows the cell's membrane by count units.
   */
  override def grow(count: Int) {
    
    // add the number of new units to the membrane
    for(i <- 0 until count) {
      _structure :+= new StructuralUnit(pApp) {
        radius = _membraneUnitRadius
        id = _lastId
      }
      _lastId += 1
    }
   
   orientMembrane
  }
  
  /*
   * The cell eats proteins to grow.
   */
  override def eat {
    
    // consume the proteins needed to grow  
    if(2 <= _proteins.length) {
      _proteins = _proteins.drop(2)
      grow(1)
    }
    
  }
  
  /*
   * Returns an array of offspring
   */
  override def reproduce: Array[Creature] = {
    if(_structure.length >= 10) 
    {
      var cells = Array(new Cell(pApp), new Cell(pApp))
      cells(0).grow(_structure.length/2)
      cells(1).grow(_structure.length/2 + {
        if(_structure.length % 2 == 0) 0 else 1
      })
      
      _structure = MutableList[StructuralUnit]()
      
      cells(0).center = new Vector(center.x - _radius, center.y)
      cells(0).orientMembrane
      cells(1).center = new Vector(center.x + _radius, center.y)
      cells(1).orientMembrane
      
      cells.asInstanceOf[Array[Creature]]
    } else {
      null
    }
  }
  
  /*
   * This is meant to open and close the membrane visually when a protein collides with it.
   * 
   * TODO: I'm going to forgo this for a little while because its so difficult (10).
   */
  override def interact(proteins: Array[Protein]) {
    
    // collect the proteins within the cell
    if(enabled)
      for(protein <- proteins) {
        if(contains(protein)) {
          _proteins :+= protein
          protein.color = pApp.color(128, 128, 0)
          protein.marked = true
        }
      }
    
    /*
     * Forces, and Physics solution
     */
    /*
    for(protein <- proteins) {
      
      // find the membrane units being collided with
      var collisionIndices = MutableList[Int]()
      for(i <- 0 until _structure.length) {
        _structure(i).additionalForce = new Vector
        if(protein.position.distance(_structure(i).position) < protein.radius + _structure(i).radius) {
          collisionIndices :+= i
        }
      }
      
      // apply forces to the membraneUnits that the protein is colliding with
      for(index <- collisionIndices) {
        _structure(index).additionalForce = protein.position - _structure(index).position
      }
    }
    */
    
    
    
    /*
     * Pivot/Joint System 
     */
    
    // if inside the cell, stop the proteins movement
    
    // otherwise, push the membrane units into the cell
    // radially move the the collided membrane units
    /*
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
    */
    // then reform when the protein is fully in the cell
  }
  
  def internalCollision {
    for(i <- 0 until _structure.length) {
      for(j <- i+1 until _structure.length) {
        if(_structure(i).collidesWith(_structure(j))) {
          _structure(i).collisionInteraction(_structure(j))
        }
      }
    } 
  }
  
  override def draw {
    super.draw
    if(enabled)
      _proteins.foreach {
        e => {
          e.draw
        }
      }
  }
  
  override def move {  
    _position += _velocity
    
    super.move
    
    for(protein <- _proteins) {
      protein.move
    }
    
    
  }

  override def velocity_=(value: Vector) {
    super.velocity = value
    for(protein <- _proteins) {
      protein.velocity = value
    }
  }
  
  def center = _position
  def center_=(value: Vector) {
    _position.x = value.x
    _position.y = value.y
  }
  
  
  
  def membraneUnitRadius = _membraneUnitRadius
  def membraneUnitRadius_=(value: Float) {
    _membraneUnitRadius = value
    for(unit <- _structure) {
      unit.radius = value
    }
  }
  
  def radius = _radius
  def proteins = _proteins
  
  
  
}