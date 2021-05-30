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

inThisBuild(Seq(
  organizationName := "happypath",
  description      := "Makes union types as easy to deal with as scala.Either without the allocation cost",
  homepage         := Some(url("https://github.com/tabdulradi/happypath")),
  scmInfo          := Some(ScmInfo(url("https://github.com/tabdulradi/happypath"), "scm:git@github.com:tabdulradi/happypath.git")),
  startYear := Some(2021),

  scalaVersion := "3.0.0",
))


lazy val core = (project in file("core"))
  .settings(name := "happypath-core")

lazy val root = (project in file("."))
  .aggregate(core)
  .settings(
    name := "happypath",
    publish / skip := true
  )