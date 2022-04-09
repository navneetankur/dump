# Edit this configuration file to define what should be installed on
# your system.  Help is available in the configuration.nix(5) man page
# and in the NixOS manual (accessible by running ‘nixos-help’). 

{ config, pkgs, inputs, ... }:
let nixpkgs-unstable = import inputs.nixpkgs-unstable{ system =  "x86_64-linux"; config = { allowUnfree = true; }; };
in
{

  nix.package = pkgs.nixUnstable;
  nix.extraOptions = ''
  experimental-features = nix-command flakes
  '';
  nixpkgs.config.allowUnfree = true;
  nix.registry.nixpkgs.flake = inputs.nixpkgs;
  imports =
    [ # Include the results of the hardware scan.
      ./hardware-configuration.nix
    ];

  # Use the systemd-boot EFI boot loader.
  boot.loader.systemd-boot.enable = true;
  boot.loader.efi.canTouchEfiVariables = false;

  networking.hostName = "nixos"; # Define your hostname.
  # networking.wireless.enable = true;  # Enables wireless support via wpa_supplicant.

  # Set your time zone.
  time.timeZone = "Asia/Calcutta";

  # The global useDHCP flag is deprecated, therefore explicitly set to false here.
  # Per-interface useDHCP will be mandatory in the future, so this generated config
  # replicates the default behaviour.
  networking.useDHCP = false;
  networking.interfaces.enp0s31f6.useDHCP = true;

  # Configure network proxy if necessary
  # networking.proxy.default = "http://user:password@proxy:port/";
  # networking.proxy.noProxy = "127.0.0.1,localhost,internal.domain";

  # Select internationalisation properties.
  # i18n.defaultLocale = "en_US.UTF-8";
  # console = {
  #   font = "Lat2-Terminus16";
  #   keyMap = "us";
  # };

  # Enable the X11 windowing system.
   services.xserver.enable = true;
   services.xserver.autorun = false;
   #services.xserver.windowManager.xmonad.enable = true;
   services.xserver.windowManager.awesome.enable = true;
   services.xserver.windowManager.awesome.package = pkgs.awesome.override { luaPackages = pkgs.luajitPackages; };
   services.xserver.displayManager.startx.enable = true;
   #services.xserver.displayManager.autologin.enable = true;
   #services.xserver.displayManager.autologin.user = navn;
   #caps:swapescape
   services.xserver.xkbOptions = caps:swapescape;
   


  

  # Configure keymap in X11
  # services.xserver.layout = "us";
  # services.xserver.xkbOptions = "eurosign:e";

  # Enable CUPS to print documents.
  # services.printing.enable = true;

  # Enable sound.
   sound.enable = true;
   hardware.pulseaudio.enable = true;

  # Enable touchpad support (enabled default in most desktopManager).
  # services.xserver.libinput.enable = true;

  # Define a user account. Don't forget to set a password with ‘passwd’.
  # users.users.jane = {
  #   isNormalUser = true;
  #   extraGroups = [ "wheel" ]; # Enable ‘sudo’ for the user.
  # };
   users.mutableUsers = false;
   users.users.navn = {
     isNormalUser = true;
     extraGroups = [ "wheel" ]; # Enable ‘sudo’ for the user.
     hashedPassword = "$6$Qa.l/o99FJbr$XrsjxBbUGkGuacQu0yuFU6D2QbICT79QGMG3EPbEKViuRW6SJMfztbtyQv8KswIFiB3i/dIziRt.JdGuC9GpN1";
     uid = 1000;
   };

  # List packages installed in system profile. To search, run:
  # $ nix search wget
   environment.etc."nixos".source = "/persist/etc/nixos";
   environment.systemPackages = with pkgs; [
     #vim # Do not forget to add an editor to edit configuration.nix! The Nano editor is also installed by default.
     wget
     firefox
     git
     curl
     kitty
     ripgrep
     fd
     # import ./overlays/rofi-as-dmenu/default.nix
     #( pkgs.runCommandLocal "dmenu" ("mkdir -p $out/bin; ln -s ${pkgs.rofi}/bin/rofi $out/bin/dmenu") )
     rofi
     ( pkgs.writeShellScriptBin "dmenu" (''${pkgs.rofi}/bin/rofi -dmenu "$@"'') )
     nixpkgs-unstable.neovim-unwrapped
     #( pkgs.writeShellScriptBin "vim" (''${nixpkgs-unstable.neovim-unwrapped}/bin/nvim "$@"'') )
     #( pkgs.writeShellScriptBin "vi" (''${nixpkgs-unstable.neovim-unwrapped}/bin/nvim "$@"'') )
	 tmux
	 neovim-remote
     sumneko-lua-language-server
     trash-cli
     fzf
     jetbrains.idea-community
     openjdk8
     python39
     numlockx
     dropbox
     redshift
     udiskie
     google-chrome
     mpv
     ffmpeg
     youtube-dl
     clipmenu
     jmtpfs
     fish
     keepassxc
     aria
     pavucontrol
     lua5_4
     go
     gopls
     arc-icon-theme
     xfce.thunar
     xarchiver
     lxappearance
     calibre
     unrar
     zip
	 unzip
     file
     procs
     tree
     hello
	 openconnect
	 tealdeer
	 fbreader
	 gparted
   ];
   programs.zsh.syntaxHighlighting.enable = true;
   programs.zsh.autosuggestions.enable = true;
   programs.zsh.enable = true;

  # Some programs need SUID wrappers, can be configured further or are
  # started in user sessions.
  # programs.mtr.enable = true;
  # programs.gnupg.agent = {
  #   enable = true;
  #   enableSSHSupport = true;
  # };

  # List services that you want to enable:

  # Enable the OpenSSH daemon.
   services.openssh.enable = true;

  # Open ports in the firewall.
  # networking.firewall.allowedTCPPorts = [ ... ];
  # networking.firewall.allowedUDPPorts = [ ... ];
  # Or disable the firewall altogether.
   networking.firewall.enable = false;

  # This value determines the NixOS release from which the default
  # settings for stateful data, like file locations and database versions
  # on your system were taken. It‘s perfectly fine and recommended to leave
  # this value at the release version of the first install of this system.
  # Before changing this value read the documentation for this option
  # (e.g. man configuration.nix or on https://nixos.org/nixos/options.html).
  system.stateVersion = "21.05"; # Did you read the comment?

  services.fstrim.enable = true;
  fileSystems."/".options = [ "ssd" "noatime" "compress=lzo" ];
  fileSystems."/home".options = [ "noatime" "compress=lzo" ];
  fileSystems."/var".options = [ "noatime" "compress=lzo" ];
  fileSystems."/tmp".options = [ "noatime" "compress=lzo" ];
  fileSystems."/boot".options = [ "noatime" ];
  fileSystems."/nix".options = [ "ssd" "noatime" "compress=lzo" ];
  fileSystems."/persist".options = [ "ssd" "noatime" "compress=lzo" ];

  fileSystems."/home/navn/.persist" =
    { device = "/persist";
      fsType = "none";
      options = ["bind"];
    };

  fileSystems."/home/navn/.ramdisk" =
    { device = "tmpfs";
      fsType = "tmpfs";
      options = ["rw" "nodev" "nosuid" "size=1g"];
    };

  fileSystems."/home/navn/.config" =
    { device = "/dev/disk/by-uuid/dceab9a0-8dd1-4a85-9544-f3ba44ee0653";
      fsType = "btrfs";
      options = [ "subvol=config"  "ssd" "noatime" "compress=lzo" ];
    };

  fileSystems."/home/navn/bin" =
    { device = "/dev/disk/by-uuid/dceab9a0-8dd1-4a85-9544-f3ba44ee0653";
      fsType = "btrfs";
      options = [ "subvol=bin"  "ssd" "noatime" "compress=lzo" ];
    };
    fileSystems."/home/navn/win/g" =
        { device = "/dev/disk/by-uuid/4CDCA296DCA27A3A";
          fsType = "ntfs"; 
          options = [ "rw" "uid=1000"];
        };
  fileSystems."/mnt/nas/Navneet" =
    { device = "//192.168.1.50/Navneet";
      fsType = "cifs";
      options = [ "x-systemd.after=network-online.target" "x-systemd.automount" "noauto" "credentials=/persist/secrets/navnas" "uid=1000" ];
    };
  fileSystems."/mnt/nas/Stream" =
    { device = "//192.168.1.50/Stream";
      fsType = "cifs";
      options = [ "x-systemd.after=network-online.target" "x-systemd.automount" "noauto" "credentials=/persist/secrets/navnas" "uid=1000" ];
    };
  fileSystems."/mnt/nas/Public" =
    { device = "//192.168.1.50/Public";
      fsType = "cifs";
      options = [ "x-systemd.after=network-online.target" "x-systemd.automount" "noauto" "credentials=/persist/secrets/navnas" "uid=1000" ];
    };
    #creddentials file format
    # username = <username>
    # password = <password>



}


