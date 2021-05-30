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

open class UnhappyCase[E <: Matchable] extends ForSyntax[E]:
  extension [A <: Matchable](a: E | A) 
    inline def fold[B](inline fe: E => B, inline fa: A => B): B =
      a match
        case e: E => fe(e)
        case a: A => fa(a)

    inline def mapError[E2](f: E => E2): E2 | A =
      a match
        case e: E => f(e)
        case a: A => a

    inline def biMap[E2, A2](fe: E => E2, fa: A => A2): E2 | A2 =
      fold(fe, fa)

    inline def orElse[EE >: E, AA >: A](fallback: => EE | AA): EE | AA =
      a match
        case e: E => fallback
        case a: A => a
          
    inline def getOrElse[AA >: A](fallback: => AA): E | AA =
      orElse(fallback)

    inline def contains[AA >: A](elem: AA): Boolean =
      a match
        case e: E => false
        case a: A => elem == a

    inline def forall(predicate: A => Boolean): Boolean =
      a match
        case e: E => true
        case a: A => predicate(a)

    inline def exists(predicate: A => Boolean): Boolean =
      a match
        case e: E => false
        case a: A => predicate(a)

    inline def filterOrElse[EE >: E](predicate: A => Boolean, fallback: => EE): EE | A =
      a match
        case e: E => e
        case a: A => if predicate(a) then a else fallback
    
    inline def toSeq: Seq[A] = 
       a match
        case e: E => Seq.empty
        case a: A => Seq(a)

    inline def toEither: Either[E, A] = 
      fold(Left(_), Right(_))
    
    inline def toOption: Option[A] = 
       a match
        case e: E => None
        case a: A => Some(a)

object UnhappyCase:
  def derived[E <: Matchable]: UnhappyCase[E] = UnhappyCase[E]
