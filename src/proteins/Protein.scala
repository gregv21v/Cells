package proteins

import processing.core._
import main.CircleObject
/*
 * When a protein push on the membrane of a cell,
 * the individual membrane units the protein is pushing
 * on pivot into the cell around the membrane units adjacent to them.
 * 
 * 
 * 
 */


class Protein(pApp: PApplet) extends CircleObject(pApp) {
  
  private var _name = "None"
  private var _marked = false // marked to be deleted

  def name = _name
  def name_=(value: String) = _name = value
  
  
  def marked = _marked 
  def marked_=(value: Boolean) = _marked = value
  
}