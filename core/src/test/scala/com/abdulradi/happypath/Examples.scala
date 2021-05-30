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


@main def forSyntaxExample(): Unit = 
  enum AppError derives UnhappyCase:
    case Err1(foo: String)
    case Err2(bar: Int)
    
  val intOrErr: AppError | Int = 42
  val strOrErr: AppError | String = "test"

  for 
    i <- intOrErr 
    s <- strOrErr
  do println(s"$s $i")

@main def throwablesExample(): Unit = 
  import syntax.throwables.{*, given}

  val res = for 
    x <- catchNonFatal(1 / 1) 
    y <- catchNonFatal(1 / 0)
  yield x + y

  println(res.fold(e => s"Error = $e", success => s"Res = $success"))
  println(res.toEither)
  println(res.toTry)
  println(res.getOrThrow)
