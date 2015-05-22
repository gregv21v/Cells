package proteins

import processing.core._
import main.GameObject
/*
 * When a protein push on the membrane of a cell,
 * the individual membrane units the protein is pushing
 * on pivot into the cell around the membrane units adjacent to them.
 * 
 * 
 * 
 */


class Protein(pApp: PApplet) extends GameObject(pApp) {
  
  private var _name = "None"
  private var _marked = false // marked to be deleted

  override def draw {
    pApp.fill(_color)
    pApp.ellipse(_position.x, _position.y, _radius * 2, _radius * 2)
  }
  
  
  
  def name = _name
  def name_=(value: String) = _name = value
  
  
  def marked = _marked 
  def marked_=(value: Boolean) = _marked = value
  
}