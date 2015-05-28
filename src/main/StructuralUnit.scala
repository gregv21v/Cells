package main

import processing.core._
import vectors._
import scala.collection.mutable._
import creatures._

class StructuralUnit(pApp: PApplet) extends CircleObject(pApp) {
  
  private var _id = 0;
  private var _additionalForce = new Vector // constant force that is pushing on the cell
  private var _directionToCenter = new Vector
     
  // the force that keeps the cell together
  def nuclearForce(cell: Cell): Vector = directionToCenter * (cell.radius - cell.center.distance(position)) 
  
  def updateAcceleration(cell: Cell) {
    acceleration = nuclearForce(cell) + _additionalForce
  }
  
  /*
  override def draw {
    pApp.fill(_color)
    pApp.ellipse(_position.x, _position.y, _radius * 2, _radius * 2)
    
    pApp.fill(0, 255, 0)
    pApp.textSize(20);
    pApp.textAlign(PConstants.CENTER, PConstants.CENTER)
    pApp.text("" + id, _position.x, _position.y)
  }
  */
  
  def collidesWith(membraneUnit: StructuralUnit): Boolean = {
    membraneUnit.position.distance(_position) < membraneUnit.radius + _radius
  }
  
  def collisionInteraction(membraneUnit: StructuralUnit) { 
   velocity = (position - membraneUnit.position).normalize        
   membraneUnit.velocity = (membraneUnit.position - position).normalize
  }

  def id = _id
  def id_=(value: Int) = _id = value

  def additionalForce = _additionalForce
  def additionalForce_=(value: Vector) {
    _additionalForce.x = value.x
    _additionalForce.y = value.y
  }
  
  def directionToCenter = _directionToCenter
  def directionToCenter_=(value: Vector) {
    _directionToCenter = value
  }
}