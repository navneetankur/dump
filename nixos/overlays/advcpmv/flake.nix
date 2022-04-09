{
  description = "adv cp,mv";

  outputs = { self, nixpkgs }: {


    packages.x86_64-linux.advcpmv = 
      with import nixpkgs{system = "x86_64-linux";};
        (coreutils.override{minimal = true; singleBinary=false;}).overrideAttrs (oldAttrs: rec {
          name = "advcpmv";
          patches = oldAttrs.patches ++ [(fetchpatch {
            name = "advcpmv-0.8-8.32.patch";
            url = "https://raw.githubusercontent.com/jarun/advcpmv/master/advcpmv-0.8-8.32.patch";
            sha256 = "sha256-PEUsHslmHysVC/ImeDhMvvbIiFpSSrSCUgc9fj3utXw=";
          })];
          postPatch = oldAttrs.postPatch + ''
            sed '2i echo Skipping usage vs getopt test && exit 77' -i ./tests/misc/usage_vs_getopt.sh
          '';
          installPhase = ''
          mkdir -p $out/bin
          cp src/cp $out/bin/cpg
          cp src/mv $out/bin/mvg
          '';
          enableParallelBuilding = true;
          doCheck = false;
          outputs = [ "out" ];
        });



  };
}
