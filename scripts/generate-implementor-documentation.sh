#!/bin/bash

KGEORGIY_REPOSITORY="../../java-advanced-2022"
KGEORGIY_IMPLEMENTOR_MODULE="${KGEORGIY_REPOSITORY}/modules/info.kgeorgiy.java.advanced.implementor"
KGEORGIY_IMPLEMENTOR_PACKAGE="${KGEORGIY_IMPLEMENTOR_MODULE}/info/kgeorgiy/java/advanced/implementor"

# kgeorgiy's interfaces and classes
IMPLER="${KGEORGIY_IMPLEMENTOR_PACKAGE}/Impler.java"
JAR_IMPLER="${KGEORGIY_IMPLEMENTOR_PACKAGE}/JarImpler.java"
IMPLER_EXCEPTION="${KGEORGIY_IMPLEMENTOR_PACKAGE}/ImplerException.java"

# solutions
SOLUTIONS="../java-solutions/info/kgeorgiy/ja/Podtsepko"
IMPLEMENTOR_IMPLEMENTATION="${SOLUTIONS}/implementor"

javadoc -private -d ../javadoc/Implementor \
  ${IMPLER} \
  ${JAR_IMPLER} \
  ${IMPLER_EXCEPTION} \
  ${IMPLEMENTOR_IMPLEMENTATION}/*.java
