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

class ErrorCase[E <: Throwable](using E: TypeTest[E | Any, E]) extends UnhappyCase[E]:
  extension [A](aOrE: E | A) 
    inline def getOrThrow: A = 
      aOrE.fold(throw _, identity)

    inline def toTry: scala.util.Try[A] = 
      aOrE.fold(scala.util.Failure(_), scala.util.Success(_))

object ErrorCase:
  def derived[E <: Throwable](using E: TypeTest[E | Any, E]): ErrorCase[E] = ErrorCase[E]
