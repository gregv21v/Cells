


package main

import processing.core._
import scala.collection.mutable._
import scala.util.Random
import proteins.Protein
import creatures._


class World(pApp: PApplet) {
  private var _bounds = new Bounds(0, 0, 400, 400)
  private var _creatures = MutableList[Creature]()
  private var _proteins = {
    for(i <- 0 until 20) yield {
      new Protein(pApp) {
        var rand = new Random
        position.x = rand.nextInt(pApp.width/2 - 200)
        position.y = rand.nextInt(pApp.height/2 - 200)
        color = pApp.color(0, 0, 255)
      }
    }
  }


  
  
  def interactions {
  
    
    _creatures.foreach {
      e => {
        e.interact(_proteins.toArray)
      }
    }
    
    _proteins = _proteins.filter { x => !x.marked }
    
    

  }
  
  
  def draw {
    
    _proteins.foreach {
      e => {
        e.draw 
      }
    }
    _creatures.foreach {
      e => {
        e.draw
      }
    }
  }
  
  
  def move {
    _proteins.foreach {
      e => {
        e.move
      }
    }
    _creatures.foreach {
      e => {
        e.move
      }
    }
  }
  
  
  
  
  def :+=(creature: Creature) {
    _creatures :+= creature
  }
}