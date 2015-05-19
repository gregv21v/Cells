package main

import processing.core._
/*
 * When a protein push on the membrane of a cell,
 * the individual membrane units the protein is pushing
 * on pivot into the cell around the membrane units adjacent to them.
 * 
 * 
 * 
 */


class Protein(pApp: PApplet) extends GameObject(pApp) {

  override def draw {
    pApp.fill(_color)
    pApp.ellipse(_position.x, _position.y, _radius * 2, _radius * 2)
  }
  
  
}