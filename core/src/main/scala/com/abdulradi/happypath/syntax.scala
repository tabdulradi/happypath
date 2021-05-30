package com.abdulradi.happypath
package syntax

import scala.util.control.NonFatal

object throwables:
  given ErrorCase[Throwable] = ErrorCase.derived[Throwable]

  def catchNonFatal[A](f: => A): Throwable | A =
    try f
    catch case NonFatal(e) => e