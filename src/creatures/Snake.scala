package creatures

import processing.core._
import scala.collection.mutable._
import vectors._
import main.GameObject
import main.StructuralUnit
import proteins._

class Snake(pApp: PApplet) extends Creature(pApp) {

  private var _head = new StructuralUnit(pApp) {
    position.x = 0
    position.y = 0
  }

  override def draw {
    super.draw
    if(_enabled)
      _head.draw
  }
  
  override def move {
    _position += _velocity
    move(_position)
  }
  
  override def grow(count: Int) {
    for(i <- 0 until count) {
      var seg = new StructuralUnit(pApp)
      if(_structure.length%2 == 0)
        seg.color = pApp.color(0, 255, 0)
      else 
        seg.color = pApp.color(255, 0, 0)
  
      if(_structure.isEmpty) {
        seg.x = _head.x - seg.radius * 2
        seg.y = _head.y
      } else {
        seg.x = _structure(_structure.length-1).x - seg.radius * 2
        seg.y = _structure(_structure.length-1).y
      } 
      _structure :+= seg
    }
  }
  
  /*
   * The snake passively consumes proteins
   */
  override def interact(proteins: Array[Protein]) {
    if(enabled)
      for(protein <- proteins) {
        if(_head.collidesWith(protein)) {
          protein.marked = true
          grow(1)
        }
      }
  }

  
  def move(newPos: Vector) {
    var percent: Float = _head.position.distance(newPos) / (_head.radius * 2)
    
    var current: StructuralUnit = null
    var previous: StructuralUnit = null
    
    if(!_structure.isEmpty) {
      for(i <- (_structure.length - 1) to 1 by -1) {
        // previous element
        current = _structure(i)
        
        // next element
        previous = _structure(i-1)
        
         current.position += (previous.position - current.position) * percent
      }
      
      current = _structure(0)
      previous = _head
      
      current.position += (previous.position - current.position) * percent
    }
    _head.position = newPos
  }
  
  
  override def position_=(value: Vector) {
    super.position = value
    move(position)
  }
}