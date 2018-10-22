name := """play-java-seed"""
organization := "yaokai"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.6"

libraryDependencies += guice

libraryDependencies += "org.neo4j" % "neo4j-ogm-core" % "3.1.4"
libraryDependencies += "org.neo4j" % "neo4j-ogm-bolt-driver" % "3.1.4"