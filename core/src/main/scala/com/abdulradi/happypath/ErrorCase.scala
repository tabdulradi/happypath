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

class ErrorCase[E <: Throwable] extends UnhappyCase[E]:
  extension [A <: Matchable](a: E | A) 
    inline def getOrThrow: A = 
       a match
        case e: E => throw e
        case a: A => a

    inline def toTry: scala.util.Try[A] = 
       a match
        case e: E => scala.util.Failure(e)
        case a: A => scala.util.Success(a)

object ErrorCase:
  def derived[E <: Throwable]: ErrorCase[E] = ErrorCase[E]
