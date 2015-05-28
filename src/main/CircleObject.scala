package main

import processing.core._
import vectors._

/*
 * x, y are center
 */



class CircleObject(pApp: PApplet) extends GameObject(pApp) {
  
  protected var _radius: Float = 20
  
  
  override def draw {
    pApp.fill(color)
    pApp.ellipseMode(PConstants.CENTER)
    pApp.ellipse(_position.x, _position.y, _radius * 2, _radius * 2)
  }
  
  override def contains(pos: Vector): Boolean = ( 
      pos.distance(_position) <= _radius
  )
  
  override def collidesWith(gameObject: GameObject): Boolean = (
       gameObject match {
         case circle: CircleObject => {
           gameObject.position.distance(_position) <= _radius + circle.radius
         }
         case rectangle: RectangleObject => {
           // TODO
           false
         }
         case _ => false
       }
  )
  
  
  
  
  
  
  
  
  
  def radius = _radius
  def radius_=(value: Float) = _radius = value

}