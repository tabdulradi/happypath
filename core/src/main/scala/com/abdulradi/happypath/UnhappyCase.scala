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

open class UnhappyCase[E](using E: TypeTest[E | Any, E]) extends ForSyntax[E]:
  extension [A](aOrE: E | A) 
    inline def fold[B](inline fe: E => B, inline fa: A => B): B =
      aOrE match
        case e: E => fe(e)
        case _ => fa(aOrE.asInstanceOf[A])

    inline def mapError[E2](f: E => E2): E2 | A =
      fold(f, identity)

    inline def biMap[E2, A2](fe: E => E2, fa: A => A2): E2 | A2 =
      fold(fe, fa)

    inline def orElse[EE >: E, AA >: A](fallback: => EE | AA): EE | AA =
      fold(_ => fallback, identity)
          
    inline def getOrElse[AA >: A](fallback: => AA): E | AA =
      orElse(fallback)

    inline def contains[AA >: A](elem: AA): Boolean =
      fold(_ => false, elem == _)

    inline def forall(predicate: A => Boolean): Boolean =
      fold(_ => true, predicate)

    inline def exists(predicate: A => Boolean): Boolean =
      fold(_ => false, predicate)

    inline def filterOrElse[EE >: E](predicate: A => Boolean, fallback: => EE): EE | A =
      aOrE.flatMap(a => if predicate(a) then a else fallback)
    
    inline def toSeq: Seq[A] = 
      fold(_ => Seq.empty, Seq(_))

    inline def toEither: Either[E, A] = 
      fold(Left(_), Right(_))
    
    inline def toOption: Option[A] = 
      fold(_ => None, Some(_))

object UnhappyCase:
  def derived[E](using E: TypeTest[E | Any, E]): UnhappyCase[E] = UnhappyCase[E]
