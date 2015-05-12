package main

import processing.core._

class MembraneUnit(pApp: PApplet) {
  
  private var _position = new PVector
  private var _radius = 0.0f
  private var _color = pApp.color(255, 0, 0)
  
  private var _velocity = new PVector
  
  
  
  
  def draw {
    pApp.fill(_color)
    pApp.ellipse(_position.x, _position.y, _radius * 2, _radius * 2)
  }
  
  def move {
    _position.add(_velocity)
  }
  
  def collidesWith(membraneUnit: MembraneUnit): Boolean = {
    membraneUnit.position.dist(_position) < membraneUnit.radius + _radius
  }
  
  def collisionInteraction(membraneUnit: MembraneUnit) { 
   velocity = new PVector(position.x - membraneUnit.position.x,
                          position.y - membraneUnit.position.y);
   velocity.normalize();
                      
   membraneUnit.velocity = new PVector(membraneUnit.position.x - position.x,
                                       membraneUnit.position.y - position.y);
   membraneUnit.velocity.normalize();
  }
  
  def radius = _radius
  def radius_=(value: Float) = _radius = value
  
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
  
  

}