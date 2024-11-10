{
  pkgs ? import <nixpkgs> { },
}:
let
  java = pkgs.jdk21;
  gradle = pkgs.gradle.override { inherit java; };
  kotlin = pkgs.kotlin.override { jre = java; };
  # kotlin-language-server = pkgs.kotlin-language-server.override { jre = java; };
in
with pkgs;
mkShell {
  packages = [
    java
    gradle
    kotlin
    spring-boot-cli
    kotlin-language-server
  ];
}
