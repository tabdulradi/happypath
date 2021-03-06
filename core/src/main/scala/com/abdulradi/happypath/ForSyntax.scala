/*
 * Copyright 2021 Tamer Abdulradi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.abdulradi.happypath

import scala.reflect.TypeTest

open class ForSyntax[E](using E: TypeTest[E | Any, E]):
  extension [A](aOrE: E | A)
    inline def flatMap[B](f: A => E | B): E | B =
      aOrE match
        case e: E => e
        case _ => f(aOrE.asInstanceOf[A])

    inline def map[B](f: A => B): E | B =
      flatMap(f)

    inline def foreach(f: A => Any): Unit = 
      flatMap(f)

object ForSyntax:
  def derived[E](using TypeTest[E | Any, E]): ForSyntax[E] = ForSyntax[E]
