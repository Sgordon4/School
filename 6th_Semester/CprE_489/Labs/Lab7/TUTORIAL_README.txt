To open wireshark for the mininet vm, follow this tutorial under "Set Up Network Access":
https://github.com/mininet/openflow-tutorial/wiki/Set-up-Virtual-Machine

Using virtualBox for this example.
This assumes that there is a host-only adapter set in VB under Settings -> Network -> Atapter 2 (or 3 or 4).
One of these should be enabled and attached to a host-only adapter.
This makes it easier for the VM to connect to the host machine.
This can be checked in File -> Host Network Manager
After launching the VM, this IP can be pinged from windows cmd using 'ping [host-only ip]'

If there is no IP address assigned to the host-only interface in the VM (check with 'ifconfig -a' in the VM),
type 'sudo dhclient eth[# here]' to assign one to the interface with that # (ex. eth1)
This address will likely be 192.168.X.X

After a host-only adapter is set up, I used PuTTY with XMing as the X server.
To connect PuTTY to the mininet VM, open windows cmd and enter 'putty.exe -X mininet@[host-interface ip]'
Then wireshark can be opened through putty with 'sudo wireshark &'.
Do files will be disabled, but you will be able to access the interfaces as a sudoer.