{
  description = "flake";
  inputs.nixpkgs-unstable.url = github:nixos/nixpkgs/nixos-unstable;
  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixos-21.05";
  inputs.nixpkgs20_09.url = "github:NixOS/nixpkgs/nixos-20.09";
  inputs.nnn.url = "path:./overlays/nnn/";
  inputs.nnn.inputs.nixpkgs.follows = "nixpkgs";
  inputs.advcpmv.url =  "path:./overlays/advcpmv/";
  inputs.advcpmv.inputs.nixpkgs.follows = "nixpkgs";
  outputs = { self, nixpkgs, ... }@inputs: {

    # replace 'joes-desktop' with your hostname here.
    nixosConfigurations.nixos = nixpkgs.lib.nixosSystem {
      system = "x86_64-linux";
      modules = [ ./configuration.nix
            ({ config, pkgs, inputs, ... }:{ 
               environment.systemPackages = with pkgs; [ 
                    inputs.nnn.packages.x86_64-linux.nnn
                    inputs.advcpmv.packages.x86_64-linux.advcpmv 
                ];
            })
			./windows.nix
      ];
      specialArgs = {inherit inputs;};
    };
  };
}
