import network
import time
import socket
import ntptime as ntp
#test
ssid = "SMARTments Ludwig Bamberger"
key  = "ckmuKNW7LcNc"

station = network.WLAN(network.STA_IF)
station.active(True)
print ("Connecting .",end="")

while not station.isconnected():
    print (".",end="")
    station.connect(ssid, key)
    time.sleep(1)

print(" Connected!")
print("My IP Address:", station.ifconfig()[0])

ntp.settime()
UTC_OFFSET = 1 * 60 * 60
actual_time = time.localtime(time.time() + UTC_OFFSET)
print(actual_time)

s = socket.socket()
ai = socket.getaddrinfo("pumpkin.international", 80)
addr = ai[0][-1]
s.connect(addr)
s.send(b"GET / HTTP/1.0\r\n\r\n")
print(s.recv(4096))
s.close()
