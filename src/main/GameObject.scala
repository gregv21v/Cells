package main


import processing.core._
import movers._

class GameObject(pApp: PApplet) {
  
  protected var _position = new PVector
  protected var _radius = 20.0f
  protected var _color = pApp.color(255, 0, 0)
  protected var _velocity = new PVector
  protected var _acceleration = new PVector
  protected var _rotation = 0.0f
  protected var _mover: Mover = null
  
  
  
  def draw {
    pApp.fill(color)
    pApp.ellipse(_position.x, _position.y, _radius, _radius)
  }
 
  def move {
    position.add(_velocity)
  }
  
  

  def radius = _radius
  def radius_=(value: Float) = _radius = value
  
  def rotation = _rotation
  def rotation_=(value: Float) = _rotation = value
  
  def position = _position
  def position_=(value: PVector) {
    _position.x = value.x
    _position.y = value.y
  }
  
  
  def velocity = _velocity
  def velocity_=(value: PVector) {
    _velocity.x = value.x
    _velocity.y = value.y
  }
  
  def acceleration = _acceleration
  def acceleration_=(value: PVector) {
    _acceleration.x = value.x
    _acceleration.y = value.y
  }
  
  

  
  def color = _color
  def color_=(value: Int) {
    _color = value
  }

}