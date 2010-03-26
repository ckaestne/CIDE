#! /bin/sh

rm -rf ./releases
mkdir releases
mkdir releases/base
mkdir releases/compression
mkdir releases/TLS

ant deployedSuite
cp deployed/lampiro.ja* releases/base/
zip -j releases/base/lampiro.zip releases/base/lampiro.jad releases/base/lampiro.jar

ant deployedSuite -propertyfile eclipseme-build-compression.properties build
cp deployed/lampiro.ja* releases/compression/
zip -j releases/compression/lampiro.zip releases/compression/lampiro.jad releases/compression/lampiro.jar

ant deployedSuite -propertyfile eclipseme-build-TLS.properties build
cp deployed/lampiro.ja* releases/TLS/
zip -j releases/TLS/lampiro.zip releases/TLS/lampiro.jad releases/TLS/lampiro.jar
