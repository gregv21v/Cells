/*
 * A form mandates how a creature grows, and moves around
 */

package creatures

import vectors._
import processing.core._
import main.StructuralUnit
import proteins.Protein
import scala.collection.mutable._

class Creature(pApp: PApplet) {
  protected var _velocity = new Vector
  protected var _position = new Vector
  protected var _structure: MutableList[StructuralUnit] = MutableList[StructuralUnit]()
  protected var _enabled = true
  
  
  
  
  
  def reproduce: Array[Creature] = null
  
  // this is what happens when the proteins touch the creature
  def interact(proteins: Array[Protein]) {}
 
  // this is what happens when the q key is pressed
  def eat {}
  
  def grow(count: Int) {}
  def move {
    for(unit <- _structure) {
      unit.move
    }
  }
  def draw {
    if(_enabled)
      for(unit <- _structure) {
        unit.draw
      }
  }
  
  
  def velocity = _velocity
  def velocity_=(value: Vector) {
    _velocity.x = value.x
    _velocity.y = value.y
    
    for(unit <- _structure) {
      unit.velocity = value
    }
    
  }
  
  
  def position = _position
  def position_=(value: Vector) {
    _position.x = value.x
    _position.y = value.y
  }
  
  def x = _position.x
  def x_=(value: Float) = {
    _position.x = value
  }
  
  def y = _position.y
  def y_=(value: Float) = {
    _position.y = value
  }
  
  def enabled = _enabled
  def enabled_=(value: Boolean) = _enabled = value
}