package main

import processing.core._
import scala.collection.mutable._

class Joint(pApp: PApplet) {
  
  
  
  private var _links = MutableList[GameObject]()
  
  _links :+= new GameObject(pApp)
  _links :+= new GameObject(pApp)
  _links :+= new GameObject(pApp)
  _links :+= new GameObject(pApp)
  _links :+= new GameObject(pApp)
  
  
  
  
  
  def draw {
    _links.foreach {
      _.draw
    }
  }
  
  
  
  
  
  def applyForce(force: PVector, linkIndex: Int) {
    if(linkIndex < _links.length) {
      // apply force to link at linkIndex,
      
      
      
      // then pull all other links with it
    }
    
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  def addLink(link: GameObject) {

    _links :+= link

  }
}