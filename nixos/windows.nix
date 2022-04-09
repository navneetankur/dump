{ config, pkgs, inputs, ... }: 
let nixpkgs-unstable = import inputs.nixpkgs-unstable{ system =  "x86_64-linux"; config = { allowUnfree = true; }; };
nixpkgs20_09 = import inputs.nixpkgs20_09{ system =  "x86_64-linux"; config = { allowUnfree = true; }; };
libvirt-unstable = 
      pkgs.libvirt.overrideAttrs(oldAttrs: rec{
        version = "7.2.0";
        src=builtins.fetchurl {
			url = "https://libvirt.org/sources/libvirt-7.2.0.tar.xz";
			sha256 = "sha256:1l6i1rz1v9rnp61sgzlrlbsfh03208dbm3b259i0jl5sqz85kx01";
        };});
in
{


boot.kernelParams = [ "intel_iommu=on,igfx_off" "iommu=pt" "vfio-pci.ids=10de:1b80,10de:10f0" "default_hugepagesz=1G" "hugepagesz=1G" "hugepages=16" "audit=0" ];
boot.kernelModules = [ "vfio" "vfio_iommu_type1" "vfio_pci" "vfio_virqfd" ];
#boot.initrd.kernelModules = [ "vfio_virqfd" "vfio_pci" "vfio_iommu_type1" "vfio" ];
  environment.systemPackages = with pkgs; [
    virtmanager
    qemu_kvm
	usbutils
	pciutils
	scream
	looking-glass-client
	ddcutil
  ];
virtualisation.libvirtd.onShutdown = "shutdown";
virtualisation.libvirtd.package = libvirt-unstable;
virtualisation.libvirtd.enable = true;
virtualisation.libvirtd.onBoot = "ignore";
virtualisation.libvirtd.qemuRunAsRoot = true;
virtualisation.libvirtd.qemuPackage = pkgs.qemu_kvm;
virtualisation.libvirtd.qemuOvmf = true;
virtualisation.libvirtd.allowedBridges = [ "virbr0" ];
boot.blacklistedKernelModules = [ "nvidia" "nouveau" ];
users.groups.libvirtd.members = ["navn"];
systemd.tmpfiles.rules = [ "f /dev/shm/looking-glass 0660 navn kvm -" ];

networking.interfaces.enp0s31f6.useDHCP = pkgs.lib.mkForce false;
#networking.interfaces.enp0s31f6.ipv4.addresses = [{ address = "192.168.1.10"; prefixLength = 24;}];
#networking.defaultGateway.interface = "enp0s31f6";
networking.defaultGateway.address = "192.168.1.1";
networking.nameservers = ["192.168.1.1"];

networking.interfaces.virbr0.useDHCP = false;
networking.bridges.virbr0.interfaces = [ "enp0s31f6" ];
networking.interfaces.virbr0.ipv4.addresses = [{ address = "192.168.1.10"; prefixLength = 24;}];
networking.defaultGateway.interface = "virbr0";
hardware.i2c.enable = true;
users.users.navn.extraGroups = [ "i2c" ];




}
