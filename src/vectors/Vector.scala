package vectors

import processing.core._


class Vector(private var _x: Float = 0, private var _y: Float = 0) {

  // vector algebra
  def +(vec: Vector) = new Vector(_x + vec.x, _y + vec.y)
  def -(vec: Vector) = new Vector(_x - vec.x, _y - vec.y)
  def /(vec: Vector) = new Vector(_x / vec.x, _y / vec.y)
  def *(vec: Vector) = new Vector(_x * vec.x, _y * vec.y)
  
  // short hand
  def +=(vec: Vector): Unit = { _x += vec.x; _y += vec.y }
  def -=(vec: Vector): Unit = { _x -= vec.x; _y -= vec.y }
  def *=(vec: Vector): Unit = { _x *= vec.x; _y *= vec.y }
  def /=(vec: Vector): Unit = { _x /= vec.x; _y /= vec.y }
  
  
  // scalar algebra
  def +(value: Float): Vector = new Vector(x + value, y + value)
  def -(value: Float): Vector = new Vector(x - value, y - value)
  def /(value: Float): Vector = new Vector(x / value, y / value)
  def *(value: Float): Vector = new Vector(x * value, y * value)
  
  def dot(vec: Vector): Float = x*vec.x + y*vec.y
  
  
  // comparisions
  def <(vec: Vector): Boolean = (x < vec.x && y < vec.y)
  def >(vec: Vector): Boolean = (x > vec.x && y > vec.y)
  
  
  def distance(vec: Vector) = Math.sqrt(
    Math.pow(vec.x - x, 2) + Math.pow(vec.y - y, 2)
  ).toFloat
  
  
  def normalize = {
    var pvec = new PVector(x, y)
    pvec.normalize()
    x = pvec.x
    y = pvec.y
    this
  }
  
  
  
  override def toString = "( " + x + "," + y + " )"
  
  def x = _x
  def x_=(value: Float) = _x = value
  
  def y = _y
  def y_=(value: Float) = _y = value
  
  
  
  

}