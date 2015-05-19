package main

import processing.core._

class MembraneUnit(pApp: PApplet) extends GameObject(pApp) {
  
  private var _id = 0;

  
  override def draw {
    pApp.fill(_color)
    pApp.ellipse(_position.x, _position.y, _radius * 2, _radius * 2)
    
    pApp.fill(0, 255, 0)
    pApp.textSize(20);
    pApp.textAlign(PConstants.CENTER, PConstants.CENTER)
    pApp.text("" + id, _position.x, _position.y)
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

  def id = _id
  def id_=(value: Int) = _id = value

  
  
  

}