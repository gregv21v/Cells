package main

import processing.core._
import vectors._

class RectangleObject(pApp: PApplet) extends GameObject(pApp) {
  
  protected var _width: Float = 40
  protected var _height: Float = 40
  
  override def draw  {
    pApp.fill(color)
    pApp.rect(_position.x, _position.y, _width, _height)
  }
  
  override def contains(pos: Vector): Boolean = (
        x >= _position.x
     && x <= _position.x + _width 
     && y >= _position.y 
     && y <= _position.y + _height
  )
  
  override def collidesWith(gameObject: GameObject): Boolean = gameObject match {
    case circle: CircleObject => {
      // TODO
      false
    }
    case rectangle: RectangleObject => (
         contains(rectangle.position)
      || contains(rectangle.position + new Vector(rectangle.width, 0))
      || contains(rectangle.position + new Vector(0, rectangle.height))
      || contains(rectangle.position + new Vector(rectangle.width, rectangle.height))
    )
    case _ => false
  }    
  
  
  
  def height = _height
  def height_=(value: Float) = _height = value
  
  def width = _width
  def width_=(value: Float) = _width = value
  

}