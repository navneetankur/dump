{
  # doing this in overly complicated way, for reference
  description = "flake for custom nnn";

  outputs = { self, nixpkgs }: {
    packages.x86_64-linux.nnn =
      let ov = self: super: {
          nnn = super.nnn.override{ conf = builtins.readFile ./nnn.h; };
      }; in
      with import nixpkgs { system = "x86_64-linux"; overlays = [ ov ]; };
      nnn.overrideAttrs(oldAttrs: rec{
        version = "4.2a";
        src=fetchFromGitHub {
            owner = "jarun";
            repo = "nnn";
            rev = "8cebc69e710dec12fdef059b50d90f76b638b013";
            sha256 = "sha256-qCRqSa3qNHlaJ/lLjBJygJ5N+okvvP1v9jVlf+HuXXs=";
        };});
  };
}
