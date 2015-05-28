package main


import processing.core._
import vectors._
import collection.mutable._

class GameObject(pApp: PApplet) {
  
  protected var _position = new Vector
  protected var _color = pApp.color(255, 0, 0)
  protected var _velocity = new Vector
  protected var _acceleration = new Vector
  protected var _visible = true
 
  def draw {}
 
  def move {
    position += _velocity
    _velocity += _acceleration
  }
 
  def contains(point: Vector): Boolean = false
  def collidesWith(gameObject: GameObject): Boolean = false

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
  
  def velocity = _velocity
  def velocity_=(value: Vector) {
    _velocity.x = value.x
    _velocity.y = value.y
  }
  
  def acceleration = _acceleration
  def acceleration_=(value: Vector) {
    _acceleration.x = value.x
    _acceleration.y = value.y
  }
 
  def color = _color
  def color_=(value: Int) {
    _color = value
  }
  
  
  def visible = _visible
  def visible_=(value: Boolean) = _visible = value

}