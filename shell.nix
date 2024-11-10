{
  pkgs ? import <nixpkgs> { config.allowUnfree = true; },
}:

let
  nodejs = pkgs.nodejs_22;
in
with pkgs;
mkShell {
  packages = [
    google-chrome
    nodejs
    nodePackages_latest.npm
    nodePackages_latest."@angular/cli"
    firebase-tools
  ];
  CHROME_BIN="google-chrome-stable";
}
