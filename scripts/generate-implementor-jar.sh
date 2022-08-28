#!/bin/bash

KGEORGIY_REPOSITORY="../../java-advanced-2022"
KGEORGIY_IMPLEMENTOR_MODULE="${KGEORGIY_REPOSITORY}/modules/info.kgeorgiy.java.advanced.implementor"
KGEORGIY_IMPLEMENTOR_PACKAGE="${KGEORGIY_IMPLEMENTOR_MODULE}/info/kgeorgiy/java/advanced/implementor"

# kgeorgiy's interfaces and classes
IMPLER="${KGEORGIY_IMPLEMENTOR_PACKAGE}/Impler"
JAR_IMPLER="${KGEORGIY_IMPLEMENTOR_PACKAGE}/JarImpler"
IMPLER_EXCEPTION="${KGEORGIY_IMPLEMENTOR_PACKAGE}/ImplerException"

# solutions
SOLUTIONS="../java-solutions/info/kgeorgiy/ja/Podtsepko"
IMPLEMENTOR_IMPLEMENTATION="${SOLUTIONS}/implementor"

PATH_TO_JAR="Implementor.jar"

javac \
  ${IMPLER}.java \
  ${JAR_IMPLER}.java \
  ${IMPLER_EXCEPTION}.java \
  ${IMPLEMENTOR_IMPLEMENTATION}/*.java

jar --create \
  --file=${PATH_TO_JAR} \
  --manifest=${IMPLEMENTOR_IMPLEMENTATION}/META_INF/MANIFEST.MF \
  -C ../java-solutions/ \
  "info/kgeorgiy/ja/Podtsepko/implementor/Implementor.class" \
  -C ../java-solutions/ \
  "info/kgeorgiy/ja/Podtsepko/implementor/ClassGenerator.class"

rm \
  ${IMPLER}.class \
  ${JAR_IMPLER}.class \
  ${IMPLER_EXCEPTION}.class \
  ${IMPLEMENTOR_IMPLEMENTATION}/*.class
