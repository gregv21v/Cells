package main

import processing.core._
import vectors._

class Bounds (
  var minX: Float = 0,
  var minY: Float = 0,
  var maxX: Float = 0,
  var maxY: Float = 0
) {
  
  def contains(point: Vector): Boolean = (
       point.x >= minX
    && point.x <= maxX 
    && point.y >= minY 
    && point.y <= maxY
  )
  
  def within(gameObject: GameObject): Boolean = gameObject match {
    case rect: RectangleObject => (
         contains(rect.position)
      && contains(new Vector(rect.x + rect.width, rect.y))
      && contains(new Vector(rect.x, rect.y + rect.height))
      && contains(new Vector(rect.x + rect.width, rect.y + rect.height))
    )
    case circle: CircleObject => (
         circle.x - circle.radius > minX
      && circle.x + circle.radius < maxX 
      && circle.y - circle.radius > minY 
      && circle.y + circle.radius < maxY
    )
    
    case _ => false
  }

  
  
}